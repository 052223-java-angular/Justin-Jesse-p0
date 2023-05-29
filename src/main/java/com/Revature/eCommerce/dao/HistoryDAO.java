package com.Revature.eCommerce.dao;

import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.utils.ConnectionFaction;
import com.Revature.eCommerce.utils.Session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO implements CrudDAO{



    public void save(String historyItemId,CartItem item, Session session, int amountSpent)
    {try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
        String sql = "INSERT INTO history_items (history_items_id, quantity, price, history_id, product_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,historyItemId);
            ps.setInt(2, item.getQuantity());
            ps.setInt(3, item.getPrice());
            ps.setString(4, "H1");// will change, just for testing
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
}
