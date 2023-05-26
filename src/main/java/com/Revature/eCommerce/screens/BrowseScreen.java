package com.Revature.eCommerce.screens;
import java.util.Scanner;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.services.RouterService;
import java.util.List;
import com.Revature.eCommerce.dao.ProductDAO;
//@AllArgsConstructor
//@NoArgsConstructor
public class BrowseScreen implements IScreen {
    private Session session;
    private Product product;
    private final RouterService router;
    private final ProductDAO productDAO;
    
    public BrowseScreen(RouterService router, Session session, ProductDAO productDAO)
    {
        this.router = router; 
        this.session = session;
        this.productDAO = productDAO;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Username: " + session.getUsername());
                displayProducts();

                System.out.println("\nPress Enter to go to next page.");
                System.out.println("Press [B] to go back.");
                System.out.println("Press [A] to go add product to cart.");
                System.out.println("Press [X] to back to Menu Screen.");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "":
                    router.navigate("/menu", scan);
                        break exit;
                    case "2":
                        break exit;
                    case "3":
                        break exit;
                    case "4":
                        break exit;
                    case "5":
                        break exit;
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
    

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void displayProducts() {
        List<Product> productList = productDAO.getAllProducts();

        System.out.println("Product List:");
        System.out.println("-----------------------------");
        for (Product product : productList) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Category ID: " + product.getCategoryId());
            System.out.println("Pricing: " + product.getPricing());
            System.out.println("Description: " + product.getDescription());
            System.out.println("-----------------------------");
        }
    }
}

