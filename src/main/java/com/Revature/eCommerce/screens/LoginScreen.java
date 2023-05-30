package com.Revature.eCommerce.screens;

import java.util.Scanner;

import com.Revature.eCommerce.models.User;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.services.UserService;
import com.Revature.eCommerce.utils.Session;

public class LoginScreen implements IScreen 
{

    private final RouterService router;
    private final UserService userService;
    private final Session session;

    public LoginScreen(UserService userService, RouterService router, Session session) {
        this.router = router;
        this.userService = userService;
        this.session = session;
    }

    @Override
    public void start(Scanner scan)
     {
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

                //This goes back to  menu once user logs in
                switch (scan.nextLine()) {
                    case "y":
                        User validUser = userService.checkUser(username, password);

                        if(validUser!=null)
                        {
                        session.setSession(validUser);
                        router.navigate("/menu", scan, "");
                        break exit;
                        }
                        else
                        {
                            clearScreen();
                            System.out.println("Invalid username or password.");
                            System.out.print("\nPress enter to continue...");
                            scan.nextLine();
                            break;
                        }
                    case "n":
                        clearScreen();
                        System.out.println("Restarting process...");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                    default:
                        clearScreen();
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }



                //break exit; 
            }
        }
    }

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
                System.out.println("Username needs to be 8-20 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }

        return username;
    }


    private String getPassword(Scanner scan) {
        String password = "";
        //String confirmPassword = "";

        while (true) {
            System.out.print("\nEnter a password (x to cancel): ");
            password = scan.nextLine();

            if (password.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isValidPassword(password)) {
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
