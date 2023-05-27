package com.Revature.eCommerce.services;

import java.util.Scanner;

import com.Revature.eCommerce.dao.CartDAO;
import com.Revature.eCommerce.dao.RoleDAO;
import com.Revature.eCommerce.dao.UserDAO;
import com.Revature.eCommerce.screens.*;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.dao.ProductDAO;

//@AllArgsConstructor
//@NoArgsConstructor
public class RouterService {
    private Session session;
    private ProductDAO productDao;
    public RouterService()
    {
        
    }
    public RouterService(Session session, ProductDAO productDao)
    {
        this.session = session;
        this.productDao = productDao;
    }

    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/login":
                new LoginScreen(getUserService(), this, session).start(scan);
                break;
            case "/menu":
                new MenuScreen(this, session).start(scan);
                break;
            case "/register":
                new RegisterScreen(getUserService(), this, session).start(scan);
                break;
            case "/browse":
            new BrowseScreen(this, session, productDao).start(scan);
                break;
            case "/cart":
                new CartScreen(getCartService(),this,session).start(scan);
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


    private UserService getUserService()
    {
        return new UserService(new UserDAO(), getRoleService());
    }

    private RoleService getRoleService(){
        return new RoleService(new RoleDAO());
    }
    private CartService getCartService()
    {
        return new CartService(new CartDAO(), new ProductDAO());
    }
}
