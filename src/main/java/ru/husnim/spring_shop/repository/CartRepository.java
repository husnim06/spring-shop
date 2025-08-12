package ru.husnim.spring_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.husnim.spring_shop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

