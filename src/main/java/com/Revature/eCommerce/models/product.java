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

public class Product 
{
    private String id;
    private String productName;
    private String categoryId;
    private int pricing;
}
