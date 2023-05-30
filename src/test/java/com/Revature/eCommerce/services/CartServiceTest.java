package com.Revature.eCommerce.services;

import com.Revature.eCommerce.dao.CartDAO;
import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.Product;
import junit.framework.TestCase;
import org.checkerframework.checker.units.qual.C;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CartServiceTest extends TestCase {

    CartService cartService;
    @Mock
    CartDAO cartDao;
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.cartService = new CartService(cartDao);
    }

    public void testGetCartItems()
    {
        String expectedUserid= "User1";
        ArrayList<Cart> expectedCart = new ArrayList<>();
        expectedCart.add(new Cart("C1",expectedUserid,0));
        expectedCart.add(new Cart("C2","User2",100));

        ArrayList<CartItem> expectedItems = new ArrayList<>();
        expectedItems.add(new CartItem("CI1", "P1", "C1", 1, 10));
        expectedItems.add(new CartItem("CI2", "P2", "C1", 2, 20));

        when(cartDao.getCartItems(expectedUserid)).thenReturn(expectedItems);

        List<CartItem> actualItems = cartService.getCartItems(expectedUserid);

        assertEquals(expectedItems, actualItems);

    }

    public void testGetCart()
    {
        String input = "validCart";
        Cart expectedCart = new Cart("Cart 1", input, 0);
        when(cartService.getCart(input)).thenReturn(Optional.of(expectedCart));

        Optional<Cart> actualCart = cartService.getCart(input);

        assertEquals(actualCart.get().getId(), expectedCart.getId());
    }

    public void testChangeItemQuantity()
    {
        Product product = new Product("P1","Product","C1",1,"Desciption");
        int quantity = 5;
        String cartId = "C1";

        doNothing().when(cartDao).changeItemQuantity(product.getProductId(), quantity, cartId);

        cartService.changeItemQuantity(product, quantity, cartId);

        verify(cartDao).changeItemQuantity(product.getProductId(), quantity, cartId);
    }



    public void testChangeItemPrice()
    {
        Product product = new Product("P1","Product","C1",1,"Desciption");
        int price = 100;
        String cartId = "C1";

        doNothing().when(cartDao).changeItemQuantity(product.getProductId(), price, cartId);

        cartService.changeItemPrice(product, price, cartId);

        verify(cartDao).changeItemPrice(product, price, cartId);
    }

    public void testCalculatePrice() {
        Product product = new Product("P1","Product","C1",10,"Desciption");
        int price = 100;
        assertEquals(1000,cartService.calculatePrice(product,price));
    }

    public void testGetAmountSpent() {
        ArrayList<CartItem> expectedItems = new ArrayList<>();
        expectedItems.add(new CartItem("CI1", "P1", "C1", 1, 10));
        expectedItems.add(new CartItem("CI2", "P2", "C1", 2, 20));

        assertEquals(30,cartService.getAmountSpent(expectedItems));
    }

    public void testDeleteItem()
    {
        String itemId = "item1";
        CartItem item = new CartItem(itemId, "product1", "cart1", 1, 10);
        doNothing().when(cartDao).deleteItem(itemId);

        cartService.deleteItem(item);

        verify(cartDao).deleteItem(itemId);
    }

    public void testDeleteCart()
    {
        String itemId = "cart1";
        CartItem item = new CartItem(itemId, "product1", "cart1", 1, 10);
        doNothing().when(cartDao).deleteItemFromCart(itemId);

        cartService.deleteCart(item.getCartId());

        verify(cartDao).deleteItemFromCart(itemId);
    }

    public void testNewCart() {
        String userId = "user1";
        doNothing().when(cartDao).update(userId);

        cartService.newCart(userId);

        verify(cartDao).update(userId);
    }
}