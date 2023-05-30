package com.Revature.eCommerce.screens;
import java.util.Scanner;

import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.services.RouterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MenuScreen implements IScreen {
    private static Logger logger = LogManager.getLogger(MenuScreen.class);
    private Session session;
    private final RouterService router;

    public MenuScreen(RouterService router, Session session)
    {
        this.router = router;
        this.session = session;

    }

    /**
     * Displays menu for the user to select for different features
     * @param scan
     */
    @Override
    public void start(Scanner scan) {
        logger.info("Navigated to Menu");
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the menu screen " + session.getUsername() + "!");
                System.out.println("\nPlease select your option:\n");
                System.out.println("Press [1] to Browse Products.");
                System.out.println("Press [2] to Search Products.");
                System.out.println("Press [3] to View Cart.");
                System.out.println("Press [4] to View History");
                System.out.println("Press [X] to exit the application.");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        router.navigate("/browse", scan, "");
                        break exit;
                    case "2":
                        router.navigate("/search", scan, "");
                        break exit;
                    case "3":
                        router.navigate("/cart", scan, "");
                        break exit;
                    case "4":
                         router.navigate("/history", scan, "");
                        break exit;
                    case "x":
                    clearScreen();
                    router.navigate("/home", scan, "");
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
}