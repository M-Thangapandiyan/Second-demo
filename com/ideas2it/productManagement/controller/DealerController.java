package com.ideas2it.productManagement.controller;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.service.impl.DealerServiceImpl;
import com.ideas2it.productManagement.util.Constant;
import com.ideas2it.productManagement.util.exception.ProductManagementException;
import com.ideas2it.productManagement.util.logger.ProductManagementLogger;
import com.ideas2it.productManagement.util.Validation;

/**
 * Controller class is used for all input and output functions related to product operation
 *
 * @author Thangapandiyan
 * @version 1.0  
 */
public class DealerController {
    private Scanner scanner = new Scanner(System.in);
    private DealerServiceImpl dealerService = new DealerServiceImpl();

    /**
     * Read the integer value from the user
     *
     * @return the specified value
     */
    public int getInt() {
       int value = 0;
       boolean result = true;
       do {
           try {
              value = scanner.nextInt();
              break;
           } catch(InputMismatchException inputMismatchException) {
               ProductManagementLogger.loggerError(Constant.INVALID_OPTION);
               scanner.nextLine();
               result = false;
           }
        } while(!result);
        return value;
    }
  
    /**
     * Read the string value from the user 
     * 
     * @param message - to display user context message
     * @return - the element at the specified value
     */
    public String getString(String message) {
        System.out.println(message);
        return scanner.next();
    }
    
    /**
     * Used to read the value for continue the process
     *
     * @return - true  if the value is one
     *           false otherwise
     */
    public boolean canContinue() {
        boolean isValid;
        while (true) {
            try {
                isValid = (1 == scanner.nextInt());
                break;
            } catch(InputMismatchException exception) {
                ProductManagementLogger.loggerError(exception);
                scanner.nextLine();
            }
        }
        return isValid;
    }

    /**
     * This method used to show the option
     */
    public void showOption() {
        try {
            do {
                System.out.println(Constant.DEALER_MENU);
                int value = scanner.nextInt();
                switch(value) {
                    case 1:
                        createDealer();
                        break;
                    case 2:
                        displayDealer();
                        break;
                     case 3:
                        removeDealer();
                        break;
                    case 4:
                        updateDealer();
                        break;
                    case 5:
                         dispalyDealerById();
                         break;
                    case 6:
                        System.out.println(Constant.THANK_YOU);
                        break;
                    default :
                        ProductManagementLogger.loggerInfo(Constant.ENTER_VALID_VALUE);
                }
                System.out.println(Constant.CONTINUE);
            } while(canContinue());
        } catch(Exception exception) {
            ProductManagementLogger.loggerError(Constant.INVALID_OPTION);
        }  
    }

    /**
     * Used to get the input details for the dealer by id
     */ 
    public void dispalyDealerById()  {
        try { 
            System.out.println(Constant.ENTER_THE_ID);
            int dealerId = scanner.nextInt();
            Dealer dealer = dealerService.getDealerById(dealerId);
            System.out.println(dealer);
                dealer.getProducts().forEach(product -> {System.out.println(product);
                                                         System.out.println(product.getManufacturer());});
        } catch(Exception exception) {
            ProductManagementLogger.loggerError(Constant.NO_DEALERS);
        } 
    }

    /**
     * Used to get the input details for create the dealer 
     */ 
    public void createDealer() {
        try {
            String company = getCompany();
            String location = getLocation();
            Dealer dealer = dealerService.createDealer(company, location);
            System.out.println(dealer.getCompany() + " company inserted");  
        } catch(ProductManagementException productManagementException) {
            ProductManagementLogger.loggerError(productManagementException.getMessage());
        }
    }
    
    /**
     * Used to get the validated company from the user
     *
     * @return company - the validated company
     */
    public String getCompany() {
         String name = "";
         boolean result = true;
         do {
             try {
                 name = getString(Constant.ENTER_THE_COMPANY);
                 result = Validation.containsOnlyAlphabet(name); 
                 if(!result) {
                     throw new ProductManagementException(Constant.INVALID_OPTION);
                 } 
             } catch(ProductManagementException productManagementException) {
                 ProductManagementLogger.loggerError(productManagementException);
             }
         } while (!result); 
         return name;
     }    

    /**
     * Used to get the validated company from the user
     *
     * @return company - the validated company
     */
    public String getLocation() {
         String location = "";
         boolean result = true;
         do {
             try {
                 location = getString(Constant.ENTER_THE_LOCATION );
                 result = Validation.containsOnlyAlphabet(location); 
                 if(!result) {
                     throw new ProductManagementException(Constant.INVALID_OPTION);
                 } 
             } catch(ProductManagementException productManagementException) {
                 ProductManagementLogger.loggerError(productManagementException);
             }
         } while (!result); 
         return location;
     }  
 
    /**
     * This method used to display the dealer details
     */
    public void displayDealer() {
        try {
            List<Dealer> dealers = dealerService.getDealers();
            dealers.forEach(dealer -> System.out.println(dealer));
        } catch(Exception exception) {
            ProductManagementLogger.loggerError(Constant.NO_DEALERS);
        }
    }

    /**
     * Remove the dealer based on the dealer id
     */ 
    public void removeDealer() {
        try {
            System.out.println(Constant.ENTER_THE_ID);
            int id = getInt();
            if (dealerService.removeDealerById(id)) {
                ProductManagementLogger.loggerInfo(Constant.DELETED);
            } else {
                throw new ProductManagementException(Constant.INVALID_OPTION); 
            }
        }
        catch(ProductManagementException productManagementException) {
            ProductManagementLogger.loggerError(productManagementException);
        }
    }

    /**
     * Update the dealer attributes based on the dealer id
     */
    public void updateDealer() {
        do {
            try {
                System.out.println(Constant.ENTER_THE_ID);
                int dealerId = getInt();
                if (dealerService.isValidId(dealerId)) {
                    System.out.println(Constant.ENTER_UPDATE_OPTION);
                    int choice = getInt();
                    switch (choice) {
                        case 1 :
                            String company = getString(Constant.ENTER_THE_COMPANY);
                            dealerService.updateDealerById(company, dealerId, choice);
                            break;
                        case 2 :
                            String location = getString(Constant.ENTER_THE_LOCATION );
                            dealerService.updateDealerById(location, dealerId, choice);
                            break;
                        default : 
                            ProductManagementLogger.loggerError(Constant.INVALID_OPTION);
                            break;
                    }
                } 
            } catch (ProductManagementException productManagementException) {
                ProductManagementLogger.loggerError(productManagementException);
            }
            System.out.println(Constant.CONTINUE);
        } while(canContinue()); 
    } 
}  