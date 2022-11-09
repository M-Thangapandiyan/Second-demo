package com.ideas2it.productManagement.util;

import java.util.regex.Pattern;
import java.util.InputMismatchException;

import com.ideas2it.productManagement.util.exception.ProductManagementException;
 
/**
 * Validation class gets the input from the controller and validated the input
 *
 * @author Thangapandiyan
 * @version 1.0  
 */
public class Validation {

    /**
     * This method used to validate the value which contains only alphabet
     *
     * @param value - the value to be validated
     * @return - true if the name matches [a-zA-Z]* pattern
     *                false otherwise
     */
    public static boolean containsOnlyAlphabet(String value) throws InputMismatchException {
        return Pattern.matches("[a-zA-Z]*", value);
    }
   
    /**
     * This method used to validate the price which contains only numbers.
     *
     * @param price - the price to be validated
     * @return      - true  if the price matches^[0-9]{4} pattern
     *                false otherwise
     */
    public static boolean isValidPrice(int price)  {
        String cost = ""; 
        cost = Integer.toString(price);
        return Pattern.matches("[0-9]{4}", cost);
    }
 
    /**
     * This method used to validate the date 
     * 
     * @param startDate - the startDate to validate
     * @return date - if the given date is valid                 
     */
    public static boolean isValidDate(String startDate) throws ProductManagementException {
        if(!(Pattern.matches("^\\d{2}-\\d{2}-\\d{4}$", startDate))) {
            throw new ProductManagementException(Constant.INVALID_DATE);
        }
        return true;
    }
}
