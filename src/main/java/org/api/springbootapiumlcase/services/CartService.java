package org.api.springbootapiumlcase.services;

import jakarta.transaction.Transactional;
import org.api.springbootapiumlcase.domain.CartItem;
import org.api.springbootapiumlcase.domain.Customer;
import org.api.springbootapiumlcase.domain.Product;
import org.api.springbootapiumlcase.domain.ShoppingCart;
import org.api.springbootapiumlcase.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {


    private final ShoppingCartRepository cartRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public CartService(ShoppingCartRepository cartRepository, CustomerService customerService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public ShoppingCart getCart(Long customerId) {
        return cartRepository.findById(customerId).orElseGet(() -> {
            Customer customer = customerService.findById(customerId);
            ShoppingCart newCart = new ShoppingCart();
            newCart.setCustomer(customer);
            return cartRepository.save(newCart);
        });
    }

    @Transactional
    public ShoppingCart addProductToCart(Long customerId, Long productId, int quantity) {
        ShoppingCart cart = getCart(customerId);
        Product product = productService.findById(productId);

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItem newCartItem = new CartItem(cart, product, quantity);
            cart.getItems().add(newCartItem);
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public ShoppingCart removeProductFromCart(Long customerId, Long productId) {
        ShoppingCart cart = getCart(customerId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return cartRepository.save(cart);
    }

    @Transactional
    public ShoppingCart clearCart(Long customerId) {
        ShoppingCart cart = getCart(customerId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}