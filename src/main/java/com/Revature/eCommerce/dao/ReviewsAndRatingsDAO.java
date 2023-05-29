package com.Revature.eCommerce.dao;

import com.Revature.eCommerce.models.ReviewsAndRatings;
import com.Revature.eCommerce.utils.ConnectionFaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

public class ReviewsAndRatingsDAO implements CrudDAO<ReviewsAndRatings> {

    @Override
    public void save(ReviewsAndRatings obj) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "INSERT INTO reviews (review_id, user_id, product_id, rating, review) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, obj.getReview_id());
                ps.setString(2, obj.getUser_id());
                ps.setString(3, obj.getProduct_id());
                ps.setInt(4, obj.getRating());
                ps.setString(5, obj.getReview());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load JDBC driver");
        }
    }

    @Override
    public void update(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ReviewsAndRatings findById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<ReviewsAndRatings> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public List<ReviewsAndRatings> findReviewsByProduct(String productId) {
        List<ReviewsAndRatings> reviews = new ArrayList<>();

        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "SELECT * FROM reviews WHERE product_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, productId);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ReviewsAndRatings review = new ReviewsAndRatings();
                        review.setReview_id(rs.getString("review_id"));
                        review.setUser_id(rs.getString("user_id"));
                        review.setProduct_id(rs.getString("product_id"));
                        review.setRating(rs.getInt("rating"));
                        review.setReview(rs.getString("review"));
                        reviews.add(review);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load JDBC driver");
        }

        return reviews;
    }
}