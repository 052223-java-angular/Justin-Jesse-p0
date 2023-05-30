package com.Revature.eCommerce.services;

import junit.framework.TestCase;
import org.mockito.MockitoAnnotations;

public class PaymentServiceTest extends TestCase {

    private PaymentService paymentService;
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.paymentService = new PaymentService();
    }

    public void testIsValidCC() {
        String valid = "1234567812345678";
        String invalid = "";

        assertTrue(paymentService.isValidCC(invalid));
        assertFalse(paymentService.isValidCC(valid));
    }

    public void testIsValidExp() {
        String valid = "10/12";
        String invalid = "1010";
        assertTrue(paymentService.isValidExp(valid));
        assertFalse(paymentService.isValidExp(invalid));
    }

    public void testIsValidCVV() {
        String valid = "123";
        String invalid = "1";
        String invalidTwo = "two";

        assertTrue(paymentService.isValidCVV(valid));
        assertFalse(paymentService.isValidCVV(invalid));
        assertFalse(paymentService.isValidCVV(invalidTwo));
    }
}