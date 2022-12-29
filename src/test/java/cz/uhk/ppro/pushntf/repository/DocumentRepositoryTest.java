package cz.uhk.ppro.pushntf.repository;

import cz.uhk.ppro.pushntf.model.Document;
import cz.uhk.ppro.pushntf.model.Party;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepositoryTest;

    @Test
    void isShouldFindDocumentByDocumentId() {
        //give
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Document document = new Document(uuid,"pdf", "This is statement", "statement20221228_jpipek", "CZE", null, LocalDateTime.now());
        documentRepositoryTest.save(document);

        //when
        Document expected = documentRepositoryTest.findByDocumentId(uuid);
        //then
        assertThat(expected).isNotNull();
    }

    @Test
    void isShouldDeleteDocumentByDocumentId() {
        //give
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Document document = new Document(uuid,"pdf", "This is statement", "statement20221228_jpipek", "CZE", null, LocalDateTime.now());
        documentRepositoryTest.save(document);

        //when
        documentRepositoryTest.deleteByDocumentId(uuid);
        Document expected = documentRepositoryTest.findByDocumentId(uuid);
        //then
        assertThat(expected).isNull();
    }

}