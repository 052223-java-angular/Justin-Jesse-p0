package com.Revature.eCommerce.utils.custom_exceptions;

//Throws category not found exception when category is not found. 
public class CategoryNotFoundException extends RuntimeException {

// Construct new Category not found exception. 
    public CategoryNotFoundException() {
        super("Category not found!");
    }
}