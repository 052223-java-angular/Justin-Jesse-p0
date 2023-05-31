package com.Revature.eCommerce.screens;
import java.util.UUID;
import java.util.Scanner;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.models.ReviewsAndRatings;
import com.Revature.eCommerce.services.ProductService;
import com.Revature.eCommerce.services.ReviewsAndRatingsService;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ReviewsAndRatingsScreen implements IScreen {
    private final ReviewsAndRatingsService reviewsAndRatingsService;
    private final String productId;
    private final RouterService router;
    private final Session session;
    private final Logger logger = LogManager.getLogger(ReviewsAndRatingsScreen.class);
    

    public ReviewsAndRatingsScreen(RouterService router, ReviewsAndRatingsService reviewsAndRatingsService, String productId, Session session) {
        this.router = router;
        this.reviewsAndRatingsService = reviewsAndRatingsService;
        this.productId = productId;
        this.session = session;
    }

    /**
     * Lets user Leave a review
     * @param scan - user input
     */
    @Override
    public void start(Scanner scan) {
        String input = "";
        logger.info("Navigated to Reviews Screen");
        exit: {
            while (true) {
                clearScreen();

                displayReviews();

                System.out.println("Press [A] to add a review.");
                System.out.println("Press [X] to go back to the Browse Screen.");
                System.out.print("");

                input = scan.nextLine().trim();
                if (input.equalsIgnoreCase("A")) {
                    logger.info("User navigated to leave a review");
                    clearScreen();
                    leaveReview(scan);
                } else if (input.equalsIgnoreCase("X")) {
                    logger.info("Navigating to browse screen");
                    clearScreen();
                    router.navigate("/browse", scan, "");
                    break exit;
                } else {
                    clearScreen();
                    logger.warn("Invalid option for reviews user input: {}", input);
                    System.out.println("Invalid option!");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                }
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Display reviews for a product
     */
    private void displayReviews() {
        logger.info("Displaying reviews for product");
        List<ReviewsAndRatings> reviews = reviewsAndRatingsService.findReviewsByProducts(productId);
        Scanner scan = new Scanner(System.in);
        System.out.println("Reviews and Ratings:");
        System.out.println("-----------------------------");
        System.out.println("Product ID: " + productId);
       // System.out.println("Number of Reviews: " + reviews.size());
        System.out.println("-----------------------------");

        if (reviews.isEmpty()) {
            logger.warn("No reviews to display for product");
            System.out.println("No reviews available for this product.");
        } else {
            for (ReviewsAndRatings review : reviews) {//Displays reviews from any user
                logger.info("Displayed review for user");
                //System.out.println("User ID: " + review.getUser_id());
                System.out.println("Rating: " + review.getRating());
                System.out.println("Review: " + review.getReview());
                System.out.println("-----------------------------");

            }
        }


    }

    /**
     * User can leave a review for a product
     * @param scan - user input
     */
    private void leaveReview(Scanner scan) {
        logger.info("Starting review and rating process");
        System.out.println("Enter your review and rating:\n");
        System.out.print("Enter Review: ");
        String review = scan.nextLine();
        System.out.print("Enter Rating (out of 5): ");
        int rating = Integer.parseInt(scan.nextLine());

        
        String userId = session.getId();
        String reviewId = getReviewID();
        clearScreen();
        System.out.println(reviewId + "\n" + userId + "\n" + productId + "\n" + rating + "\n" + review);
        reviewsAndRatingsService.LeaveReview(reviewId, userId, productId, rating, review);//inserts the review to DB
        
        System.out.println("Review submitted successfully!");
        System.out.print("\nPress anything to return to Browse...");
        logger.info("User successfully posted review");
        scan.nextLine();
        router.navigate("/browse", scan, "");
    }

    /**
     * Helper class
     * @return - generates a random id
     */
    public String getReviewID() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
