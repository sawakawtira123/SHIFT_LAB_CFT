package ru.kashtyra.alexandr.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kashtyra.alexandr.demo.common.exception.AlreadyExistException;
import ru.kashtyra.alexandr.demo.common.exception.ApiFormatException;
import ru.kashtyra.alexandr.demo.common.exception.NotFoundException;
import ru.kashtyra.alexandr.demo.dto.entity.Product;
import ru.kashtyra.alexandr.demo.dto.entity.Properties;
import ru.kashtyra.alexandr.demo.dto.entity.TypeProduct;
import ru.kashtyra.alexandr.demo.repositories.ProductRepository;
import ru.kashtyra.alexandr.demo.repositories.PropertiesRepository;
import ru.kashtyra.alexandr.demo.repositories.TypeProductRepository;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final TypeProductRepository typeProductRepository;
    private final PropertiesRepository propertiesRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, TypeProductRepository typeProductRepository, PropertiesRepository propertiesRepository) {
        this.productRepository = productRepository;
        this.typeProductRepository = typeProductRepository;
        this.propertiesRepository = propertiesRepository;
       
    }

    private static Supplier<? extends DataAccessException> getSupplier(Integer productId) {
        return () -> new NotFoundException(String.format("Product [%s] not found", productId));
    }

    //вывод всех продуктов
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    //вывод продукта по Id
    public Optional<Product> getProductById(Integer ProductId) {
        return productRepository.findById(ProductId);
    }

    //добавление продукта
    public void add(Product product) {
        Optional<Product> findAccount = productRepository.findBySeriesProduct(product.getSeriesProduct());
        if (findAccount.isEmpty()) {
            productRepository.saveAndFlush(Product
                    .builder()
                    .manufacturer(product.getManufacturer())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .seriesProduct(product.getSeriesProduct())
                    .typeProduct(checkTypeProduct(product.getTypeProduct()))
                    .property(product.getProperty())
                    .build());
        } else
            throw new AlreadyExistException("Product series is already exist");
    }

    //роверка правильности ввода типа продукта
    private String checkTypeProduct(String typeProduct) {
        Iterable<TypeProduct> typeProducts = typeProductRepository.findAll();
        for (TypeProduct existingTypes : typeProducts) {
            if (existingTypes.getName().equals(typeProduct))
                return typeProduct;
        }
        throw new ApiFormatException(HttpStatus.BAD_REQUEST, String.format("Type product [%s] does not exist", typeProduct));
    }

    public Integer edit(Integer productId, Product product) {
        Product availableProduct = productRepository.findById(productId).orElseThrow(getSupplier(productId));
        availableProduct.setManufacturer(product.getManufacturer());
        availableProduct.setPrice(product.getPrice());
        availableProduct.setProperty(product.getProperty());
        availableProduct.setSeriesProduct(product.getSeriesProduct());
        availableProduct.setQuantity(product.getQuantity());
        availableProduct.setTypeProduct(product.getTypeProduct());
        return productRepository.saveAndFlush(availableProduct).getId();
    }

    //вывод продукта по типу
    public Iterable<Product> getAllTypeProduct(String typeProduct) {
        if (typeProduct.isEmpty()) {
            throw new AlreadyExistException("Product type not specified");
        } else {
            checkTypeProduct(typeProduct);
        }
        return productRepository.findByTypeProduct(typeProduct);
    }

    private void addTypeCategory(TypeProduct typeProduct) {
        typeProductRepository.save(typeProduct);
    }

    private void addProperty(Properties properties) {
        propertiesRepository.save(properties);
    }

}
