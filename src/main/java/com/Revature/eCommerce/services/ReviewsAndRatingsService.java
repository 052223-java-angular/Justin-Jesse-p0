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


    
}
