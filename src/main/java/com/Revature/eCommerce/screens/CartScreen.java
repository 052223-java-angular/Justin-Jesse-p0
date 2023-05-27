package com.Revature.eCommerce.screens;

import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.services.CartService;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.utils.Session;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class CartScreen implements IScreen
    {
        private static final Logger logger = LogManager.getLogger(CartScreen.class);
        private final Session session;
        private final CartService cartService;
        private final RouterService router;
        ArrayList<CartItem> items;
    public CartScreen(CartService cartService, RouterService routerService, Session session)
    {
        this.cartService = cartService;
        this.router = routerService;
        this.session = session;
        this.items = new ArrayList<>();
    }

    @Override
    public void start(Scanner scan)
    {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the cart screen " + session.getUsername() +"!");

                displayCart();
                System.out.println();
                System.out.println("Press[1] to checkout.");
                System.out.println("Press[2] to add an items.");
                System.out.println("Press[3] to delete an item.");
                System.out.println("Press[X] Menu");


                System.out.print("\nEnter: ");
                input = scan.nextLine();
                switch (input.toLowerCase()) {
                    case "1":
                        //checkout
                        break exit;
                    case "2":
                        clearScreen();
                        //implement an add item
                        break;
                    case "3":
                        //clearScreen();
                        //implement a delete item
                        //scan.nextLine();
                        break;
                    case "x":
                        clearScreen();
                        router.navigate("/menu", scan);
                        break exit;
                    default:
                        clearScreen();
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }
        }
    }

    public void displayCart()
    {
        //System.out.println(session.getId());
        Optional<Cart> cart = cartService.getCart(session.getId());


       if (cart.isEmpty())
        {

            System.out.println("\nThere is nothing in your cart!");
        }
        else
        {
            items = cartService.getCartItems(cart.get().getId());
            System.out.println("-------- Your Cart --------");

            for(CartItem item:items)
            {
                Product product = cartService.getProduct(item.getProductId());

                System.out.println("Name: " + product.getProductName());

                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Price: " + item.getPrice());
                System.out.println("---------------------------");

            }
            System.out.println("Amount spent: " + cart.get().getAmountSpent());
            System.out.println("---------------------------");
        }

    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
