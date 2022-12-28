package cz.uhk.ppro.pushntf.api;

import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.model.Investment;
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

@Tag(name = "investment", description = "API for Investment")
@RequestMapping("/api/investments")
public interface InvestmentApi {

    @Operation(summary = "Find investment by UUID", description = "Returns a single investment", tags = {"investment"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Investment.class))),
            @ApiResponse(responseCode = "400", description = "Invalid UUID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Investment not found", content = @Content)})
    @RequestMapping(value = "/{uuid}", produces = {"application/json", "application/vnd.api+json"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Investment> findByUuid(
            @Parameter(description = "UUID of investment", required = true)
            @PathVariable String uuid)
            throws Exception;

    @Operation(summary = "List all investments", description = "Returns a investment collection", tags = {"investment"})
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Investment> findInvestments();

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Investment updateInvestment(@PathVariable("uuid") final String uuid, @RequestBody final Investment investment) throws Exception;

    @Operation(summary = "Create investment", description = "Create new investment entity", tags = {"investment"})
    @ApiResponses(value = {@ApiResponse(description = "successful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Investment.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = Investment.class))})})
    @PostMapping(value = "/", consumes = {"application/json", "application/xml", "application/x-www-form-urlencoded"})
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<Investment> createInvestment(
            @NotNull
            @Parameter(description = "Created investment object", required = true)
            @Valid @RequestBody Investment body)
            throws Exception;

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public long deleteInvestment(@PathVariable final String uuid) throws NotFoundException, Exception;

}

