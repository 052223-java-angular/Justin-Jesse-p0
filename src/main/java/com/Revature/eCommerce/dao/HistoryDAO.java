package com.Revature.eCommerce.dao;

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



    //need to pass in History ID
    public void save(String historyItemId,CartItem item)
    {try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
        String sql = "INSERT INTO history_items (history_items_id, quantity, price, history_id, product_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,historyItemId);
            ps.setInt(2, item.getQuantity());
            ps.setInt(3, item.getPrice());
            ps.setString(4,"h1");//needs to pass the history
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
}
