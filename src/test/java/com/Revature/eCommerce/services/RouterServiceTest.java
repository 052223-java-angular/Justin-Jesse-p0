package com.Revature.eCommerce.services;

import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.models.History;
import com.Revature.eCommerce.screens.*;
import com.Revature.eCommerce.utils.Session;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class RouterServiceTest extends TestCase {
    @Mock
    private Session session;
    @Mock
    private Product product;
    @Mock
    private HomeScreen homeScreen;
    @Mock
    private LoginScreen loginScreen;
    @Mock
    private MenuScreen menuScreen;
    @Mock
    private RegisterScreen registerScreen;
    @Mock
    private BrowseScreen browseScreen;
    @Mock
    private SearchScreen searchScreen;
    @Mock
    private CartScreen cartScreen;
    @Mock
    private ReviewsAndRatingsScreen reviewsAndRatingsScreen;
    private RouterService routerService;
    private History history;

    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        routerService = new RouterService(session, product, history);
    }

    public void testNavigateToHome()
    {
        String path = "/home";
        Scanner scan = new Scanner(System.in);
        routerService.navigate(path, scan, "");
        verify(homeScreen).start(scan);
    }
    public void testNavigateToLogin()
    {
        String path = "/login";
        Scanner scan = new Scanner(System.in);
        routerService.navigate(path, scan, "");
        verify(loginScreen).start(scan);
    }
}