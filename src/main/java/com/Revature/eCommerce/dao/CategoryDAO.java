package com.Revature.eCommerce.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.Revature.eCommerce.models.Category;
import com.Revature.eCommerce.utils.ConnectionFaction;

public class CategoryDAO implements CrudDAO<Category> {

    @Override
    public void save(Category obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Category findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        try {
            Connection conn = ConnectionFaction.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Category");
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategory_ID(resultSet.getString("category_ID"));
                category.setCategory_Name(resultSet.getString("category_Name"));

                categoryList.add(category);
            }
            resultSet.close();
            statement.close();
        } 
     catch (SQLException e) {
        throw new RuntimeException("Unable to connect to db");
    } catch (IOException e) {
        throw new RuntimeException("Cannot find application.properties");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("Unable to load jdbc");
    }

        return categoryList;
    }
}

