package ru.kashtyra.alexandr.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kashtyra.alexandr.demo.common.UriPath;
import ru.kashtyra.alexandr.demo.dto.entity.Product;
import ru.kashtyra.alexandr.demo.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping(value = UriPath.PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody Product product) {
        productService.add(product);
    }

    @PutMapping(UriPath.PRODUCT_ID)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Integer> editProduct(@PathVariable Integer productId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.edit(productId, product));
    }

    @GetMapping
    @ResponseBody
    @RequestMapping(value = UriPath.PRODUCT_TYPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Product>> getAllByTypeProduct(@RequestParam String typeProduct) {
        return ResponseEntity.ok(productService.getAllTypeProduct(typeProduct));
    }

    @GetMapping(UriPath.PRODUCT_ID)
    @ResponseBody
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

}
