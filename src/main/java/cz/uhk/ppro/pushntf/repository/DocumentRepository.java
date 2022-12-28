package cz.uhk.ppro.pushntf.repository;

import cz.uhk.ppro.pushntf.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByDocumentId(String uuid);

    @Transactional
    long deleteByDocumentId(String uuid);
}
