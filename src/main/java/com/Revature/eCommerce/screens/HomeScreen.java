package com.Revature.eCommerce.screens;
import java.util.Scanner;
import com.Revature.eCommerce.services.RouterService;

public class HomeScreen implements IScreen{
    private final RouterService router;



    public HomeScreen(RouterService router) {
        this.router = router;
    }

	@Override
    public void start(Scanner scan) {
        String input = "";

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
                        router.navigate("/login", scan);
                        break;
                    case "2":
                        router.navigate("/register", scan);
                        break;
                    case "x":
                        System.out.println("\nGoodbye!");
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
        System.exit(0);
    }


    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

