package com.Revature.eCommerce.screens;
import java.util.Scanner;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.models.History;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.services.ProductService;
import com.Revature.eCommerce.services.HistoryService;
import com.Revature.eCommerce.models.HistoryItem;
import com.Revature.eCommerce.services.ReviewsAndRatingsService;
import com.Revature.eCommerce.screens.ReviewsAndRatingsScreen;
import java.util.Optional;
import java.util.List;
//import com.Revature.eCommerce.dao.ProductDAO;
//@AllArgsConstructor
//@NoArgsConstructor
public class HistoryScreen implements IScreen {
    private Session session;
    //private Product product;
    private final RouterService router;
    private final HistoryService historyService;
    private final History history;

 
    
    public HistoryScreen(RouterService router, Session session, HistoryService historyService, History history)
    {
        this.router = router; 
        this.session = session;
        //this.product = product;
        this.historyService = historyService;
        this.history = history;

    }

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
              
                displayOrders();


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
    public void displayOrders() {
        List<HistoryItem> historyList = historyService.getAllHistoryById(history.getId());
        int listSize = historyList.size();
        boolean exit = false; 
        int index = 0;
        Scanner scan = new Scanner(System.in);
        
        while (!exit) {
            HistoryItem history = historyList.get(index);
            //ProductService product = product.getProduct(product.getProduct(history.getProductId()));
            System.out.println("History Item Purchases");
            System.out.println("-----------------------------");
            System.out.println("Username: " + session.getUsername() + "\n");
            //System.out.println("History ID:" + history.getHistoryId());
            System.out.println("Product ID:: " + history.getProductId());
            System.out.println("Pricing: $" + history.getPrice());
            System.out.println("Quantity: " + history.getQuantity());
            System.out.println("-----------------------------");
    
            System.out.println("\n \nPress Enter to go to next page.");
            System.out.println("Press [B] to go back.");
            System.out.println("Press [X] to back to Menu Screen.");
            System.out.print("");
    
            String input = scan.nextLine().trim();
            

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
}

