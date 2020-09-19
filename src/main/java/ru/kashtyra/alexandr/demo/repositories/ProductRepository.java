package ru.kashtyra.alexandr.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kashtyra.alexandr.demo.dto.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, JpaRepository<Product, Integer> {
    Iterable<Product> findByTypeProduct(String typeProduct);
    Optional<Product> findBySeriesProduct(Integer id);
    Optional<Product> findById(Integer id);
}
