package com.Revature.eCommerce.services;

import java.util.List;
//import java.util.Optional;
import com.Revature.eCommerce.dao.ProductDAO;
import com.Revature.eCommerce.models.Product;

//import lombok.AllArgsConstructor;


    public class ProductService {
        private final ProductDAO productDao;


    public ProductService(ProductDAO productDao){
        this.productDao = productDao;
        //this.categoryService = categoryService;
    }

	public List<Product> getAllProducts(){
        List<Product> products = productDao.getAllProducts();

        return products;

    }
    
    public List<Product> findProductByName(String input){
        List<Product> products = productDao.findProductByName(input);
        return products;

    }
    public List<Product> findProductByCategory(String input){
        List<Product> products = productDao.findProductByCategory(input);
        return products;

    }

    public List<Product> findProductByPricing(int min, int max){
        List<Product> products = productDao.findProductByPricing(min, max);
        return products;

    }
    public Product getProduct(String productID)
    {
        return productDao.findById(productID);
    }


}

