package cz.uhk.ppro.pushntf.repository;

import cz.uhk.ppro.pushntf.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepositoryTest;


    @Test
    void isShouldFindProductByProductId() {
        //give
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Product product = new Product(uuid,"15312548","Standard Account", 222.24, "CZK", LocalDateTime.now());

        productRepositoryTest.save(product);

        //when
        Product expected = productRepositoryTest.findByProductId(uuid);
        //then
        assertThat(expected).isNotNull();
    }

    @Test
    void isShouldDeleteProductByProductId() {
        //give
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Product product = new Product(uuid,"15312548","Standard Account", 222.24, "CZK", LocalDateTime.now());
        productRepositoryTest.save(product);

        //when
        productRepositoryTest.deleteByProductId(uuid);
        Product expected = productRepositoryTest.findByProductId(uuid);
        //then
        assertThat(expected).isNull();
    }
}