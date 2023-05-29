package com.Revature.eCommerce.services;

public class PaymentService {
    public boolean isValidCC(String cc)
    {
        String regex = "^[0-9]{16}$";
        return cc.equalsIgnoreCase(regex);
    }
    public boolean isValidExp(String exp)
    {
        String [] date = exp.split("/");
        if(date.length != 2)
        {
            return false;
        }
        else
        {
            int month = Integer.parseInt(date[0]);
            if (month > 0 && month <13)
            {
                int year = Integer.parseInt(date[1]);
                return true;
            }
        }
        return false;
    }

    public boolean isValidCVV(String cvv)
    {
        return cvv.length() == 3 && cvv.matches("^[0-9]{3}$");
    }
}
