package com.Revature.eCommerce.services;

import java.util.List;
//import java.util.Optional;
import com.Revature.eCommerce.dao.ProductDAO;
import com.Revature.eCommerce.models.Product;

/**
 * Service layer between Screen and product DAO
 */
    public class ProductService {
        private final ProductDAO productDao;


    public ProductService(ProductDAO productDao){
        this.productDao = productDao;
    }

    /**
     * returns a list of all the products in DB
     * @return
     */
	public List<Product> getAllProducts(){
        List<Product> products = productDao.getAllProducts();

        return products;

    }

    /**
     * finds products by the product name
     * @param input - product name
     * @return - list of products
     */
    public List<Product> findProductByName(String input){
        List<Product> products = productDao.findProductByName(input);
        return products;

    }

    /**
     * finds the products by the category id
     * @param input - category id
     * @return list of products
     */
    public List<Product> findProductByCategory(String input){
        List<Product> products = productDao.findProductByCategory(input);
        return products;

    }

    /**
     * Search products by price range
     * @param min - minimum number
     * @param max - maximum number
     * @return - list of products
     */
    public List<Product> findProductByPricing(int min, int max){
        List<Product> products = productDao.findProductByPricing(min, max);
        return products;

    }

    /**
     * Gets product by ID
     * @param productID - product id
     * @return product
     */
    public Product getProduct(String productID)
    {
        return productDao.findById(productID);
    }


}

