package com.Revature.eCommerce.screens;
import java.util.UUID;
import java.util.Scanner;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.models.ReviewsAndRatings;
import com.Revature.eCommerce.services.ProductService;
import com.Revature.eCommerce.services.ReviewsAndRatingsService;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.utils.Session;
import java.util.List;

public class ReviewsAndRatingsScreen implements IScreen {
    private final ReviewsAndRatingsService reviewsAndRatingsService;
    private final String productId;
    private final RouterService router;
    private final Session session;
    

    public ReviewsAndRatingsScreen(RouterService router, ReviewsAndRatingsService reviewsAndRatingsService, String productId, Session session) {
        this.router = router;
        this.reviewsAndRatingsService = reviewsAndRatingsService;
        this.productId = productId;
        this.session = session;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit: {
            while (true) {
                clearScreen();

                displayReviews();

                System.out.println("Press [A] to add a review.");
                System.out.println("Press [X] to go back to the Browse Screen.");
                System.out.print("");

                input = scan.nextLine().trim();
                if (input.equalsIgnoreCase("A")) {
                    clearScreen();
                    leaveReview(scan);
                } else if (input.equalsIgnoreCase("X")) {
                    clearScreen();
                    router.navigate("/browse", scan, "");
                    break exit;
                } else {
                    clearScreen();
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

    private void displayReviews() {
        List<ReviewsAndRatings> reviews = reviewsAndRatingsService.findReviewsByProducts(productId);
        Scanner scan = new Scanner(System.in);
        System.out.println("Reviews and Ratings:");
        System.out.println("-----------------------------");
        System.out.println("Product ID: " + productId);
        System.out.println("Number of Reviews: " + reviews.size());
        System.out.println("-----------------------------");

        if (reviews.isEmpty()) {
            System.out.println("No reviews available for this product.");
        } else {
            for (ReviewsAndRatings review : reviews) {
                System.out.println("User ID: " + review.getUser_id());
                System.out.println("Rating: " + review.getRating());
                System.out.println("Review: " + review.getReview());
                System.out.println("-----------------------------");
            }
        }


    }

    private void leaveReview(Scanner scan) {
        System.out.println("Enter your review and rating (out of 5):");
        System.out.print("Review: ");
        String review = scan.nextLine();
        System.out.print("Rating: ");
        int rating = Integer.parseInt(scan.nextLine());

        
        String userId = session.getId();
        String reviewId = getReviewID();
        clearScreen();
        System.out.println(reviewId + "\n" + userId + "\n" + productId + "\n" + rating + "\n" + review);
        reviewsAndRatingsService.LeaveReview(reviewId, userId, productId, rating, review);
        
        System.out.println("Review submitted successfully!");
        System.out.print("\nPress anything to return to Browse...");
        scan.nextLine();
        router.navigate("/browse", scan, "");
    }
    public String getReviewID() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
