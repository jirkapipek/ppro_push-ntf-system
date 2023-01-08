package cz.uhk.ppro.pushntf.service;

import cz.uhk.ppro.pushntf.model.Product;
import cz.uhk.ppro.pushntf.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService implements BaseEntityService<Product> {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findByUUID(String uuid) {
        Product Product = repository.findByProductId(uuid);
        return Product;
    }

    @Override
    public Product create(Product Product) {
        return repository.save(Product);
    }

    @Override
    public Product update(Product Product) {
        return repository.save(Product);
    }

    @Override
    public void deleteByUUID(String uuid) {
        repository.deleteByProductId(uuid);

    }
}
