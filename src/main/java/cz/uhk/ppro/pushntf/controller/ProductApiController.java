package cz.uhk.ppro.pushntf.controller;

import cz.uhk.ppro.pushntf.api.ProductApi;
import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.exception.NotMatchException;
import cz.uhk.ppro.pushntf.exception.ObjectExistsException;
import cz.uhk.ppro.pushntf.model.Product;
import cz.uhk.ppro.pushntf.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Log4j2
public class ProductApiController implements ProductApi {

    @Autowired
    private ProductRepository repository;

    @Override
    public ResponseEntity<Product> findByUuid(String uuid) throws Exception {

        Product product = repository.findByProductId(uuid)
                .orElseThrow(() -> new NotFoundException("Product with this UUID id not found"));
        log.info("Returned product with UUID " + uuid);
        return ResponseEntity.ok().body(product);
    }

    @Override
    public Collection<Product> findProducts() {
        log.info("Returned " + repository.findAll().size() + " parties");
        return repository.findAll();
    }

    @Override
    public Product updateProduct(String uuid, Product product) throws Exception {
        System.out.println(product.getProductId() + "\n");
        System.out.println(uuid);
        if (product.getProductId() != null && !product.getProductId().equals(uuid))
            throw new NotMatchException("UUID in URL not match URL in body");
        Product productDb = repository.findByProductId(uuid).orElseThrow(() -> new NotFoundException("Product with this UUID id not found"));
        if (product.getProductCode() != null) productDb.setProductCode(product.getProductCode());
        if (product.getProductName() != null) productDb.setProductName(product.getProductName());
        if (product.getPrice() != null) productDb.setPrice(product.getPrice());
        if (product.getCurrency() != null) productDb.setCurrency(product.getCurrency());

        log.info("Product with UUID " + uuid + " has been updated");
        return repository.save(productDb);
    }

    @Override
    public ResponseEntity<Product> createProduct(Product body) throws Exception {

        if (repository.findByProductId(body.getProductId()).isPresent() == true) {
            throw new ObjectExistsException("Entity with this UUID exists, if you want update, use PUT");
        }
        log.info("Product with UUID " + body.getProductId() + " has been created");
        return new ResponseEntity<Product>(repository.save(body), HttpStatus.CREATED);
    }

    @Override

    public long deleteProduct(String uuid) throws Exception {
        repository.findByProductId(uuid).orElseThrow(() -> new NotFoundException("Product with this UUID id not found"));
        repository.deleteByProductId(uuid);
        log.info("Product with UUID " + uuid + " has been deleted");
        return 1;
    }
}
