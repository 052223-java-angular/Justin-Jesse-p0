package com.Revature.eCommerce.services;
import java.util.List;
import com.Revature.eCommerce.dao.ReviewsAndRatingsDAO;
import com.Revature.eCommerce.models.ReviewsAndRatings;

/**
 * Service layer between reviews screen and DAO
 */
public class ReviewsAndRatingsService {
    private final ReviewsAndRatingsDAO reviewsandratingsDao;
    public ReviewsAndRatingsService (ReviewsAndRatingsDAO reviewsandratingsDao){
        this.reviewsandratingsDao = reviewsandratingsDao;
    }

    /**
     * finds reviews by product id
     * @param productId - id to use
     * @return - reviews for product
     */
    public List<ReviewsAndRatings> findReviewsByProducts(String productId){
     List<ReviewsAndRatings> reviews = reviewsandratingsDao.findReviewsByProduct(productId);
     return reviews;

    }

    /**
     * Inserts a review
     * @param review_ID - review id
     * @param user_Id - user id
     * @param product_Id - product id
     * @param rating - rating
     * @param review -review
     */
    public void LeaveReview(String review_ID, String user_Id, String product_Id, int rating, String review){
    reviewsandratingsDao.LeaveReview(review_ID, user_Id, product_Id, rating, review);
    }



}
