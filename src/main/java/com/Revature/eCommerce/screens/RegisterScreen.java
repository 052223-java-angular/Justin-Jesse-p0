package com.Revature.eCommerce.screens;

import java.util.Scanner;

import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.models.User;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Users can register an account to access features
 */
public class RegisterScreen implements IScreen
{
    private static Logger logger = LogManager.getLogger(RegisterScreen.class);
    private final UserService userService;
    private final RouterService router;
    private Session session;

    public RegisterScreen(UserService userService, RouterService router, Session session)
    {
        this.userService = userService;
        this.router = router;
        this.session = session;
    }
    @Override
    public void start(Scanner scan) {
        logger.info("Starting registration process");
        String username = "";
        String password = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the register screen!");
                logger.info("Navigated to Register screen");
                username = getUsername(scan);
                logger.info("username: {}",username);

                if (username.equals("x")) {
                    logger.info("Exit registration");
                    break exit;
                }


                password = getPassword(scan);

                if (password.equals("x")) {
                    logger.info("Exit registration");
                    break exit;
                }

                clearScreen();
                System.out.println("Please confirm your credentials:");
                System.out.println("\nUsername: " + username);
                System.out.println("Password: " + password);
                System.out.print("\nEnter (y/n): ");

                switch (scan.nextLine()) {
                    case "y":
                        logger.info("User confirmed credentials are correct");
                        User createdUser = userService.register(username, password);
                        session.setSession(createdUser);
                        router.navigate("/login", scan, "");
                        break exit;
                    case "n":
                        logger.info("Restarting registration process");
                        clearScreen();
                        System.out.println("Restarting process...");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
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
    }

    /**
     * Takes in a username and checks to see if it valid and unique
     * @param scan
     * @return
     */
    public String getUsername(Scanner scan) {
        String username = "";

        while (true) {
            System.out.print("\nEnter a username (x to cancel): ");
            username = scan.nextLine();

            if (username.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isValidUsername(username)) {
                logger.warn("Invalid username for: {}", username);
                clearScreen();
                System.out.println("Username needs to be 8-20 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            if (!userService.isUniqueUsername(username)) {
                clearScreen();
                logger.warn("Username is not unique for: {}", username);
                System.out.println("Username is not unique!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }

        return username;
    }

    /**
     * Takes in a users password and check to see if it is valid and the same before
     * registering a user
     * @param scan
     * @return
     */
    public String getPassword(Scanner scan) {
        String password = "";
        String confirmPassword = "";

        while (true) {
            System.out.print("\nEnter a password (x to cancel): ");
            password = scan.nextLine();

            if (password.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isValidPassword(password)) {
                logger.warn("Invalid password");
                clearScreen();
                System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            System.out.print("\nPlease confirm password (x to cancel): ");
            confirmPassword = scan.nextLine();

            if (confirmPassword.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isSamePassword(password, confirmPassword)) {
                clearScreen();
                logger.warn("Passwords did not match when registering");
                System.out.println("Passwords do not match");
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