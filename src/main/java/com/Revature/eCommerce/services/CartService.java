package com.Revature.eCommerce.services;
import com.Revature.eCommerce.dao.CartDAO;

import com.Revature.eCommerce.dao.ProductDAO;
import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import java.util.ArrayList;
import java.util.Optional;

public class CartService {
    private final CartDAO cartDAO;
    public CartService(CartDAO cartDAO)
    {
        this.cartDAO = cartDAO;
    }

    public ArrayList<CartItem> getCartItems(String cartID)
    {

        return cartDAO.getCartItems(cartID);
   }
   public Optional<Cart> getCart(String userId)
   {

       return cartDAO.getCart(userId);
   }

   public void changeItemQuantity(String productId, int quantity, String cartId)
   {
       cartDAO.changeItemQuantity(productId, quantity, cartId);
   }


}
