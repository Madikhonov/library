package com.stem_library.library.Service;

import com.stem_library.library.Entity.CartItem;
import com.stem_library.library.Entity.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private static final String CART_SESSION_KEY = "cartItems";

    public void addToCart(Product product, int quantity, HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

       
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                session.setAttribute(CART_SESSION_KEY, cartItems);
                return;
            }
        }

    
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItems.add(cartItem);
        session.setAttribute(CART_SESSION_KEY, cartItems);
    }

    public List<CartItem> getCartItems(HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>)
                session.getAttribute(CART_SESSION_KEY);
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        return cartItems;
    }
}
