package com.Revature.eCommerce.services;

import java.util.Optional;

import com.Revature.eCommerce.dao.CategoryDAO;
import com.Revature.eCommerce.models.Category;
import com.Revature.eCommerce.utils.custom_exceptions.CategoryNotFoundException;

import lombok.AllArgsConstructor;

//The category class handles all category opertations in the eCommerce App
@AllArgsConstructor
public class CategoryService {
    private final CategoryDAO categoryDao;

//Find's Category by name. Throws CategoryNotFoundException if not found. 
    public Category findByName(String categoryName) throws CategoryNotFoundException {
        Optional<Category> categoryOpt = categoryDao.findByName(categoryName);

        return categoryOpt.orElseThrow(CategoryNotFoundException::new);
    }
}