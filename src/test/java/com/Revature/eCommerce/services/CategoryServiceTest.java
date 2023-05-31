package com.Revature.eCommerce.services;

import com.Revature.eCommerce.dao.CategoryDAO;
import com.Revature.eCommerce.models.Category;
import junit.framework.TestCase;
import org.mockito.MockitoAnnotations;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CategoryServiceTest extends TestCase {

    CategoryService categoryService;
    @Mock
    CategoryDAO categoryDao;
    public void setUp() throws Exception
    {
        MockitoAnnotations.openMocks(this);
        this.categoryService = new CategoryService(categoryDao);
    }

    public void testGetAllCategories()
    {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("1", "Category 1"));
        categories.add(new Category("2", "Category 2"));

        when(categoryDao.getAllCategories()).thenReturn(categories);

        List<Category> actualCategories = categoryService.getAllCategories();

        assertEquals(categories, actualCategories);

    }
}