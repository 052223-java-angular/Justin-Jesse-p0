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
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
//import com.Revature.eCommerce.dao.ProductDAO;
//@AllArgsConstructor
//@NoArgsConstructor
public class BrowseScreen implements IScreen {
    private Session session;
    //private Product product;
    private final RouterService router;
    private final ProductService productService;
    private final ReviewsAndRatingsService reviewsAndRatingsService;
    private final CartService cartService;
    
    public BrowseScreen(RouterService router, Session session, Product product, ProductService productService, ReviewsAndRatingsService reviewsAndRatingsService, CartService cartService)
    {
        this.router = router; 
        this.session = session;
        //this.product = product;
        this.productService = productService;
        this.reviewsAndRatingsService = reviewsAndRatingsService;
        this.cartService = cartService;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
              
                displayProducts();


                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "":
                    router.navigate("/menu", scan, "");
                        break exit;
                    case "2":
                        break exit;
                    case "3":
                        break exit;
                    case "4":
                        break exit;
                    case "5":
                        break exit;

                    case "R":
                        clearScreen();
                        router.navigate("/reviews", scan, "");
                            break exit;
                    case "x":
                    clearScreen();
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
    

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void displayProducts() {
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
                router.navigate("/reviews", scan, product.getProductId());
 
            }


            if (input.equalsIgnoreCase("A")) {
                clearScreen();
                exit = true;   
                addToCart(product);
 
            }

            if (input.equalsIgnoreCase("X")) {
                clearScreen();
                exit = true;
                router.navigate("/menu", scan, "");
            } 
             else {
                clearScreen();
                if (input.equalsIgnoreCase("B")) {
                    index--;
                } else {
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
    Optional<CartItem> optionalCartItem = cartItems.stream()
            .filter(item -> item.getProductId().equals(product.getProductId()))
            .findFirst();

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
    }

    System.out.print("\nPress enter to continue...");
    scan.nextLine();
}


}