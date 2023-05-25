package com.Revature.eCommerce.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.Revature.eCommerce.models.Category;
import com.Revature.eCommerce.models.User;
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

    public Optional<Category> findByName(String categoryName) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "SELECT * FROM Category WHERE categoryName = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, categoryName);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Category category = new Category();
                        category.setId(rs.getString("id"));
                        category.setName(rs.getString("categoryName"));
                        return Optional.of(category);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }

        return Optional.empty();
    }
}