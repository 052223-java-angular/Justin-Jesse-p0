package com.Revature.eCommerce.services;

import java.util.Scanner;

import com.Revature.eCommerce.dao.*;
import com.Revature.eCommerce.screens.*;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.dao.ProductDAO;
import com.Revature.eCommerce.dao.CategoryDAO;
import com.Revature.eCommerce.dao.ReviewsAndRatingsDAO;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.models.History;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RouterService {
    private Logger logger = LogManager.getLogger(RouterService.class);
    private Session session;
    private Product product;
    private History history;
    public RouterService(Session session, Product product, History history)
    {
        this.session = session;
        this.product = product;
        this.history = history;
    }

    public void navigate(String path, Scanner scan, String productId) {
        switch (path) {

            case "/home":
                new HomeScreen(this).start(scan);
                break;

            case "/login":
                new LoginScreen(getUserService(), this, session).start(scan);
                break;

            case "/menu":
                new MenuScreen(this, session,getHistoryService()).start(scan);
                break;

            case "/register":
                new RegisterScreen(getUserService(), this, session).start(scan);
                break;

            case "/browse":
                new BrowseScreen(this, session, product, getProductService(), getReviewsAndRatingsService(), getCartService()).start(scan);
                break;

            case "/search":
                new SearchScreen(this, session, getProductService(), getCategoryService(), getCartService()).start(scan);
                break;

            case "/cart":
                new CartScreen(getCartService(),getProductService(),getHistoryService(),this,session).start(scan);
                break;


            case "/reviews":
                new ReviewsAndRatingsScreen(this, getReviewsAndRatingsService(), productId, session).start(scan);
                break;
               
            case "/history":
                new HistoryScreen(this, session, getHistoryService()).start(scan);
                break;

            default:
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
    private HistoryService getHistoryService() {
        return new HistoryService(new HistoryDAO(), history);
    }


    private ProductService getProductService(){
        return new ProductService(new ProductDAO());
    };

    private CategoryService getCategoryService()
    {
        return new CategoryService(new CategoryDAO());
    }

    private ReviewsAndRatingsService getReviewsAndRatingsService()
    {
        return new ReviewsAndRatingsService(new ReviewsAndRatingsDAO());
    }


}
