package com.Revature.eCommerce.screens;
import java.util.Scanner;
import com.Revature.eCommerce.services.RouterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Home screen where users can login,register or exit application
 */
public class HomeScreen implements IScreen{
    private final RouterService router;
    private static final Logger logger = LogManager.getLogger(HomeScreen.class);


    public HomeScreen(RouterService router) {
        this.router = router;
    }

	@Override
    public void start(Scanner scan) {
        String input = "";
        logger.info("Navigated to home screen");
        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to eCommerce App");
                System.out.println("Please select your option:\n");
                System.out.println("Press [1] to login.");
                System.out.println("Press [2] to register.");
                System.out.println("Press [X] to exit the application.");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        logger.info("Navigating to login screen");
                        router.navigate("/login", scan, "");
                        break;
                    case "2":
                        logger.info("Navigating to register screen");
                        router.navigate("/register", scan, "");
                        break;
                    case "x":
                        logger.info("Exiting home screen");
                        System.out.println("\nGoodbye!");
                        break exit;

                    default:
                        logger.warn("Invalid option");
                        clearScreen();
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }
        }
        System.exit(0);
    }


    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

