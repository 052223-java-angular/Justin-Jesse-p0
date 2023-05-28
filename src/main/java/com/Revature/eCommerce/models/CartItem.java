package com.Revature.eCommerce.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartItem {
    private String id;
    private String productId;
    private  String cartId;
    private int quantity;
    private int price;
}
