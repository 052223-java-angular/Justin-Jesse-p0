package com.Revature.eCommerce.services;
import java.util.List;
import com.Revature.eCommerce.dao.ReviewsAndRatingsDAO;
import com.Revature.eCommerce.models.ReviewsAndRatings;

public class ReviewsAndRatingsService {
    private final ReviewsAndRatingsDAO reviewsandratingsDao;
    public ReviewsAndRatingsService (ReviewsAndRatingsDAO reviewsandratingsDao){
        this.reviewsandratingsDao = reviewsandratingsDao;
    }

    public List<ReviewsAndRatings> findReviewsByProducts(String productId){
     List<ReviewsAndRatings> reviews = reviewsandratingsDao.findReviewsByProduct(productId);
     return reviews;

    }
    public void LeaveReview(String review_ID, String user_Id, String product_Id, int rating, String review){
    reviewsandratingsDao.LeaveReview(review_ID, user_Id, product_Id, rating, review);
    }




}
