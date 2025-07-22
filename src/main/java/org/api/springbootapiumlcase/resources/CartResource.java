package org.api.springbootapiumlcase.resources;

import org.api.springbootapiumlcase.domain.ShoppingCart;
import org.api.springbootapiumlcase.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/carts")
public class CartResource {

    private final CartService cartService;

    @Autowired
    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable Long customerId) {
        ShoppingCart cart = cartService.getCart(customerId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{customerId}/products/{productId}")
    public ResponseEntity<ShoppingCart> addProduct(
            @PathVariable Long customerId,
            @PathVariable Long productId,
            @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        ShoppingCart cart = cartService.addProductToCart(customerId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public ResponseEntity<ShoppingCart> removeProduct(
            @PathVariable Long customerId,
            @PathVariable Long productId) {
        ShoppingCart cart = cartService.removeProductFromCart(customerId, productId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ShoppingCart> clearCart(@PathVariable Long customerId) {
        ShoppingCart cart = cartService.clearCart(customerId);
        return ResponseEntity.noContent().build();
    }
}