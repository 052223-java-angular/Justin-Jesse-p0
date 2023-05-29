package com.Revature.eCommerce.services;
import com.Revature.eCommerce.dao.CartDAO;

import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartService {
    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public ArrayList<CartItem> getCartItems(String cartID) {

        return cartDAO.getCartItems(cartID);
    }

    public Optional<Cart> getCart(String userId) {

        return cartDAO.getCart(userId);
    }

    public void changeItemQuantity(Product product, int quantity, String cartId) {
        cartDAO.changeItemQuantity(product.getProductId(), quantity, cartId);

    }

    public void changeItemPrice(Product product, int updatedPrice, String cartId) {
        cartDAO.changeItemPrice(product, updatedPrice, cartId);

    }

    public int calculatePrice(Product product, int quantity) {
        return product.getPricing() * quantity;
    }


    public int getAmountSpent(ArrayList<CartItem> items) {
        return items.stream().mapToInt(CartItem::getPrice).sum();
    }

    public void deleteItem(CartItem item) {
        cartDAO.delete(item.getId());
    }

    public void deleteCart(String id)
    {
        cartDAO.deleteCart(id);
    }
    public void newCart(String id)
    {
        cartDAO.update(id);
    }
}