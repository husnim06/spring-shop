package ru.husnim.spring_shop.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.husnim.spring_shop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Optional<Category> findByName(String name);
    
}
