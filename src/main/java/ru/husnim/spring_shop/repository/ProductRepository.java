package ru.husnim.spring_shop.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.husnim.spring_shop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    Optional<Product> findByName (String name);
    
}
