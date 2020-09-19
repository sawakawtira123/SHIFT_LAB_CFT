package ru.kashtyra.alexandr.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kashtyra.alexandr.demo.dto.entity.Properties;

@Repository
public interface PropertiesRepository extends CrudRepository<Properties, Integer>, JpaRepository<Properties, Integer> {
}
