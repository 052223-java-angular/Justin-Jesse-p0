package com.Revature.eCommerce.screens;

import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.services.CartService;
import com.Revature.eCommerce.services.ProductService;
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
        private final ProductService productService;
        private final RouterService router;
        ArrayList<CartItem> items;
        Optional<Cart> cart;
    public CartScreen(CartService cartService,ProductService productService, RouterService routerService, Session session)
    {
        this.cartService = cartService;
        this.productService = productService;
        this.router = routerService;
        this.session = session;
        this.items = new ArrayList<>();
        cart= cartService.getCart(session.getId());

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
                System.out.println("Press[2] to change quantity.");
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
                        changeQuantity(scan);
                        break;
                    case "3":
                        clearScreen();
                        deleteItem(scan);
                        break;
                    case "x":
                        clearScreen();
                        router.navigate("/menu", scan, "");
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
                Product product = productService.getProduct(item.getProductId());

                System.out.println("Name: " + product.getProductName());

                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Price Per Item: " + product.getPricing());
                System.out.println("Total: " + item.getPrice());//cartService.calculatePrice(product ,item.getQuantity(), cart.get().getId()));
                System.out.println("---------------------------");

            }
            System.out.println("Amount spent: " + cartService.getAmountSpent(items));
            System.out.println("---------------------------");
        }

    }
        public void changeQuantity(Scanner scan) {
            clearScreen();
            String name;
            int quantity;
            Product product;
            boolean control = true;

            displayCart();

            while (control)
            {
                System.out.print("\nEnter the item to select: ");
                name = scan.nextLine();
                name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                product = productService.findByName(name);
                if (product == null) {
                    System.out.println("Invalid product. Please enter a valid product name.");
                    continue;
                }

                System.out.print("Change the amount: ");
                quantity = scan.nextInt();
                scan.nextLine();

                if (quantity == 0) {
                    System.out.println("Invalid quantity. Quantity cannot be 0.");
                    continue;
                }

                for (CartItem item : items) {
                    if (item.getProductId().equalsIgnoreCase(product.getProductId())) {
                        cartService.changeItemQuantity(product, quantity, cart.get().getId());
                        int newPrice =  cartService.calculatePrice(product ,quantity);
                        cartService.changeItemPrice(product, newPrice ,cart.get().getId());
                        break;
                    }
                }

                System.out.print("\nDo you want to change the quantity of another item? (y/n): ");
                String choice = scan.nextLine().toLowerCase();
                control = choice.equals("y");
            }

            start(scan);
        }

        public void deleteItem(Scanner scan)
        {
        clearScreen();
        String name;
        int quantity;
        Product product;
        boolean control = true;

        displayCart();

            while (control)
        {
            System.out.print("\nEnter the item to delete: ");
            name = scan.nextLine();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            product = productService.findByName(name);
            if (product == null) {
                System.out.println("Invalid product. Please enter a valid product name.");
                continue;
            }


            for (CartItem item : items) {
                if (item.getProductId().equalsIgnoreCase(product.getProductId())) {
                    cartService.deleteItem(item);
                    break;
                }
            }

            System.out.print("\nDo you want to delete another item? (y/n): ");
            String choice = scan.nextLine().toLowerCase();
            control = choice.equals("y");
        }

        start(scan);
    }

        private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
