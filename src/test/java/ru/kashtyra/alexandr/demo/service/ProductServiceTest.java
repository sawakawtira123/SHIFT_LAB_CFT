package ru.kashtyra.alexandr.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import ru.kashtyra.alexandr.demo.common.exception.AlreadyExistException;
import ru.kashtyra.alexandr.demo.common.exception.ApiFormatException;
import ru.kashtyra.alexandr.demo.common.exception.NotFoundException;
import ru.kashtyra.alexandr.demo.dto.entity.Product;
import ru.kashtyra.alexandr.demo.repositories.ProductRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@ComponentScan(basePackages = "ru.kashtyra.alexandr.demo.service")
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void addTypeException() {
        when(productRepository.findBySeriesProduct(any())).thenReturn(Optional.empty());
        String typeProduct = "Test type";
        try {
            Product product = getProduct();
            product.setTypeProduct(typeProduct);
            productService.add(product);
            Assertions.fail("Expected ApiFormatException");
        } catch (ApiFormatException thrown) {
            Assertions.assertEquals(String.format("Type product [%s] does not exist", typeProduct), thrown.getMessage());
            Assertions.assertEquals(thrown.getErrorHttpStatus(), HttpStatus.BAD_REQUEST);
        }
    }
    @Test
    void addTest() {
        when(productRepository.findBySeriesProduct(any())).thenReturn(Optional.empty());
        productService.add(getProduct());
    }
    @Test
    void addWithException() {
        Product product = getProduct();
        when(productRepository.findBySeriesProduct(any())).thenReturn(Optional.of(product));
        try {
            productService.add(getProduct());
            Assertions.fail("Expected AlreadyExistException");
        } catch (AlreadyExistException thrown) {
            Assertions.assertEquals("Product series is already exist", thrown.getMessage());
        }
    }

    @Test
    void editWithException() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());
        Product product = getProduct();
        Integer productId = product.getId();
        try {
            productService.edit(productId,product);
            Assertions.fail("Expected NotFoundException");
        } catch (NotFoundException thrown) {
            Assertions.assertEquals(String.format("Product [%s] not found", productId), thrown.getMessage());
            Assertions.assertEquals(thrown.getErrorHttpStatus(), HttpStatus.BAD_REQUEST);
        }
    }

    private Product getProduct(){
    Product product = new Product();
    product.setId(1);
    product.setTypeProduct("Ноутбук");
    product.setManufacturer("sony");
    product.setSeriesProduct(1234);
    product.setQuantity(5);
    product.setProperty("16 Дюймовые");
    product.setPrice(24000);
    return product;
    }
}

