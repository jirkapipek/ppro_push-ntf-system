package cz.uhk.ppro.pushntf.api;

import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.model.Party;
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

@Tag(name = "party", description = "API for Party")
@RequestMapping("/api/parties")
public interface PartyApi {

    @Operation(summary = "Find person (Party) by UUID", description = "Returns a single party", tags = { "party" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Party.class))),
            @ApiResponse(responseCode = "400", description = "Invalid UUID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Party not found", content = @Content) })
    @RequestMapping(value = "/{uuid}", produces = { "application/json",  "application/vnd.api+json"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Party> findByUuid(
            @Parameter(description = "UUID of party", required = true)
            @PathVariable String uuid)
            throws Exception;

    @Operation(summary = "List all parties", description = "Returns a party collection", tags = { "party" })
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Party> findParties();

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Party updateParty(@PathVariable("uuid") final String uuid, @RequestBody final Party party) throws Exception;

    @Operation(summary = "Create party", description = "Create new party entity", tags = { "party" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Party.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = Party.class)) }) })
    @PostMapping(value = "/", consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<Party> createParty(
            @NotNull
            @Parameter(description = "Created party object", required = true)
            @Valid @RequestBody Party body)
            throws Exception;

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public long deleteParty(@PathVariable final String uuid) throws NotFoundException, Exception;

}

