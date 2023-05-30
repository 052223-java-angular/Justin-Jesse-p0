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
            String sql = "SELECT * FROM REVIEWSANDRATINGS WHERE product_ID = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, productId);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ReviewsAndRatings review = new ReviewsAndRatings();
                        review.setReview_id(rs.getString("review_ID"));
                        review.setUser_id(rs.getString("user_ID"));
                        review.setProduct_id(rs.getString("product_ID"));
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

    public void LeaveReview(String review_ID, String user_ID, String product_ID, int rating, String review) {
        try (Connection conn = ConnectionFaction.getInstance().getConnection()) {
            String sql = "INSERT INTO REVIEWSANDRATINGS (review_ID, user_ID, product_ID, rating, review) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, review_ID);
                ps.setString(2, user_ID);
                ps.setString(3, product_ID);
                ps.setInt(4, rating);
                ps.setString(5, review);
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
    public void save(ReviewsAndRatings obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}