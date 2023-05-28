package com.Revature.eCommerce.services;
import java.util.List;

import com.Revature.eCommerce.dao.CategoryDAO;
import com.Revature.eCommerce.models.Category;
//import com.Revature.eCommerce.utils.custom_exceptions.CategoryNotFoundException;

//import lombok.AllArgsConstructor;

//The category class handles all category opertations in the eCommerce App
//@AllArgsConstructor
public class CategoryService {
    private final CategoryDAO categoryDao;
    
    public CategoryService(CategoryDAO categoryDao){
        this.categoryDao = categoryDao;
    }
//Finds All Categories
    public List<Category> getAllCategories()  {
        List<Category> category = categoryDao.getAllCategories();

        return category;
    }
}