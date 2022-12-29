package cz.uhk.ppro.pushntf.controller;

import cz.uhk.ppro.pushntf.api.DocumentApi;
import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.exception.NotMatchException;
import cz.uhk.ppro.pushntf.exception.NotValidException;
import cz.uhk.ppro.pushntf.exception.ObjectExistsException;
import cz.uhk.ppro.pushntf.model.Document;
import cz.uhk.ppro.pushntf.model.Party;
import cz.uhk.ppro.pushntf.repository.PartyRepository;
import cz.uhk.ppro.pushntf.service.DocumentService;
import cz.uhk.ppro.pushntf.service.PartyService;
import cz.uhk.ppro.pushntf.utils.UUIDValidator;
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
    private DocumentService documentService;
    @Autowired
    private PartyService partyService;

    @Autowired
    private UUIDValidator validator;
    @Override
    public ResponseEntity<Document> findByUuid(String uuid) throws Exception {


        Document document = documentService.findByUUID(uuid);

        if (document == null)
            throw new NotFoundException("Document with this UUID id not found");

        log.info("Returned document with UUID " + uuid);

        return ResponseEntity.ok().body(document);
    }

    @Override
    public Collection<Document> findDocuments() {
        log.info("Returned " + documentService.findAll().size() + " parties");
        return documentService.findAll();
    }

    @Override
    public Document updateDocument(String uuid, Document document) throws Exception {

        if (document.getDocumentId() != null && !document.getDocumentId().equals(uuid))
            throw new NotMatchException("UUID in URL not match URL in body");

        Document documentDb = documentService.findByUUID(uuid);

        if (documentDb == null)
            throw new NotFoundException("Document with this UUID id not found");

        if (document.getDocumentLanguage() != null) documentDb.setDocumentLanguage(document.getDocumentLanguage());
        if (document.getOwner() != null) {
            if (partyService.findByUUID(document.getOwner().getPartyId()) == null)
                throw new NotFoundException("Owner with this " + document.getOwner().getPartyId() + " partyId not found");

            documentDb.setOwner(document.getOwner());

        }

        if (document.getDescription() != null) documentDb.setDescription(document.getDescription());
        if (document.getFilename() != null) documentDb.setFilename(document.getFilename());
        if (document.getFormat() != null) documentDb.setFormat(document.getFormat());

        log.info("Document with UUID " + uuid + " has been updated");
        return documentService.update(documentDb);
    }

    @Override
    public ResponseEntity<Document> createDocument(Document body) throws Exception {

        if(!validator.isUuidStringValid(body.getDocumentId())) throw new NotValidException("UUID is not valid");

        if (documentService.findByUUID(body.getDocumentId()) != null)
            throw new ObjectExistsException("Entity with this UUID exists, if you want update, use PUT");

        if (partyService.findByUUID(body.getOwner().getPartyId()) == null)
            throw new NotFoundException("Owner with this " + body.getOwner().getPartyId() + " partyId not found");

        log.info("Document with UUID " + body.getDocumentId() + " has been created");

        return new ResponseEntity<Document>(documentService.create(body), HttpStatus.CREATED);
    }

    @Override

    public long deleteDocument(String uuid) throws Exception {
        if (documentService.findByUUID(uuid) == null)
            throw new NotFoundException("Document with this UUID id not found");

        documentService.deleteByUUID(uuid);
        log.info("Document with UUID " + uuid + " has been deleted");

        return 1;
    }
}
