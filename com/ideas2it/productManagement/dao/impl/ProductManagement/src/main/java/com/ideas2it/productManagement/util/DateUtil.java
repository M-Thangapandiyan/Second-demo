package com.ideas2it.productManagement.util;

import java.text.ParseException; 
import java.text.SimpleDateFormat;   
import java.util.Date;
 
import com.ideas2it.productManagement.util.exception.ProductManagementException;

/**
 * This methos used to validate the date and calculate the yearsDifference
 *
 * @author Thangapandiyan
 * @version 1.0 
 */
public class DateUtil {   
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static Date currentDate = new Date(); 
        
    /**
     * This method is used to convert the date format
     *
     * @param startDate - the startDate to convert to date format
     *
     * @return date- if the given date converted
     */
    public static Date getDate(String startDate) throws ProductManagementException { 
        dateFormat.setLenient(false);
        Date date = null;
        try {
             date = dateFormat.parse(startDate);
        } catch(ParseException productManagementException){
            throw new ProductManagementException("Invalid Date");
        }
        return date;
    } 
    
    /**
     * This method is used to convert the sql date to date
     *
     * @param java.sql.Date date - the startDate to convert to date format
     *
     * @return date - converted date
     */
    public static java.util.Date getDate(java.sql.Date date) {
        return new java.util.Date(date.getTime()); 
    }
    
    /**
     * This method is used to convert the date to sql date
     *
     * @param Date date - the startDate to convert to date format
     *
     * @return sql date - converted sql date
     */
    public static java.sql.Date getSqlDate(Date date) {
        return new java.sql.Date(date.getTime()); 
    }
    
    /**
     * Used to find the product lifetime
     * 
     * @param date - the date to calculate
     * @param currentdate - to calculate
     * @return yearsDifference - if the given date calculated
     */
    public static int find(Date date, Date currentDate) {
        int yearsDifference = 0; 
        if(null != date && null != currentDate) {
            long timeDifference = currentDate.getTime() - date.getTime(); 
            yearsDifference = (int)(timeDifference / (1000l * 60 * 60 * 24 * 365));    
        }
        return yearsDifference;
    }
}