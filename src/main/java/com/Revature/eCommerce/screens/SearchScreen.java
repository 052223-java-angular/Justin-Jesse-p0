package com.Revature.eCommerce.screens;
import java.util.Scanner;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.services.RouterService;
import java.util.List;
import com.Revature.eCommerce.dao.ProductDAO;
//@AllArgsConstructor
//@NoArgsConstructor
public class SearchScreen implements IScreen {
    private Session session;
    private Product product;
    private final RouterService router;
    private final ProductDAO productDAO;
    
    public SearchScreen(RouterService router, Session session, ProductDAO productDAO)
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
                System.out.println("------------------Product Search--------------------" );
                System.out.println("\nPlease select your option:\n");
                System.out.println("Press [1] to Search By Products Name");
                System.out.println("Press [2] to Search By Category");
                System.out.println("Press [3] to Search By Price");
                System.out.println("Press [X] to return to menu");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        break exit;
                    case "2":
                        break exit;
                    case "3":
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
    public void DisplaySearchedProductName(Scanner scan) {
    

        
    }
}

