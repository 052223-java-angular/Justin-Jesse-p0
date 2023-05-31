package com.Revature.eCommerce.screens;
import java.util.Scanner;
import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.services.ProductService;
import com.Revature.eCommerce.services.CartService;
import com.Revature.eCommerce.services.ReviewsAndRatingsService;
import com.Revature.eCommerce.screens.ReviewsAndRatingsScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class BrowseScreen implements IScreen {
    private static Logger logger = LogManager.getLogger(BrowseScreen.class);

    private Session session;
    private final RouterService router;
    private final ProductService productService;
    private final ReviewsAndRatingsService reviewsAndRatingsService;
    private final CartService cartService;
    
    public BrowseScreen(RouterService router, Session session, Product product, ProductService productService, ReviewsAndRatingsService reviewsAndRatingsService, CartService cartService)
    {
        this.router = router;
        this.session = session;
        this.productService = productService;
        this.reviewsAndRatingsService = reviewsAndRatingsService;
        this.cartService = cartService;
    }

    /**
     * Starts the browsing screen
     * @param scan-user input
     */
    @Override
    public void start(Scanner scan) {
        logger.info("Navigated to Browser screen");
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                logger.info("Displaying Products");
                displayProducts();



                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "":
                        logger.info("Navigating to menu");
                    router.navigate("/menu", scan, "");
                        break exit;
                    case "R":
                        clearScreen();
                        logger.info("Navigating to reviews");
                        router.navigate("/reviews", scan, "");
                            break exit;
                    case "x":
                    clearScreen();
                        break exit;
                    default:
                        clearScreen();
                        logger.warn("Invlaid input from user: {}", input);
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }
        }
    }
    

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Displays the product in the store
     */
    public void displayProducts() {
        logger.info("Displayed products");
        List<Product> productList = productService.getAllProducts();
        int listSize = productList.size();
        boolean exit = false; 
        int index = 0;
        Scanner scan = new Scanner(System.in);
    
        while (!exit) {
            Product product = productList.get(index);
            System.out.println("Product List:");
            System.out.println("-----------------------------");
            System.out.println("Username: " + session.getUsername() + "\n");
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Category ID: " + product.getCategoryId());
            System.out.println("Pricing: $" + product.getPricing());
            System.out.println("Description: " + product.getDescription());
            System.out.println("-----------------------------");
    
            System.out.println("\n \nPress Enter to go to next page.");
            System.out.println("Press [B] to go back.");
            System.out.println("Press [A] to go add product to cart.");
            System.out.println("Press [R] to View Reviews");
            System.out.println("Press [X] to back to Menu Screen.");
            System.out.print("");
    
            String input = scan.nextLine().trim();
            if (input.equalsIgnoreCase("R")) {
                clearScreen();
                exit = true;
                logger.info("Navigating to reviews");
                router.navigate("/reviews", scan, product.getProductId());
 
            }


            if (input.equalsIgnoreCase("A")) {
                clearScreen();
                exit = true;
                logger.info("User added to cart: {}", product.getProductName());
                addToCart(product);
 
            }

            if (input.equalsIgnoreCase("X")) {
                clearScreen();
                exit = true;
                logger.info("Navigating to Menu");
                router.navigate("/menu", scan, "");
            } 
             else {
                clearScreen();
                if (input.equalsIgnoreCase("B")) {
                    logger.info("User viewing previous product");
                    index--;
                } else {
                    logger.info("User viewing next product");
                    index++;
                }
                if (index >= listSize) {
                    index = 0;
                }
                if (index < 0) {
                    index = listSize - 1;
                }
            }
        }
    }

    /**
     * Adds a product to a users cart
     * @param product - the product to add
     */
    private void addToCart(Product product) {
    Scanner scan = new Scanner(System.in);

    Optional<Cart> optionalCart = cartService.getCart(session.getId());
    Cart cart;
    if (optionalCart.isPresent()) {
        cart = optionalCart.get();
    } else {

        cart = new Cart(UUID.randomUUID().toString(), session.getId(), 0);
        cartService.newCart(cart.getId());
    }

    ArrayList<CartItem> cartItems = cartService.getCartItems(cart.getId());
    Optional<CartItem> optionalCartItem = cartItems.stream().filter(item -> item.getProductId().equals(product.getProductId())).findFirst();

    if (optionalCartItem.isPresent()) {
        CartItem existingItem = optionalCartItem.get();
        int newQuantity = existingItem.getQuantity() + 1;
        cartService.changeItemQuantity(product, newQuantity, cart.getId());
        logger.info("User added another product for: {}", product.getProductName());
        System.out.println("Quantity updated in the cart!");
    } else {

        CartItem newItem = new CartItem(UUID.randomUUID().toString(), product.getProductId(),
                cart.getId(), 1, product.getPricing());
        cartService.setCart(product, newItem, cart);
        logger.info("User added to new item to cart: {}", product);
        System.out.println("Item added to the cart!");
    }

    System.out.print("\nPress enter to return to browse screen");
    scan.nextLine();
    logger.info("Navigating to browse screen");
    router.navigate("/browse", scan, "");
}


}