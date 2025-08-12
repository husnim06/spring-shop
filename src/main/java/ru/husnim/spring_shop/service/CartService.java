package ru.husnim.spring_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.husnim.spring_shop.model.Cart;
import ru.husnim.spring_shop.model.CartItem;
import ru.husnim.spring_shop.model.Product;
import ru.husnim.spring_shop.repository.CartRepository;
import ru.husnim.spring_shop.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;
    
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Корзина не найдена"));
    }

    public Cart addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = getCart(cartId);
        
        // Загружаем продукт по ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));

        // Создаем новый элемент корзины
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        
        // Добавляем элемент в корзину
        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long cartId, Long cartItemId) {
        Cart cart = getCart(cartId);
        cart.getItems().removeIf(item -> item.getId().equals(cartItemId));
        return cartRepository.save(cart);
    }

    public Cart clearCart(Long cartId) {
        Cart cart = getCart(cartId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
    
    public void deleteCart(Long cartId) {
        Cart cart = getCart(cartId);
        cartRepository.delete(cart);
    }
    
}
