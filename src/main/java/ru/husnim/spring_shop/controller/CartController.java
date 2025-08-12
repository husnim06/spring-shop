package ru.husnim.spring_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.husnim.spring_shop.model.Cart;
import ru.husnim.spring_shop.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        return new ResponseEntity<>(cartService.getCart(cartId), HttpStatus.OK);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<Void> addItemToCart(@PathVariable Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        cartService.addItemToCart(cartId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        cartService.removeItemFromCart(cartId, cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
