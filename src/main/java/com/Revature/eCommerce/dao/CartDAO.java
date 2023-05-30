package com.Revature.eCommerce.dao;

import com.Revature.eCommerce.models.Cart;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.utils.ConnectionFaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CartDAO implements CrudDAO
{

  

    @Override
    public void update(String userId)
    {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "INSERT INTO cart (cart_id,user_id,amount_spent) VALUES (?,?,?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, UUID.randomUUID().toString());
                ps.setString(2,userId);
                ps.setInt(3,0);
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
    public void delete(String id) {

    }


    public void deleteItem(String id)
    {try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
        String sql = "DELETE FROM cart_item where cart_item_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
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
    public void deleteItemFromCart(String id)
    {try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
        String sql = "DELETE FROM cart_item WHERE cart_ID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
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
    public Object findById(String id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    public Optional<Cart> getCart(String userID) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "SELECT * FROM cart Where user_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, userID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Cart cart = new Cart(rs.getString("cart_id"), rs.getString("user_id"),
                                rs.getInt("amount_spent"));
                        return Optional.of(cart);
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

    public ArrayList<CartItem> getCartItems(String cartID) {
        ArrayList<CartItem> items = new ArrayList<>();

        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "SELECT * FROM cart_item WHERE cart_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cartID);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CartItem item = new CartItem(rs.getString("cart_item_id"), rs.getString("product_id"),
                                rs.getString("cart_id"), rs.getInt("quantity"), rs.getInt("price"));

                        items.add(item);
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
        return items;
    }

    public void changeItemQuantity(String productId, int quantity, String cartId) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "UPDATE cart_item SET quantity = ? WHERE product_ID = ? AND cart_ID = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, quantity);
                ps.setString(2, productId);
                ps.setString(3, cartId);
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

    public void changeItemPrice(Product product, int updatedPrice, String cartId) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "UPDATE cart_item SET price = ? WHERE product_ID = ? AND cart_ID = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, updatedPrice);
                ps.setString(2, product.getProductId());
                ps.setString(3, cartId);
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

    
    public void setCart(Product product, CartItem item, Cart cart)
    {try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
        String sql = "INSERT INTO CART_ITEM (cart_Item_ID, product_ID, cart_ID, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,item.getId());
            ps.setString(2, product.getProductId());
            ps.setString(3, cart.getId());
            ps.setInt(4, item.getPrice());
            ps.setInt(5, item.getQuantity());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}

