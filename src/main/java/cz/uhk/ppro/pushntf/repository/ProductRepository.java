package cz.uhk.ppro.pushntf.repository;

import cz.uhk.ppro.pushntf.model.Party;
import cz.uhk.ppro.pushntf.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(String uuid);
    @Transactional
    long deleteByProductId(String uuid);
}
