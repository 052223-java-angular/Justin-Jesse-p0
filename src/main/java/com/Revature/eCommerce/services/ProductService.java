package com.Revature.eCommerce.services;

import com.Revature.eCommerce.dao.ProductDAO;
import com.Revature.eCommerce.models.Product;


public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product findByName(String name)
    {
        //Should add a check to avoid misspelling and wrong data entered
       return  productDAO.findByName(name);

    }
    public Product getProduct(String productID)
    {
        return productDAO.findById(productID);
    }
}
