package com.Revature.eCommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ReviewsAndRatings 
{

    private String review_id;    
    private String user_id;
    private String product_id;
    private int rating;
    private String review;

}
