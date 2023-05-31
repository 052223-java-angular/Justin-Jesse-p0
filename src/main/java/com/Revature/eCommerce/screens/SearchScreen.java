package com.Revature.eCommerce.screens;
import java.util.Scanner;
import java.util.Optional;
import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import java.util.UUID;
import java.util.ArrayList;
import java.util.InputMismatchException;

import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.models.Category;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.services.ProductService;
import com.Revature.eCommerce.services.CategoryService;
import com.Revature.eCommerce.services.CartService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchScreen implements IScreen {
    private static Logger logger = LogManager.getLogger(SearchScreen.class);
    private Session session;
    private final RouterService router;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;
    
    public SearchScreen(RouterService router, Session session, ProductService productService, CategoryService categoryService, CartService cartService)
    {
        this.router = router; 
        this.session = session;
        this.productService = productService;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }

    /**
     * Prompts the user to search for product by name, category, price or return to menu
     * @param scan-user input
     */
    @Override
    public void start(Scanner scan) {
        String input = "";
        logger.info("Navigated to product search screen");
        exit: {
            while (true) {
                clearScreen();
                System.out.println("------------------Product Search--------------------" );
                System.out.println("\nPlease select your option:\n");
                System.out.println("Press [1] to Search By Products Name");
                System.out.println("Press [2] to Search By Category");
                System.out.println("Press [3] to Search By Price");
                System.out.println("Press [X] to return to Menu");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        logger.info("Displaying products by product name");
                    DisplaySearchedProductName(scan);
                        break exit;
                    case "2":
                        logger.info("Displaying products by category");
                    CategoryDisplay(scan);
                        break exit;
                    case "3":
                        logger.info("Displaying products by price");
                        DisplaySearchedProductPricing(scan);
                        break exit;

                    case "x":
                    clearScreen();
                    logger.info("Exiting product search screen");
                    router.navigate("/menu", scan, "");
                        break exit;

                    default:
                        clearScreen();
                        logger.warn("Invalid option user input: {}", input);
                        System.out.println("Invalid Option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break exit;
                }
            }
        }
    }
    

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Displays the product by name
     * @param scan-usre input
     */
    public void DisplaySearchedProductName(Scanner scan) {
        clearScreen();
        System.out.println("Enter Product Name: ");
        String name = scan.nextLine();
        logger.info("User entered product for: {} ",name);
        List<Product> productList = productService.findProductByName(name);//gets products from DB
        int listSize = productList.size();
        boolean exit = false; 
        int index = 0;
        if (listSize == 0) {
            logger.warn("No products were found for {}:", name);
            System.out.println("No products found.");
            System.out.println("Press Enter to return to Menu Screen.");
            scan.nextLine();
            clearScreen();
            logger.info("Navigating to menu screen");
            router.navigate("/menu", scan, "");
            return;
        }

        while(!exit){

        Product product = productList.get(index);//Displays the product
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
            System.out.println("Press [R] to View Reviews.");
            System.out.println("Press [X] to back to Menu Screen.");
            System.out.print("");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("R")) {
                clearScreen();
                logger.info("Navigating to product reviews");
                exit = true;   
                router.navigate("/reviews", scan, product.getProductId());
 
            }
            if (input.equalsIgnoreCase("A")) {
                clearScreen();
                exit = true;
                logger.info("Starting add to cart process");
                addToCart(product);
            }
            if (input.equalsIgnoreCase("X")){
                clearScreen();
                logger.info("Navigating to menu");
                exit = true;
                router.navigate("/menu", scan, "");
                scanner.close();

            }
            else{

                clearScreen();
            if (input.equalsIgnoreCase("B")){
                logger.info("User viewing previous product");
                index--;
            }
            else{
                logger.info("User viewing next product");
                index++;
            }
                if (index>=listSize){
                    index = 0;
                }
                if(index < 0){
                    index = listSize - 1;
                }
            }
        }
    }

    /**
     * Displays the categories for the user to select
     * @param scan
     */
    public void CategoryDisplay(Scanner scan) {
        clearScreen();
        logger.info("Displaying category for user to select");
        List<Category> categoryList = categoryService.getAllCategories();
        int listSize = categoryList.size();

        int index = 0;

        System.out.println("Category List:");
        System.out.println("-----------------------------");

           for (int i = 0 ; i < listSize; i++){//Displays the categories for user to select
            Category category = categoryList.get(i);
            System.out.println("Category " + category.getCategory_ID() + " " + category.getCategory_Name());

           }

            System.out.println("-----------------------------");


            System.out.println("\n \nEnter the Category Number");
            System.out.println("Press [X] to back to Menu Screen.");
            System.out.print("");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
           

            if (input.equalsIgnoreCase("X")){
                clearScreen();
                logger.info("Navigating to menu");
                router.navigate("/menu", scan, "");
                scanner.close();

            }
           
            try {
                index = Integer.parseInt(input);

            } 
            catch (NumberFormatException e) {
                logger.warn("User input is invalid for {}", input);
                System.out.println("Invalid Input.");
                System.out.println("Press Enter to return to Menu Screen.");
                scan.nextLine();
                clearScreen();
                logger.info("Navigating to menu screen");
                router.navigate("/menu", scan, "");
            }
            
            if (index != 0 && index <= listSize ){
               

                clearScreen();
                DisplaySearchedProductCategory(Integer.toString(index));
           
            }
            

            else {
                logger.warn("User invalid input for: {}", input);
            System.out.println("Invalid Input");
            System.out.println("Press Enter to return to Menu Screen.");
            scan.nextLine();
            clearScreen();
            logger.info("Navigating to menu screen");
            router.navigate("/menu", scan, "");
            }
        }

    /**
     * Displays the products by category
     * @param category
     */
    public void DisplaySearchedProductCategory(String category) {
        logger.info("Displaying products by category: {}", category);
        Scanner scan = new Scanner(System.in);
        clearScreen();
        List<Product> productList = productService.findProductByCategory(category);
        int listSize = productList.size();
        boolean exit = false; 
        int index = 0;
        if (listSize == 0) {
            System.out.println("No products found.");
            System.out.println("Press Enter to return to Menu Screen.");
            scan.nextLine();
            clearScreen();
            router.navigate("/menu", scan, "");
            return;
        }

        while(!exit){

        Product product = productList.get(index);
            System.out.println("Product List:");
            System.out.println("-----------------------------");
            System.out.println("Username: " + session.getUsername() + "\n");
            //System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Category ID: " + product.getCategoryId());
            System.out.println("Pricing: $" + product.getPricing());
            System.out.println("Description: " + product.getDescription());
            System.out.println("-----------------------------");


            System.out.println("\n \nPress Enter to go to next page.");
            System.out.println("Press [B] to go back.");
            System.out.println("Press [A] to go add product to cart.");
            System.out.println("Press [R] to View Reviews.");
            System.out.println("Press [X] to back to Menu Screen.");
            System.out.print("");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("R")) {
                clearScreen();
                exit = true;
                logger.info("Navigating to reviews screen");
                router.navigate("/reviews", scan, product.getProductId());
 
            }
            if (input.equalsIgnoreCase("A")) {
                clearScreen();
                exit = true;   
                addToCart(product);
            }
            if (input.equalsIgnoreCase("X")){
                clearScreen();
                exit = true;
                logger.info("Navigating to menu screen");
                router.navigate("/menu", scan, "");
                scanner.close();

            }
            else{

                clearScreen();
            if (input.equalsIgnoreCase("B")){
                logger.info("User viewing previous product");
                index--;
            }
            else{
                logger.info("User viewing next product");
                index++;
            }
                if (index>=listSize){
                    index = 0;
                }
                if(index < 0){
                    index = listSize - 1;
                }
            }
        }
    }

    /**
     * Displays the products by the price range Min - Max
     * @param scan - user input
     */
    public void DisplaySearchedProductPricing(Scanner scan) {
        int min = 0;
        int max = 0;
        
        clearScreen();
        try{
        System.out.println("Enter Minimum Value ");
        min = scan.nextInt();
        scan.nextLine();
        clearScreen();
        }
        catch(InputMismatchException e){
         System.out.println("Input must be a number");
         System.out.println("Press anything to try again...");
         scan.nextLine();
         DisplaySearchedProductPricing(scan);
        }
        try{
        System.out.println("Enter Maximum Value ");
        max = scan.nextInt();
        scan.nextLine();
        clearScreen();
        }

        catch(InputMismatchException e){
            System.out.println("Input must be a number");
            System.out.println("Press anything to try again...");
            scan.nextLine();
            DisplaySearchedProductPricing(scan);
        }
        logger.debug("Displaying products by pricing min{}, max{}", min,max);
        List<Product> productList = productService.findProductByPricing(min, max);
        int listSize = productList.size();
        boolean exit = false;
        int index = 0;
        
        if (listSize == 0) {
            System.out.println("No products found.");
            System.out.println("Press Enter to return to Menu Screen.");
            scan.nextLine();
            clearScreen();
            logger.warn("No products found, Navigating back to menu");
            router.navigate("/menu", scan, "");
            return;
        }
    
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
    
            System.out.println("\n \nPress Enter to go to the next page.");
            System.out.println("Press [B] to go back.");
            System.out.println("Press [A] to add the product to the cart.");
            System.out.println("Press [R] to View Reviews.");
            System.out.println("Press [X] to go back to the Menu Screen.");
            System.out.print("");
            
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("R")) {
                clearScreen();
                exit = true;
                logger.info("Navigating to reviews");
                router.navigate("/reviews", scan, product.getProductId());
 
            }

            if (input.equalsIgnoreCase("A")) {
                clearScreen();
                exit = true;
                logger.info("Starting add to product process");
                addToCart(product);
            }
            if (input.equalsIgnoreCase("X")) {
                clearScreen();
                exit = true;
                logger.info("Navigating to menu screen");
                router.navigate("/menu", scan, "");
                scanner.close();
                break; // Exit the while loop
            } else {
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
     * User can add product to cart
     * @param product - the product to add
     */
    private void addToCart(Product product) {
        Scanner scan = new Scanner(System.in);
        logger.info("Add product to cart process started");
        Optional<Cart> optionalCart = cartService.getCart(session.getId());
        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
    
            cart = new Cart(UUID.randomUUID().toString(), session.getId(), 0);
            cartService.newCart(cart.getId());// creates a new cart if cart is not present
        }
    
        ArrayList<CartItem> cartItems = cartService.getCartItems(cart.getId());
        Optional<CartItem> optionalCartItem = cartItems.stream().filter(item -> item.getProductId().equals(product.getProductId())).findFirst();
    
        if (optionalCartItem.isPresent()) {
            CartItem existingItem = optionalCartItem.get();
            int newQuantity = existingItem.getQuantity() + 1;
            cartService.changeItemQuantity(product, newQuantity, cart.getId());
            System.out.println("Quantity updated in the cart!");
        } else {
    
            CartItem newItem = new CartItem(UUID.randomUUID().toString(), product.getProductId(),
                    cart.getId(), 1, product.getPricing());
            cartService.setCart(product, newItem, cart);
            System.out.println("Item added to the cart!");
            logger.info("User successfully added product to {} cart : ", product);
        }
    
        System.out.print("\nPress enter to return to browse screen");
        logger.info("Navigating to browse screen");
        scan.nextLine();
        router.navigate("/browse", scan, "");
    }
}


