package com.Revature.eCommerce.models;

import java.sql.Date;

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

public class PaymentInfo {
    private String id;
    private String userId;
    private int ccNumber;
    private int cv;
    private int expDate;
}
