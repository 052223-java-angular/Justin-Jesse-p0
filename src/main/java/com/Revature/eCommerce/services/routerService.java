package com.Revature.eCommerce.services;

import java.util.Scanner;

import com.Revature.eCommerce.dao.userDAO;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.screens.HomeScreen;
import com.Revature.eCommerce.screens.MenuScreen;
import com.Revature.eCommerce.screens.RegisterScreen;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class routerService {
    private Session session;

    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/login":
                break;
            case "/menu":
                new MenuScreen(session).start(scan);
                break;
            case "/register":
                new RegisterScreen(getUserService(), this, session).start(scan);
                break;
            case "/product":
                break;
            case "/cart":
                break;
            case "/payment":
                break;
            case "/review":
                break;
            default:
            case "/history":
            break;

        }
    }


    private userService getUserService() {
        return new userService(new userDAO());
    }


}