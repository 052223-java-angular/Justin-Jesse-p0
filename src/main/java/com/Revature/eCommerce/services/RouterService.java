package com.Revature.eCommerce.services;

import java.util.Scanner;

import com.Revature.eCommerce.dao.*;
import com.Revature.eCommerce.screens.*;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.screens.HomeScreen;
import com.Revature.eCommerce.screens.LoginScreen;
import com.Revature.eCommerce.screens.MenuScreen;
import com.Revature.eCommerce.screens.RegisterScreen;
import com.Revature.eCommerce.screens.BrowseScreen;
import com.Revature.eCommerce.screens.SearchScreen;

public class RouterService {
    private Session session;
    private Product product;
    public RouterService(Session session, Product product)
    {
        this.session = session;
        this.product = product;

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
                new BrowseScreen(this, session, product, getProductService()).start(scan);
                break;

            case "/search":
                new SearchScreen(this, session, getProductService(), getCategoryService()).start(scan);
                break;

            case "/cart":
                new CartScreen(getCartService(),getProductService(),getHistoryService(),this,session).start(scan);
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
        return new CartService(new CartDAO());
    }
    private HistoryService getHistoryService(){return new HistoryService(new HistoryDAO());}
    private ProductService getProductService(){
        return new ProductService(new ProductDAO());
    };

    private CategoryService getCategoryService()
    {
        return new CategoryService(new CategoryDAO());
    }

}
