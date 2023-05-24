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

public class reviewsAndRatings 
{
    private String id;    
    private String userId;
    private String productId;
    private int rating;
    private String review;
}