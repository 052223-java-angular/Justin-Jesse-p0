package com.Revature.eCommerce.screens;

import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
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
                        checkOut(scan);
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


        public void displayCart() {
            items = cartService.getCartItems(cart.get().getId());
            if (items.isEmpty()){
                System.out.println("\nThere is nothing in your cart!");
            } else {

                System.out.println("-------- Your Cart --------");

                for (int i = 0; i < items.size(); i++) {
                    CartItem item = items.get(i);
                    Product product = productService.getProduct(item.getProductId());

                    System.out.printf("%d. Name: %s\n", (i + 1), product.getProductName());
                    System.out.println("   Quantity: " + item.getQuantity());
                    System.out.println("   Price Per Item: " + product.getPricing());
                    System.out.println("   Total: " + item.getPrice());
                    System.out.println("---------------------------");
                }

                amountSpent = cartService.getAmountSpent(items);
                System.out.println("Amount spent: " + amountSpent);
                System.out.println("---------------------------");
            }
        }

        public void changeQuantity(Scanner scan) {
            clearScreen();
            int itemNumber;
            int quantity;
            boolean control = true;
            while (control) {
                if (items.isEmpty()) {
                    System.out.println("No items in the cart. Returning to the menu.");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    router.navigate("/menu", scan);
                }
                displayCart();
                System.out.print("\nEnter the item number to select or [x] to go back: ");
                String input = scan.nextLine();

                if (input.equalsIgnoreCase("x")) {
                    break;
                }

                try {
                    itemNumber = Integer.parseInt(input);

                    if (itemNumber < 1 || itemNumber > items.size()) {
                        System.out.println("Invalid item number. Please try again.");
                        continue;
                    }

                    System.out.print("Enter the new quantity: ");
                    quantity = scan.nextInt();
                    scan.nextLine();

                    if (quantity <= 0) {
                        System.out.println("Invalid quantity. Quantity must be greater than 0.");
                        continue;
                    }

                    CartItem item = items.get(itemNumber - 1);
                    Product product = productService.getProduct(item.getProductId());

                    cartService.changeItemQuantity(product, quantity, cart.get().getId());
                    int newPrice =  cartService.calculatePrice(product,quantity);
                    cartService.changeItemPrice(product, newPrice ,cart.get().getId());
                    System.out.println("Quantity updated successfully!");

                    System.out.print("\nDo you want to change the quantity of another item? (y/n): ");
                    input = scan.nextLine().toLowerCase();
                    control = input.equals("y");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid item number or 'x' to go back.");
                }
            }

            start(scan);
        }


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
                    System.out.println("No items in the cart. Returning to the menu.");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    router.navigate("/menu", scan);
                }
                    System.out.print("\nAre you sure you want to check out? (y/n) ");
                    input = scan.nextLine();
                    displayCart();
                    if (input.equalsIgnoreCase("y")) {
                        System.out.print("Enter CC number: ");
                        cc = scan.nextLine();
                        if (new PaymentService().isValidCC(cc)) {
                            System.out.println("Invalid credit card number. Please try again.");
                            continue;
                        }
                        System.out.print("Enter Exp date (mm/yy): ");
                        exp = scan.nextLine();
                        if (!new PaymentService().isValidExp(exp)) {
                            System.out.println("Invalid expiration date. Please try again.");
                            continue;
                        }
                        System.out.print("Enter CVV: ");
                        cvv = scan.nextLine();
                        if (!new PaymentService().isValidCVV(cvv)) {
                            System.out.println("Invalid CVV. Please try again.");
                            continue;
                        }
                        control = false;
                        historyService.createOrder(items);
                        cartService.deleteCart(cart.get().getId());
                        cartService.newCart(session.getId());
                        System.out.print("Order submitted!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        router.navigate("/menu", scan);
                    } else if (input.equalsIgnoreCase("n")) {
                        start(scan);
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    }
                }
            }


        public void deleteItem(Scanner scan) {
            clearScreen();
            int selection;
            boolean control = true;

            while (control) {
                if (items.isEmpty()) {
                    System.out.println("No items in the cart. Returning to the menu.");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    router.navigate("/menu", scan);
                }
                displayCart();
                System.out.print("\nEnter the number of the item to delete or [x] to go back: ");
                String input = scan.nextLine();
                if (input.equalsIgnoreCase("x")) {
                    break;
                }

                try {
                    selection = Integer.parseInt(input);
                    if (selection < 1 || selection > items.size()) {
                        System.out.println("Invalid selection. Please enter a valid number.");
                        continue;
                    }

                    CartItem item = items.get(selection - 1);
                    cartService.deleteItem(item);
                    System.out.println("Item deleted.");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    continue;
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
