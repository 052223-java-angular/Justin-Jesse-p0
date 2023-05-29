package com.Revature.eCommerce.utils.custom_exceptions;

public class NoItemsInCartException extends RuntimeException{
    public NoItemsInCartException() {
        super("There are no items in your cart!");
    }
}
