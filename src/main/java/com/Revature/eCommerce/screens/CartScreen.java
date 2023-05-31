package com.Revature.eCommerce.screens;

import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.History;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.services.*;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.utils.custom_exceptions.NoItemsInCartException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Users can change quantity, delete or check out their cart items
 */
@AllArgsConstructor
public class CartScreen implements IScreen
    {
        private static final Logger logger = LogManager.getLogger(CartScreen.class);
        private final Session session;
        private final CartService cartService;
        private final ProductService productService;
        private final HistoryService historyService;
        private final RouterService router;
        
        ArrayList<CartItem> items;
        Optional<Cart> cart;
        Optional<History> history;
        private int amountSpent;
    public CartScreen(CartService cartService,ProductService productService,HistoryService historyService, RouterService router, Session session)
    {
        this.cartService = cartService;
        this.productService = productService;
        this.historyService = historyService;
        this.router = router;
        this.session = session;
        this.items = new ArrayList<>();
        cart= cartService.getCart(session.getId());
        history = historyService.findByUserId(session.getId());
  
    }

        /**
         * Start method to display the user cart and access features
         * @param scan
         */
    @Override
    public void start(Scanner scan)
    {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the cart screen " + session.getUsername() +"!");

                displayCart();//displays the cart
                System.out.println();
                System.out.println("Press[1] to checkout.");
                System.out.println("Press[2] to change quantity.");
                System.out.println("Press[3] to delete an item.");
                System.out.println("Press[X] Menu");


                System.out.print("\nEnter: ");
                input = scan.nextLine();
                switch (input.toLowerCase()) {
                    case "1":
                        logger.info("Navigating to checkout");
                        checkOut(scan);
                        break exit;
                    case "2":
                        clearScreen();
                        logger.info("Navigating to change quantity");
                        changeQuantity(scan);
                        break;
                    case "3":
                        clearScreen();
                        logger.info("Navigating to delete items");
                        deleteItem(scan);
                        break;
                    case "x":
                        clearScreen();
                        logger.info("Navigating to menu screen");
                        router.navigate("/menu", scan, "");
                        break exit;
                    default:
                        clearScreen();
                        logger.warn("User input was invalid for {}", input);
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }
        }
    }

        /**
         * This method displays the items in the users cart
         */
        public void displayCart() {
            logger.info("Displaying users cart items");
            int finalAmount = 0;
            items = cartService.getCartItems(cart.get().getId());//Gets items associated with users cart
            if (items.isEmpty()){
                logger.info("Users had nothing in their cart");
                System.out.println("\nThere is nothing in your cart!");
            } else {

                System.out.println("-------- Your Cart --------");

                for (int i = 0; i < items.size(); i++) {//iterates over the size of the array
                    CartItem item = items.get(i);//gets the item in the cart at the element
                    Product product = productService.getProduct(item.getProductId());

                    System.out.printf("%d. Name: %s\n", (i + 1), product.getProductName());
                    System.out.println("   Quantity: " + item.getQuantity());
                    System.out.println("   Price Per Item: " + product.getPricing());
                    System.out.println("   Total: " + item.getPrice());
                    System.out.println("---------------------------");
                    amountSpent = item.getQuantity() * item.getPrice();// gets the price of the item and total
                    finalAmount = amountSpent + finalAmount;//Calculates the final total
                }
                
                
                
                System.out.println("Amount spent: " + finalAmount);
                System.out.println("---------------------------");
            }
        }

        public void changeQuantity(Scanner scan) {
            logger.info("Navigated to change quantity feature");
            clearScreen();
            int itemNumber;// selected product
            int quantity;// quantity to change to
            boolean control = true;
            while (control) {
                if (items.isEmpty()) {
                    logger.info("User had no items in cart");
                    System.out.println("No items in the cart. Returning to the menu.");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    logger.info("Navigating to menu screen");
                    router.navigate("/menu", scan, "");
                }
                displayCart();
                System.out.print("\nEnter the item number to select or [x] to go back: ");
                String input = scan.nextLine();

                if (input.equalsIgnoreCase("x")) {
                    logger.info("Navigating to cart screen");
                    break;
                }

                try {
                    itemNumber = Integer.parseInt(input);

                    if (itemNumber < 1 || itemNumber > items.size()) {//user cant input 0 or larger than their cart size
                        logger.info("User input invalid number: {}",input );
                        System.out.println("Invalid item number. Please try again.");
                        continue;
                    }

                    System.out.print("Enter the new quantity: ");
                    quantity = scan.nextInt();
                    scan.nextLine();

                    if (quantity <= 0) {
                        logger.warn("Invalid quantity for item {}:", quantity);
                        System.out.println("Invalid quantity. Quantity must be greater than 0.");
                        continue;
                    }

                    CartItem item = items.get(itemNumber - 1);
                    Product product = productService.getProduct(item.getProductId());

                    cartService.changeItemQuantity(product, quantity, cart.get().getId());// changes the quantity of item in cart in database
                    int newPrice =  cartService.calculatePrice(product,quantity);// calculates new price
                    cartService.changeItemPrice(product, newPrice ,cart.get().getId());// changes the price in DB
                    System.out.println("Quantity updated successfully!");
                    logger.info("Quanitity: {} changed for product{}:",quantity,product);

                    System.out.print("\nDo you want to change the quantity of another item? (y/n): ");
                    input = scan.nextLine().toLowerCase();
                    control = input.equals("y");
                    logger.info("Restarting change quantity process");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid item number or 'x' to go back.");
                }
            }
            logger.info("Finished changing item quantity");
            logger.info("Navigating to cart screen");
            start(scan);
        }

        /**
         * User can check out the items in their cart
         * @param scan - user input
         */
        public void checkOut (Scanner scan)
        {
            String input;
            String cc;
            String exp;
            String cvv;
            boolean control = true;

            while (control) {
                clearScreen();
                if (items.isEmpty()) {
                    logger.warn("No items to checkout");
                    System.out.println("No items in the cart. Returning to the menu.");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    logger.info("Navigating to menu");
                    router.navigate("/menu", scan, "");
                }
                    System.out.print("\nAre you sure you want to check out? (y/n) ");
                    input = scan.nextLine();
                    displayCart();
                    logger.info("Starting checkout process");
                    if (input.equalsIgnoreCase("y")) {
                        System.out.print("Enter CC number: ");
                        cc = scan.nextLine();
                        if (new PaymentService().isValidCC(cc)) {
                            logger.warn("Invalid CC entered");
                            System.out.println("Invalid credit card number. Please try again.");
                            continue;
                        }
                        System.out.print("Enter Exp date (mm/yy): ");
                        exp = scan.nextLine();
                        if (!new PaymentService().isValidExp(exp)) {
                            logger.warn("Invalid EXP date entered");
                            System.out.println("Invalid expiration date. Please try again.");
                            continue;
                        }
                        System.out.print("Enter CVV: ");
                        cvv = scan.nextLine();
                        if (!new PaymentService().isValidCVV(cvv)) {
                            logger.warn("Invalid CVV entered");
                            System.out.println("Invalid CVV. Please try again.");
                            continue;
                        }
                        control = false;
   
                        historyService.createOrder(items, history.get().getId());//creates history item
                        cartService.deleteCart(cart.get().getId());// deletes cart
                        cartService.newCart(session.getId());//Gives the user a new cart
                        System.out.print("Order submitted!");
                        logger.info("Successfully checked out cart items");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        logger.info("Navigating to menu");
                        router.navigate("/menu", scan, "");
                    } else if (input.equalsIgnoreCase("n")) {
                        start(scan);
                        break;
                    } else {
                        logger.info("Canceled checkout process");
                        logger.info("Navigating to cart screen");
                        System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    }
                }
            }

        /**
         * user can delete an item from their cart
         * @param scan- user input
         */
        public void deleteItem(Scanner scan) {
            clearScreen();
            logger.info("Starting item deletion process");
            int selection;// user selects the item to delete
            boolean control = true;

            while (control) {
                if (items.isEmpty()) {
                    logger.warn("No items in cart to delete");
                    System.out.println("No items in the cart. Returning to the menu.");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    logger.info("Navigating to menu");
                    router.navigate("/menu", scan, "");
                }
                displayCart();
                System.out.print("\nEnter the number of the item to delete or [x] to go back: ");
                logger.info("Selecting product to delete");
                String input = scan.nextLine();
                if (input.equalsIgnoreCase("x")) {
                    logger.info("Navigating to menu");
                    break;
                }

                try {
                    selection = Integer.parseInt(input);
                    if (selection < 1 || selection > items.size()) {// user cant select 0 or a number larger than their item in cart
                        logger.warn("User input was invalid");
                        System.out.println("Invalid selection. Please enter a valid number.");
                        continue;
                    }

                    CartItem item = items.get(selection - 1);//Decrements to get the users selected product
                    cartService.deleteItem(item);//deletes an item from users cart
                    System.out.println("Item deleted.");
                    logger.info("User successfully deleted item from cart");

                } catch (NumberFormatException e) {
                    logger.info("User input was invalid for selecting an item");
                    System.out.println("Invalid input. Please enter a valid number.");
                    continue;
                }

                System.out.print("\nDo you want to delete another item? (y/n): ");
                String choice = scan.nextLine().toLowerCase();
                control = choice.equals("y");
                logger.info("User selected to delete another item");
            }
            logger.info("Ending delete item process");
            start(scan);
        }


        private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
