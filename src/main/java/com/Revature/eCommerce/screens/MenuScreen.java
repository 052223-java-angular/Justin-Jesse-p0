package com.Revature.eCommerce.screens;
import java.util.Scanner;
import com.Revature.eCommerce.utils.Session;

//@AllArgsConstructor
//@NoArgsConstructor
public class MenuScreen implements IScreen {
    private Session session;

    public MenuScreen(Session session)
    {
        this.session = session;
    }

    @Override
    public void start(Scanner scan) {
        System.out.println("Welcome to the menu screen " + session.getUsername() + "!");
        scan.nextLine();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}