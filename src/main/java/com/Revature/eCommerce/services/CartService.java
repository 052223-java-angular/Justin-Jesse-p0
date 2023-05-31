package com.Revature.eCommerce.services;
import com.Revature.eCommerce.dao.CartDAO;

import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
 * Service layer for screen and DAO
 */
public class CartService {
    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    /**
     * Gets an array list based on the cart id
     * @param cartID - cartid
     * @return - arraylist of cartitems
     */
    public ArrayList<CartItem> getCartItems(String cartID) {

        return cartDAO.getCartItems(cartID);
    }

    /**
     * gets cart based on the user id
     * @param userId - user id
     * @return - cart associated with user
     */
    public Optional<Cart> getCart(String userId) {

        return cartDAO.getCart(userId);
    }

    /**
     * changes the item quantity of the product in the cart
     * @param product - product
     * @param quantity - quantity
     * @param cartId - cart id
     */
    public void changeItemQuantity(Product product, int quantity, String cartId) {
        cartDAO.changeItemQuantity(product.getProductId(), quantity, cartId);

    }

    /**
     * changes the item price of the product in the cart
     * @param product product
     * @param updatedPrice new price
     * @param cartId - cart id
     */
    public void changeItemPrice(Product product, int updatedPrice, String cartId) {
        cartDAO.changeItemPrice(product, updatedPrice, cartId);

    }

    /**
     * calculates the price of items
     * @param product
     * @param quantity
     * @return
     */
    public int calculatePrice(Product product, int quantity) {
        return product.getPricing() * quantity;
    }

    /**
     * gets the total sum of the amount spent in cart
     * @param items
     * @return
     */
    public int getAmountSpent(ArrayList<CartItem> items)
    {
        return items.stream().mapToInt(CartItem::getPrice).sum();
    }

    /**
     * deletes the selected cart item
     * @param item
     */
    public void deleteItem(CartItem item) {
        cartDAO.delete(item.getId());
    }

    /**
     * Adds items to the cart
     * @param product
     * @param item
     * @param cart
     */
    public void setCart(Product product, CartItem item, Cart cart){
        cartDAO.setCart(product, item, cart);
    }

    /**
     * Deletes the cart items from cart
     * @param string
     */
    public void deleteCart(String string)
    {
        cartDAO.deleteCart(string);
    }

    /**
     * Updates the user cart
     * @param id
     */
    public void newCart(String id)
    {
        cartDAO.update(id);
    }

    /**
     * checks if user has a cart assigned to them
     * @param userID
     * @return
     */
    public boolean doesUserHaveCart(String userID)
    {
        Optional<Cart> cartOptional = cartDAO.getCart(userID);
        return cartOptional.isPresent();
    }

    /**
     * Creates a cart if  a user doesnt have a cart
     * @param userId
     */
    public void createCart(String userId)
    {
        cartDAO.createCart(userId);
    }
}