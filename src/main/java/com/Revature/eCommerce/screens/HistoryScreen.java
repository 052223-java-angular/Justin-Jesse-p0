package com.Revature.eCommerce.screens;

import com.Revature.eCommerce.dao.ProductDAO;
import com.Revature.eCommerce.models.History;
import com.Revature.eCommerce.models.HistoryItem;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.services.HistoryService;
import com.Revature.eCommerce.services.ProductService;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * This class displays the user order history
 */
public class HistoryScreen implements IScreen {
    private static Logger logger = (Logger) LogManager.getLogger(HistoryScreen.class);
    private Session session;
    private final RouterService router;
    private final HistoryService historyService;
    Optional<History> history;
    
    
    public HistoryScreen(RouterService router, Session session, HistoryService historyService)
    {
        this.router = router; 
        this.session = session;
        this.historyService = historyService;
        history = historyService.findByUserId(session.getId());
    }

    /**
     * Start method of the history screen to display previous orders from user
     * @param scan-user input
     */
    @Override
    public void start(Scanner scan) {
        String input = "";

        logger.info("Navigated to order history screen");
        exit: {
            while (true) {
                clearScreen();
              
                displayOrders();


                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "":
                        logger.info("Navigating to menu screen");
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

                    case "r":
                        clearScreen();
                        router.navigate("/reviews", scan, "");
                            break exit;
                    case "x":
                    clearScreen();
                    logger.info("Navigating to menu");
                        break exit;
                    default:
                        clearScreen();
                        logger.warn("Invalid input for user");
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        logger.info("restarting order history");
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
     * Displays order history of user
     */
    public void displayOrders() {
        logger.info("Displaying order history");

        List<HistoryItem> historyList = historyService.getAllHistoryById(history.get().getId());// gets a list of history items linked to user
        int listSize = historyList.size();
        boolean exit = false; 
        int index = 0;
        Scanner scan = new Scanner(System.in);
        
        while (!exit) {
            try{
            HistoryItem history = historyList.get(index);// displays the item of that a customer bought
            
            System.out.println("History Item Purchases");
            System.out.println("-----------------------------");
            System.out.println("Username: " + session.getUsername() + "\n");

            System.out.println("Product Name: " + getProduct(history.getProductId()).getProductName());
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
                logger.info("Navigating to menu");
                router.navigate("/menu", scan, "");
            } 
             else {
                clearScreen();
                if (input.equalsIgnoreCase("B")) {
                    logger.info("User viewing previous product {}");
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
            catch(IndexOutOfBoundsException e){
                logger.warn("No order history to display");
                System.out.println("No Purchase History!\n");
                System.out.println("Press Anything To Return to Menu");
                scan.nextLine();
                logger.info("Navigating to menu");
                router.navigate("/menu", scan, "");

            }
        }

    }

    public Product getProduct(String product_id)
    {
        return new ProductService(new ProductDAO()).getProduct(product_id);
    }
}




