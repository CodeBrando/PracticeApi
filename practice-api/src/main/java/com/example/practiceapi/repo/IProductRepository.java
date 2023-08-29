package com.example.practiceapi.repo;

import com.example.practiceapi.models.de.ProductDE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductDE, Long> {

    boolean existsByProductCode(String productCode);

    Optional<ProductDE> findByProductCode(String productCode);

}
