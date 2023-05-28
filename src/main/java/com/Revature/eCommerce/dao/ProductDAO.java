package com.Revature.eCommerce.dao;

//import java.io.IOError;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//import java.util.Optional;
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
 
public List<Product> findProductByName(String productName) {
    List<Product> products = new ArrayList<>();
    try {
        Connection conn = ConnectionFaction.getInstance().getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM PRODUCT WHERE product_Name ILIKE ?");
        preparedStatement.setString(1, "%" + productName + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Product product = new Product();
            product.setProductId(resultSet.getString("product_ID"));
            product.setProductName(resultSet.getString("product_Name"));
            product.setCategoryId(resultSet.getString("category_ID"));
            product.setPricing(resultSet.getInt("pricing"));
            product.setDescription(resultSet.getString("description"));

            products.add(product);
        }
    } 
         catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    return products;
}

public List<Product> findProductByCategory(String category_ID) {
    List<Product> products = new ArrayList<>();
    try {
        Connection conn = ConnectionFaction.getInstance().getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM PRODUCT WHERE category_ID  = ?");
        preparedStatement.setString(1, category_ID);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Product product = new Product();
            product.setProductId(resultSet.getString("product_ID"));
            product.setProductName(resultSet.getString("product_Name"));
            product.setCategoryId(resultSet.getString("category_ID"));
            product.setPricing(resultSet.getInt("pricing"));
            product.setDescription(resultSet.getString("description"));

            products.add(product);
        }
    } 
         catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    return products;
}
public List<Product> findProductByPricing(int minPrice, int maxPrice) {
    List<Product> products = new ArrayList<>();
    try {
        Connection conn = ConnectionFaction.getInstance().getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM PRODUCT WHERE pricing >= ? AND pricing <= ?");
        preparedStatement.setInt(1, minPrice);
        preparedStatement.setInt(2, maxPrice);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Product product = new Product();
            product.setProductId(resultSet.getString("product_ID"));
            product.setProductName(resultSet.getString("product_Name"));
            product.setCategoryId(resultSet.getString("category_ID"));
            product.setPricing(resultSet.getInt("pricing"));
            product.setDescription(resultSet.getString("description"));

            products.add(product);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Unable to connect to the database");
    } catch (IOException e) {
        throw new RuntimeException("Cannot find application.properties");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("Unable to load JDBC driver");
    }
    return products;
}

    @Override
    public void save(Product obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
    

}

