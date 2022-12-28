package cz.uhk.ppro.pushntf.api;

import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.model.Product;
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

@Tag(name = "product", description = "API for Product")
@RequestMapping("/api/products")
public interface ProductApi {

    @Operation(summary = "Find product by UUID", description = "Returns a single product", tags = { "product" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "Invalid UUID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
    @RequestMapping(value = "/{uuid}", produces = { "application/json",  "application/vnd.api+json"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> findByUuid(
            @Parameter(description = "UUID of product", required = true)
            @PathVariable String uuid)
            throws Exception;

    @Operation(summary = "List all products", description = "Returns a product collection", tags = { "product" })
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Product> findProducts();

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable("uuid") final String uuid, @RequestBody final Product product) throws Exception;

    @Operation(summary = "Create product", description = "Create new product entity", tags = { "product" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = Product.class)) }) })
    @PostMapping(value = "/", consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<Product> createProduct(
            @NotNull
            @Parameter(description = "Created product object", required = true)
            @Valid @RequestBody Product body)
            throws Exception;

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public long deleteProduct(@PathVariable final String uuid) throws NotFoundException, Exception;

}

