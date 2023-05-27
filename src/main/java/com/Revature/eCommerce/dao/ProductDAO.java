package com.Revature.eCommerce.dao;

//import java.io.IOError;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.utils.ConnectionFaction;


public class ProductDAO implements CrudDAO<Product> {



    @Override
    public void update(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Product findById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /* public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        try(Connection conn = ConnectionFaction.getInstance().getConnection()){
            String sql = "Select * FROM product";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");
            
            try (ResultSet rs = statement.executeQuery()){
                Product product = new Product();
                product.setProductId(resultSet.getString("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setCategoryId(resultSet.getString("category_id"));
                product.setPricing(resultSet.getInt("pricing"));
                product.setDescription(resultSet.getString("description"));
            }
            resultSet.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
        return productList;

    } */

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            Connection conn = ConnectionFaction.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("product_ID"));
                product.setProductName(resultSet.getString("product_Name"));
                product.setCategoryId(resultSet.getString("category_ID"));
                product.setPricing(resultSet.getInt("pricing"));
                product.setDescription(resultSet.getString("description"));
                productList.add(product);
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

        return productList;
    }
    
// Retrieves Product By Name, Category, or Pricing. 
    public List<Product> findProduct(String productName, String categoryId, int pricing) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            List<Product> productList = new ArrayList<>();
            String sql = "SELECT * FROM product WHERE productName ILIKE ? AND categoryID ILIKE ? AND pricing ILIKE ? ";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, productName);
                ps.setString(2, categoryId);
                ps.setInt(3, pricing);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Product product = new Product();
                        product.setProductId(rs.getString("product_id"));
                        product.setProductName(rs.getString("product_name"));
                        product.setCategoryId(rs.getString("category_id"));
                        product.setPricing(rs.getInt("pricing"));
                        return productList;
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

        return null;
    }

    @Override
    public void save(Product obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
    

}

