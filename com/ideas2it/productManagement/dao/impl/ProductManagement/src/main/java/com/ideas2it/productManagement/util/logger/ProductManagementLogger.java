package com.ideas2it.productManagement.util.logger;

import com.ideas2it.productManagement.util.exception.ProductManagementException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ProductManagementLogger {
    public static Logger logger = LogManager.getLogger(ProductManagementLogger.class); 
    
    public static void loggerInfo(String message) {
        logger.info(message);       
    }
   
    public static void loggerError(String message) {
        logger.error(message);       
    }
    
    public static void loggerError(ProductManagementException exception) {
        logger.error(exception);       
    }
    
     public static void loggerError(Exception exception) {
        logger.error(exception);       
    }
}  