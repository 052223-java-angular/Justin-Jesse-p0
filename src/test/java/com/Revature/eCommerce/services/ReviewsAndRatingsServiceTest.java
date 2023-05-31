package com.Revature.eCommerce.services;

import com.Revature.eCommerce.dao.ReviewsAndRatingsDAO;
import com.Revature.eCommerce.models.ReviewsAndRatings;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReviewsAndRatingsServiceTest extends TestCase
{

    private ReviewsAndRatingsService reviewsAndRatingsService;
    @Mock
    private ReviewsAndRatingsDAO reviewsAndRatingsDAO;
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.reviewsAndRatingsService = new ReviewsAndRatingsService(reviewsAndRatingsDAO);

    }

    public void testFindReviewsByProducts() {
        String productId = "P1";
        List<ReviewsAndRatings> expected = new ArrayList<>();
        expected.add(new ReviewsAndRatings("R1", "U1", "P1", 4, "Great product"));
        when(reviewsAndRatingsDAO.findReviewsByProduct(productId)).thenReturn(expected);

        List<ReviewsAndRatings> actual = reviewsAndRatingsService.findReviewsByProducts(productId);

        assertEquals(expected, actual);
        verify(reviewsAndRatingsDAO).findReviewsByProduct(productId);
    }

    public void testLeaveReview() {
        String reviewId = "R1";
        String userId = "U1";
        String productId = "P1";
        int rating = 4;
        String review = "Great product";

        reviewsAndRatingsService.LeaveReview(reviewId,userId,productId,rating,review);
        List<ReviewsAndRatings> expected = reviewsAndRatingsService.findReviewsByProducts(productId);

        verify(reviewsAndRatingsDAO).LeaveReview(reviewId, userId, productId, rating, review);
        //assertEquals(reviewId, expected.get(0).getReview_id());
    }
}