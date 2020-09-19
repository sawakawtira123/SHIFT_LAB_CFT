package ru.kashtyra.alexandr.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kashtyra.alexandr.demo.dto.entity.TypeProduct;

@Repository
public interface TypeProductRepository extends CrudRepository<TypeProduct, String>, JpaRepository<TypeProduct, String> {
}
