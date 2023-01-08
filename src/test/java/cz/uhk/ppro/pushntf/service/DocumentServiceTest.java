package cz.uhk.ppro.pushntf.service;

import cz.uhk.ppro.pushntf.model.Document;
import cz.uhk.ppro.pushntf.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;
    private DocumentService documentService;

    @BeforeEach
    void setUp() {
        documentService = new DocumentService(documentRepository);
    }

    @Test
    void canFindAllDocuments() {
        //when
        documentService.findAll();
        //then
        verify(documentRepository).findAll();
    }

    @Test
    void findDocumentByUUID() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        //when
        documentService.findByUUID(uuid);
        //then
        verify(documentRepository).findByDocumentId(uuid);
    }

    @Test
    void canCreateDocument() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Document document = new Document(uuid,"pdf", "This is statement", "statement20221228_jpipek", "CZE", null, LocalDateTime.now());

        //when
        documentService.create(document);

        //then
        ArgumentCaptor<Document> documentArgumentCaptor = ArgumentCaptor.forClass(Document.class);

        verify(documentRepository).save(documentArgumentCaptor.capture());

        Document capturedDocument = documentArgumentCaptor.getValue();

        assertThat(capturedDocument).isEqualTo(document);
    }

    @Test
    void canUpdateDocument() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Document document = new Document(uuid,"pdf", "This is statement", "statement20221228_jpipek", "CZE", null, LocalDateTime.now());

        //when
        documentService.update(document);

        //then
        ArgumentCaptor<Document> documentArgumentCaptor = ArgumentCaptor.forClass(Document.class);

        verify(documentRepository).save(documentArgumentCaptor.capture());

        Document capturedDocument = documentArgumentCaptor.getValue();

        assertThat(capturedDocument).isEqualTo(document);
    }

    @Test
    void deleteDocumentByUUID() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        //when
        documentService.deleteByUUID(uuid);
        //then
        verify(documentRepository).deleteByDocumentId(uuid);
    }
}