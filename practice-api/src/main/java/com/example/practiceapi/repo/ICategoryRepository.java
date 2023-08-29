package com.example.practiceapi.repo;

import com.example.practiceapi.models.de.CategoryDE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryDE, Long> {

    boolean existsByName(String categoryName);

    Optional<CategoryDE> findByCategoryName(String categoryName);

}
