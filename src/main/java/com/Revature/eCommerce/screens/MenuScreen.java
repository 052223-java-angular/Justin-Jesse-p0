package com.Revature.eCommerce.screens;
import java.util.Scanner;

import com.Revature.eCommerce.dao.CartDAO;
import java.util.UUID;
import com.Revature.eCommerce.dao.HistoryDAO;
import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.History;
import com.Revature.eCommerce.services.CartService;
import com.Revature.eCommerce.services.HistoryService;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.services.RouterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Menu screen that lets users navigate to different screens
 */
public class MenuScreen implements IScreen {
    private static Logger logger = LogManager.getLogger(MenuScreen.class);
    private Session session;
    private HistoryService historyService;
    private final RouterService router;

    public MenuScreen(RouterService router, Session session, HistoryService historyService)
    {
        this.router = router;
        this.session = session;

        this.historyService = historyService;
    }

    /**
     * Displays menu for the user to select for different features
     * @param scan - user input
     */
    @Override
    public void start(Scanner scan) {
        clearScreen();
        doesUserHaveCart(session.getId());
        doesUserHaveHistory(session.getId());
        logger.info("Navigated to Menu");
        String input = "";

        exit: {
            while (true) {
                //clearScreen();
                System.out.println("Welcome to the menu screen " + session.getUsername() + "!");
                System.out.println("\nPlease select your option:\n");
                System.out.println("Press [1] to Browse Products.");
                System.out.println("Press [2] to Search Products.");
                System.out.println("Press [3] to View Cart.");
                System.out.println("Press [4] to View History");
                System.out.println("Press [X] to logout the application.");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        logger.info("Navigating to browse screen");
                        router.navigate("/browse", scan, "");
                        break exit;
                    case "2":
                        logger.info("Navigating to search screen");
                        router.navigate("/search", scan, "");
                        break exit;
                    case "3":
                        logger.info("Navigating to cart screen");
                        router.navigate("/cart", scan, "");
                        break exit;
                    case "4":
                        logger.info("Navigating to history screen");
                        router.navigate("/history", scan, "");
                        break exit;
                    case "x":
                    clearScreen();
                        logger.info("Navigating to home screen");
                        router.navigate("/home", scan, "");
                        break exit; 
                    default:
                        clearScreen();
                        logger.warn("User invalid input for: ", input);
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
     * if user does not have cart id, one is assigned
     * @param userId - Id for user
     */
    private void doesUserHaveCart(String userId)
    {
        if (new CartService(new CartDAO()).doesUserHaveCart(userId))
        {
            System.out.println("Cart Already There");
        }
        else{
            new CartService(new CartDAO()).createCart(userId);
        }
    }

    /**
     * if user does not have history id, one is assigned
     * @param userId - Id for user
     */
    private void doesUserHaveHistory(String userId)
    {
        historyService = new HistoryService (new HistoryDAO(), new History());
        if (historyService.doesUserHaveHistory(userId))
        {
            System.out.println("History Already There");
        }
        else{
            System.out.println("History Being Made");
            String historyId= UUID.randomUUID().toString();
            System.out.println(session.getId() + " " + historyId + " ");
            historyService.createHistory(session.getId(), historyId, 1);
            
            
        }
    }
}