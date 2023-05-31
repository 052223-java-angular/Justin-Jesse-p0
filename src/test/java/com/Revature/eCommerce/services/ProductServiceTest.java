package com.Revature.eCommerce.services;

import com.Revature.eCommerce.dao.ProductDAO;
import com.Revature.eCommerce.models.Product;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ProductServiceTest extends TestCase {

    private ProductService productService;
    @Mock
    private ProductDAO productDao;

    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productDao);
    }

    public void testGetAllProducts()
    {
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Product 1", "Category 1", 100, "Description 1"));
        products.add(new Product("2", "Product 2", "Category 2", 200, "Description 2"));

        when(productDao.getAllProducts()).thenReturn(products);

        List<Product> actualProducts = productService.getAllProducts();

        assertEquals(products, actualProducts);
    }

    public void testFindProductByName()
    {
        String input = "Product 1";
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Product 1", "Category 1", 100, "Description 1"));
        products.add(new Product("2", "Product 2", "Category 2", 200, "Description 2"));

        when(productDao.findProductByName(products.get(0).getProductName())).thenReturn(products);

        List<Product> actualProducts = productService.findProductByName(input);

        assertEquals(actualProducts.get(0),products.get(0));

    }

    public void testFindProductByCategory() {
        String input = "Category 1";
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Product 1", "Category 1", 100, "Description 1"));
        products.add(new Product("2", "Product 2", "Category 2", 200, "Description 2"));

        when(productDao.findProductByCategory(products.get(0).getCategoryId())).thenReturn(products);

        List<Product> actualProducts = productService.findProductByCategory(input);

        assertEquals(actualProducts.get(0),products.get(0));
    }

    public void testFindProductByPricing() {
        int min = 1;
        int max = 200;
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Product 1", "Category 1", 100, "Description 1"));
        products.add(new Product("2", "Product 2", "Category 2", 200, "Description 2"));
        products.add(new Product("3", "Product 3", "Category 3", 1, "Description 3"));

        when(productDao.findProductByPricing(min, max)).thenReturn(products);

        List<Product> actualProducts = productService.findProductByPricing(min, max);

        assertEquals(actualProducts.size(), products.size());
    }

    public void testGetProduct() {
        String productId = "1";
        Product expectedProduct = new Product("1", "Product 1", "Category 1", 100, "Description 1");

        when(productDao.findById(productId)).thenReturn(expectedProduct);

        Product actualProduct = productService.getProduct(productId);

        assertEquals( expectedProduct, actualProduct);
    }
}