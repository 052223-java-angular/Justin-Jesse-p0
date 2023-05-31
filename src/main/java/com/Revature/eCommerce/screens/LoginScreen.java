package com.Revature.eCommerce.screens;

import java.util.Scanner;

import com.Revature.eCommerce.models.User;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.services.UserService;
import com.Revature.eCommerce.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginScreen implements IScreen 
{
    private static Logger logger = LogManager.getLogger(LoginScreen.class);
    private final RouterService router;
    private final UserService userService;
    private final Session session;

    public LoginScreen(UserService userService, RouterService router, Session session) {
        this.router = router;
        this.userService = userService;
        this.session = session;
    }

    /**
     * Displays the login screen where users enter the username and password
     * @param scan - user input
     */
    @Override
    public void start(Scanner scan)
     {
         logger.info("Navigated to login screen");
        String username = "";
        String password = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the login screen!");

                username = getUsername(scan);

                if (username.equals("x")) {
                    break exit;
                }


                password = getPassword(scan);

                if (password.equals("x")) {
                    break exit;
                }

                clearScreen();
                System.out.println("\nUsername: " + username);
                System.out.println("Password: " + password);
                System.out.print("\nSubmit? (y/n): ");

                switch (scan.nextLine()) {
                    case "y":
                        logger.info("User confirmed credentials");
                        User validUser = userService.checkUser(username, password);

                        if(validUser!=null)
                        {
                            logger.info("User successfully logged in");
                        session.setSession(validUser);
                        logger.info("");
                        logger.info("Navigating to menu screen");
                        router.navigate("/menu", scan, "");
                        break exit;
                        }
                        else
                        {
                            logger.warn("User entered an invalid username or password");
                            clearScreen();
                            System.out.println("Invalid username or password.");
                            System.out.print("\nPress enter to continue...");
                            scan.nextLine();
                            break;
                        }
                    case "n":
                        clearScreen();
                        logger.info("Restarting the login process");
                        System.out.println("Restarting process...");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                    default:
                        clearScreen();
                        logger.warn("User input was invalid for confirmation");
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }
        }
    }

    /**
     * Prompts the user to enter a username for their registered account
     * @param scan
     * @return
     */
    private String getUsername(Scanner scan) 
    {
        String username = "";

        while (true) {
            System.out.print("\nEnter a username (x to cancel): ");
            username = scan.nextLine();

            if (username.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isValidUsername(username)) {
               clearScreen();
                logger.warn("Invalid username for: {}", username);
                System.out.println("Username needs to be 8-20 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }

        return username;
    }

    /**
     * Prompts the user to enter a password for their registered account
     * @param scan
     * @return
     */
    private String getPassword(Scanner scan) {
        String password = "";
        while (true) {
            System.out.print("\nEnter a password (x to cancel): ");
            password = scan.nextLine();

            if (password.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isValidPassword(password)) {
                logger.warn("User entered an invalid password");
                clearScreen();
                System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }

        return password;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}
