package com.Revature.eCommerce.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HistoryItem {
    private String id;
    private String productId;
    private  String historyId;
    private int quantity;
    private int price;
}
