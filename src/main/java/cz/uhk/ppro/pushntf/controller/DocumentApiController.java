package cz.uhk.ppro.pushntf.controller;

import cz.uhk.ppro.pushntf.api.DocumentApi;
import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.exception.NotMatchException;
import cz.uhk.ppro.pushntf.exception.ObjectExistsException;
import cz.uhk.ppro.pushntf.model.Document;
import cz.uhk.ppro.pushntf.model.Party;
import cz.uhk.ppro.pushntf.repository.DocumentRepository;
import cz.uhk.ppro.pushntf.repository.PartyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Log4j2
public class DocumentApiController implements DocumentApi {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private PartyRepository partyRepository;

    @Override
    public ResponseEntity<Document> findByUuid(String uuid) throws Exception {

        Document document = documentRepository.findByDocumentId(uuid)
                .orElseThrow(() -> new NotFoundException("Document with this UUID id not found"));
        log.info("Returned document with UUID " + uuid);
        return ResponseEntity.ok().body(document);
    }

    @Override
    public Collection<Document> findDocuments() {
        log.info("Returned " + documentRepository.findAll().size() + " parties");
        return documentRepository.findAll();
    }

    @Override
    public Document updateDocument(String uuid, Document document) throws Exception {
        System.out.println(document.getDocumentId() + "\n");
        System.out.println(uuid);
        if (document.getDocumentId() != null && !document.getDocumentId().equals(uuid))
            throw new NotMatchException("UUID in URL not match URL in body");
        Document documentDb = documentRepository.findByDocumentId(uuid).orElseThrow(() -> new NotFoundException("Document with this UUID id not found"));

        if (document.getDocumentLanguage() != null) documentDb.setDocumentLanguage(document.getDocumentLanguage());
        if (document.getOwner() != null) {
            documentDb.setOwner(document.getOwner());
            partyRepository.findByPartyId(document.getOwner().getPartyId()).orElseThrow(() -> new NotFoundException("Owner with this " + document.getOwner().getPartyId() + " partyId not found"));
        }

        if (document.getDescription() != null) documentDb.setDescription(document.getDescription());
        if (document.getFilename() != null) documentDb.setFilename(document.getFilename());
        if (document.getFormat() != null) documentDb.setFormat(document.getFormat());

        log.info("Document with UUID " + uuid + " has been updated");
        return documentRepository.save(documentDb);
    }

    @Override
    public ResponseEntity<Document> createDocument(Document body) throws Exception {

        if (documentRepository.findByDocumentId(body.getDocumentId()).isPresent() == true) {
            throw new ObjectExistsException("Entity with this UUID exists, if you want update, use PUT");
        }
        Party partyDb = partyRepository.findByPartyId(body.getOwner().getPartyId()).orElseThrow(() -> new NotFoundException("Owner with this " + body.getOwner().getPartyId() + " partyId not found"));

        log.info("Document with UUID " + body.getDocumentId() + " has been created");
        return new ResponseEntity<Document>(documentRepository.save(body), HttpStatus.CREATED);
    }

    @Override

    public long deleteDocument(String uuid) throws Exception {
        documentRepository.findByDocumentId(uuid).orElseThrow(() -> new NotFoundException("Document with this UUID id not found"));
        documentRepository.deleteByDocumentId(uuid);
        log.info("Document with UUID " + uuid + " has been deleted");
        return 1;
    }
}
