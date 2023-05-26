package com.Revature.eCommerce.screens;

import com.Revature.eCommerce.models.User;
import com.Revature.eCommerce.utils.Session;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Scanner;

    @AllArgsConstructor
public class CartScreen implements IScreen{
    private final Session session;
    @Override
    public void start(Scanner scan)
    {
        String username = "";
        String password = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Items in your cart " + session.getUsername() +":");

                //username = getUsername(scan);

                if (true) {

                    break exit;
                }


                //password = getPassword(scan);

                if (true) {
                    break exit;
                }

                clearScreen();
                System.out.println("Please confirm your credentials:");
                System.out.println("\nUsername: " + username);
                System.out.println("Password: " + password);
                System.out.print("\nEnter (y/n): ");

                switch (scan.nextLine()) {
                    case "y":
                        //User createdUser = userService.register(username, password);
                        //session.setSession(createdUser);
                        //router.navigate("/login", scan);
                        break exit;
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
            }
        }
    }
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
