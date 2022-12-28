package cz.uhk.ppro.pushntf.api;

import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.model.Document;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Tag(name = "document", description = "API for Document")
@RequestMapping("/api/documents")
public interface DocumentApi {

    @Operation(summary = "Find document by UUID", description = "Returns a single document", tags = {"document"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Document.class))),
            @ApiResponse(responseCode = "400", description = "Invalid UUID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Document not found", content = @Content)})
    @RequestMapping(value = "/{uuid}", produces = {"application/json", "application/vnd.api+json"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Document> findByUuid(
            @Parameter(description = "UUID of document", required = true)
            @PathVariable String uuid)
            throws Exception;

    @Operation(summary = "List all documents", description = "Returns a document collection", tags = {"document"})
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Document> findDocuments();

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Document updateDocument(@PathVariable("uuid") final String uuid, @RequestBody final Document document) throws Exception;

    @Operation(summary = "Create document", description = "Create new document entity", tags = {"document"})
    @ApiResponses(value = {@ApiResponse(description = "successful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Document.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = Document.class))})})
    @PostMapping(value = "/", consumes = {"application/json", "application/xml", "application/x-www-form-urlencoded"})
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<Document> createDocument(
            @NotNull
            @Parameter(description = "Created document object", required = true)
            @Valid @RequestBody Document body)
            throws Exception;

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public long deleteDocument(@PathVariable final String uuid) throws NotFoundException, Exception;

}

