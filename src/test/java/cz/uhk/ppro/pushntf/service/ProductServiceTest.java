package cz.uhk.ppro.pushntf.service;

import cz.uhk.ppro.pushntf.model.Product;
import cz.uhk.ppro.pushntf.repository.ProductRepository;
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
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    void canFindAllProducts() {
        //when
        productService.findAll();
        //then
        verify(productRepository).findAll();
    }

    @Test
    void findProductByUUID() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        //when
        productService.findByUUID(uuid);
        //then
        verify(productRepository).findByProductId(uuid);
    }

    @Test
    void canCreateProduct() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        Product product = new Product(uuid,"15312548","Standard Account", 222.24, "CZK", LocalDateTime.now());

        //when
        productService.create(product);

        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void canUpdateProduct() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        Product product = new Product(uuid,"15312548","Standard Account", 222.24, "CZK", LocalDateTime.now());

        //when
        productService.update(product);

        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void deleteProductByUUID() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        //when
        productService.deleteByUUID(uuid);
        //then
        verify(productRepository).deleteByProductId(uuid);
    }
}