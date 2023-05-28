package com.Revature.eCommerce.dao;
import com.Revature.eCommerce.models.Role;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.Revature.eCommerce.utils.ConnectionFaction;

public class RoleDAO implements CrudDAO{
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
    public Optional<Role> findByName(String name) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "SELECT * FROM roles WHERE rolename = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Role role = new Role();
                        role.setId(rs.getString("role_id"));
                        role.setName(rs.getString("rolename"));
                        return Optional.of(role);
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
