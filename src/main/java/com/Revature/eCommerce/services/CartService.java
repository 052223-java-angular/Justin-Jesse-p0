package com.Revature.eCommerce.services;
import com.Revature.eCommerce.dao.CartDAO;

import com.Revature.eCommerce.dao.ProductDAO;
import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.Product;

import java.util.ArrayList;
import java.util.Optional;

public class CartService {
    private final CartDAO cartDAO;
    private final ProductDAO productDAO;
    public CartService(CartDAO cartDAO, ProductDAO productDAO)
    {
        this.cartDAO = cartDAO;
        this.productDAO = productDAO;
    }

    public ArrayList<CartItem> getCartItems(String cartID)
    {

        return cartDAO.getCartItems(cartID);
   }
   public Optional<Cart> getCart(String userId)
   {

       return cartDAO.getCart(userId);
   }

   public Product getProduct(String productID)
   {
     return productDAO.findById(productID);
   }

}
