package cz.uhk.ppro.pushntf.service;

import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.model.Document;
import cz.uhk.ppro.pushntf.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class DocumentService implements BaseEntityService<Document> {

    @Autowired
    private DocumentRepository repository;

    @Override
    public List<Document> findAll() {
        return repository.findAll();
    }

    @Override
    public Document findByUUID(String uuid) {
        Document document = repository.findByDocumentId(uuid);
        return document;
    }

    @Override
    public Document create(Document document) {
        return repository.save(document);
    }

    @Override
    public Document update(Document document) {
        return repository.save(document);
    }

    @Override
    public void deleteByUUID(String uuid) {
        repository.deleteByDocumentId(uuid);

    }
}
