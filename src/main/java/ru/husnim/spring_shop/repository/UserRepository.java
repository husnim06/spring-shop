package ru.husnim.spring_shop.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.husnim.spring_shop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByName (String name);
    
}
