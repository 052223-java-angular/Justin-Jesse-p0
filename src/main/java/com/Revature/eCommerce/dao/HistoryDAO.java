package com.Revature.eCommerce.dao;
import java.util.Optional;

import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.History;
import com.Revature.eCommerce.models.HistoryItem;
import com.Revature.eCommerce.utils.ConnectionFaction;
import com.Revature.eCommerce.utils.Session;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class HistoryDAO implements CrudDAO{



    public void save(String historyItemId, CartItem item, String historyId)
    {try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
        String sql = "INSERT INTO HISTORY_ITEMS (history_Items_ID, quantity, price, history_ID, product_ID) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,historyItemId);
            ps.setInt(2, item.getQuantity());
            ps.setInt(3, item.getPrice());
            ps.setString(4, historyId);// will change, just for testing
            ps.setString(5, item.getProductId());
            ps.executeUpdate();
        }
      

    } catch (SQLException e) {
        throw new RuntimeException("Unable to connect to db");
    } catch (IOException e) {
        throw new RuntimeException("Cannot find application.properties");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("Unable to load jdbc");
    }
    }

    @Override
    public void save(Object obj) {

    }

    @Override
    public void update(String id) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    public List<HistoryItem> getAllHistory() {
        List<HistoryItem> historyList = new ArrayList<>();
        try {
            Connection conn = ConnectionFaction.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM HISTORY_ITEMS");
            while (resultSet.next()) {
                HistoryItem history = new HistoryItem();
                history.setId(resultSet.getString("history_ID"));
                history.setProductId(resultSet.getString("product_ID"));
                history.setHistoryId(resultSet.getString("history_Items_ID"));
                history.setQuantity(resultSet.getInt("quantity"));
                history.setPrice(resultSet.getInt("price"));
                historyList.add(history);
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

        return historyList;
    }

    public List<HistoryItem> getAllHistoryById(String historyID) {
        List<HistoryItem> historyList = new ArrayList<>();
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "SELECT * FROM HISTORY_ITEMS WHERE history_ID = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, historyID);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        HistoryItem history = new HistoryItem();
                        history.setId(rs.getString("history_Items_ID"));
                        history.setProductId(rs.getString("quantity"));
                        history.setHistoryId(rs.getString("price"));
                        history.setQuantity(rs.getInt("history_ID"));
                        history.setPrice(rs.getInt("prodcut_ID"));
                        
                    }
                }
                return historyList;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }

   
    }
    

    public Optional<History> findByUserId(String userId) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "SELECT * FROM HISTORY WHERE user_ID = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, userId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        History history = new History();
                        history.setId(rs.getString("history_ID"));
                        history.setUserId(rs.getString("user_ID"));
                        history.setTotalCost(rs.getFloat("total_Cost"));
                        return Optional.of(history);
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

    public Optional<History> findByUserHistoryId(String historyId) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "SELECT * FROM HISTORY WHERE history_ID = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, historyId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        History history = new History();
                        history.setId(rs.getString("history_ID"));
                        history.setUserId(rs.getString("user_ID"));
                        history.setTotalCost(rs.getFloat("total_Cost"));
                        return Optional.of(history);
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

    public void setHistory(String historyId, String userId, Float totalAmount)
    {try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
        String sql = "INSERT INTO HISTORY (history_ID, user_ID, total_Cost) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,historyId);
            ps.setString(2, userId);
            ps.setFloat(3, totalAmount);
  
        }
      

    } catch (SQLException e) {
        throw new RuntimeException("Unable to connect to db");
    } catch (IOException e) {
        throw new RuntimeException("Cannot find application.properties");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("Unable to load jdbc");
    }
    }


  


}
