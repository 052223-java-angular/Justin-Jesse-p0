package com.Revature.eCommerce.services;

/**
 * Validates payment credentials
 */
public class PaymentService {
    /**
     * Verifies if cc number is 16 digits and numbers
     * @param cc - number
     * @return - true/false
     */
    public boolean isValidCC(String cc)
    {

        if (cc.length() != 16 || !cc.matches("\\d{16}")) {
            return true;
        }
        return false;
    }

    /**
     * verifies if exp date is valid
     * @param exp - exp number
     * @return true/false
     */
    public boolean isValidExp(String exp)
    {
        String [] date = exp.split("/");// splits the 4 num at the slash
        if(date.length != 2) // checks array length
        {
            return false;
        }
        else
        {
            int month = Integer.parseInt(date[0]);
            if (month > 0 && month <13)//verify if its 1-12
            {
                int year = Integer.parseInt(date[1]);
                return true;
            }
        }
        return false;
    }

    /**
     * checks if cvv is valid 3 digits
     * @param cvv - number entered
     * @return true/false
     */
    public boolean isValidCVV(String cvv)
    {
        return cvv.length() == 3 && cvv.matches("^[0-9]{3}$");
    }
}
