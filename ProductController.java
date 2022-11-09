import java.text.ParseException;  
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException; 
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.Scanner;

import ideas2it.project.service.*;
import ideas2it.project.constant.*;
//import ideas2it.project.constant.*;
import ideas2it.project.util.*;
//import ideas2it.project.util.*;
/**
 * Controller is used for all input and output functions related to product operation
 */
public class ProductController {
    private Scanner scanner = new Scanner(System.in);
    public ProductService productService = new ProductService();

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
        }
        catch(InputMismatchException inputMismatchException) {
            System.out.println("invalid");
            scanner.nextLine();
            result = false;
        }
        } while(!result);
        return value;
    }
    
    /**
     * Read the float value from the user
     *
     * @return the specified value
     */
    public float getFloat() {
        return scanner.nextFloat();
    }

    public static void main(String[] args) {
        ProductController productController = new ProductController();
        productController.showOption();
    }
       
    /**
     * Used to show menu
     */
    public void showOption() throws InputMismatchException {
        do {
            System.out.println(Constant.WATCH_MENU);
            int value = getInt();
            switch(value) {
            case 1:
                createWatch();
                break;

            case 2:
                displayWatch();
                break;

            case 3:
                getWatch();
                break;

            case 4:
                removeWatch();
                break;

            case 5:
                updateWatch();
                break; 

            case 6:
                searchWatch();
                break; 

            case 7:
                getString("Thank you");
                break; 

            default :
                getString(Constant.INVALID_OPTION);
            }   
           // System.out.println(Constant.CONTINUE);
        } while(canContinue()); 
    }    
    /*public Date a() {
        Date date = null;
    do {
        try {
           date = DateUtil.getDate(getDate());
        } 
        catch (ParseException parseException) {
            System.out.println();
        }
    } while (date == null);
        return date;
    }*/
    /**
     * Used to get the input details for the watch and create the watch 
     */ 
    public void createWatch() {
        String name = getName();
        Colour colour = getValidColour();
        int price = getPrice();
        String style = getStyle();
        String type = getType();
        Date date = DateUtil.getDate(getDate());
        int age = DateUtil.find(date);
        System.out.println(Constant.PRODUCT_CREATED 
            + productService.createWatch(name, colour, price, style, type, date, age)); 
    }
  
    /**
     * Used to read the value for continue the process
     *
     * @return - true  if the value is one
     *           false otherwise
     */
    public boolean canContinue() {
        return 1 == scanner.nextInt();
    }
      
    /**
     * Used to list the watch details 
     */
    public void getWatch() {
        String productId = getString("Constant.ENTER _THE_PRODUCT_ID");
        Watch watch = productService.getWatchById(productId);
        if (null == watch) {
            System.out.println(Constant.INVALID_OPTION);
        } else {
            System.out.println(watch);
        }
    }
                    
    /**
     * Used to display the watch details
     */
    public void displayWatch() {
        for (Map.Entry feature : productService.getWatches().entrySet()) {
            System.out.println(feature.getValue().toString());
        }
    }  

    /**
     * Removes the watch based on the product id
     */ 
    public void removeWatch() {
        String id = getString(Constant.ENTER_THE_PRODUCT_ID);
        if (productService.removeWatchById(id)) {
            System.out.println("Succesfully deleted");
        }
        System.out.println(Constant.INVALID_OPTION);
    }

    /**
     * Update the watch based on the product id
     */
    public void updateWatch() {
        String productId = getString(Constant.ENTER_THE_PRODUCT_ID);
        if (productService.isIdExist(productId)) {
            System.out.println(Constant.WATCH_ATTRIBUTE_MENU);
            String choice = getString(Constant.ENTER_THE_UPDATE_OPTION);
            switch (choice) {
                case "1" :
                    String name = getString(Constant.ENTER_THE_NAME);
                    productService.updateWatch(name, productId, choice);
                    break;

                case "2" :
                    String colour = getString(Constant.ENTER_THE_COLOUR );
                    productService.updateWatch(colour, productId, choice);
                    break;

                case "3" :
                    String price =  getString(Constant.ENTER_THE_PRICE);
                    productService.updateWatch(price, productId, choice);
                    break;  

                case "4" :
                    String style = getString(Constant.ENTER_THE_WATCH_STYLE);
                    productService.updateWatch(style, productId, choice);
                    break;  

                case "5" :
                    String type = getString(Constant.ENTER_THE_WATCH_TYPE);
                    productService.updateWatch(type, productId, choice);
                    break;

                default :
                    System.out.println(Constant.INVALID_OPTION);
                    break;
                }
            } else {
            System.out.println(Constant.INVALID_OPTION);
        }
    }  
   
    /**
     * Used to search the watch by brand name.
     */
    public void searchWatch() {
        String brandName = getString(Constant.ENTER_THE_NAME);
        List<Watch> watches = productService.searchWatchByName(brandName);
        System.out.println(watches);
    }
  
    /**
     * Used to get the validated colour from the user
     *
     * @return colour - the validated colour
     */
    public Colour getValidColour() {
        boolean isValid = true;
        Colour result = null;
        System.out.println(Constant.WATCH_COLOUR_LIST);
        while (isValid) {
                int value = getInt();
                result = Colour.getColour(value);
                if (result == null) {
                    System.out.println(Constant.INVALID_OPTION);
                } else {
                    isValid = false;
                } 
        }
        return result;
    }
     
   /**
    * Used to get the validated price from the user
    * 
    * @return  the validated price
    */
    public int getPrice() throws InputMismatchException   {
        int price = 0;
        boolean result = true;
        System.out.println(Constant.PRICE_LIST);
            price = getInt();
           // try {
                result = Validation.isValidPrice(price);
           // }
            //catch(InputMismatchException e) {
               // System.out.println("invalid" + e ); 
                //getPrice() ;
         //  }// 
        return price; 
    } 
     
    /**
     * Used to get the validated name from the user
     *
     * @return name - the validated name
     */
    public String getName() {
         System.out.println(Constant.BRAND_LIST);
         String name;
         boolean result;
         do {
             name = getString(Constant.ENTER_THE_NAME);
             result = Validation.containsOnlyAlphabet(name); 
             if(!result) {
                 System.out.println(Constant.INVALID_OPTION);
             } 
         } while (!result); 
         return name;
     }
  
    /**
     * Used to get the validated price from the user
     *
     * @return style - the validated style
     */
    public String getStyle() {
         System.out.println(Constant.STYLE_LIST);
         String style;
         boolean result;
         do {
             style = getString(Constant.ENTER_THE_WATCH_STYLE);
             result = Validation.containsOnlyAlphabet(style);
             if(!result) {
                 System.out.println(Constant.INVALID_OPTION);
             } 
         } while (!result); 
         return style;
    }
   
    /**
     * Used to get the validated type from the user
     *
     * @return type - the validated type
     */
    public String getType() {
        System.out.println(Constant.WATCH_TYPE_LIST);
        String type = "";
        boolean result = true;
        do {
            try {
                type = getString(Constant.ENTER_THE_WATCH_TYPE);
                result = Validation.containsOnlyAlphabet(type);
             }
             catch (Exception exception) {
                 System.out.println(Constant.INVALID_OPTION);
                 scanner.nextLine();
             } 
        } while (!result); 
        return type;
    }
 
    /**
     * Used to get the  date from the user
     *
     * @return the validated date
     */
    public String getDate() {
        String result = "";
        do {
            try {
                result = getString(Constant.ENTER_THE_DATE);
                validation.validDate(result);
            }
            catch(Exception exception) {
                System.out.println("Invalid");
                scanner.nextLine();
            } 
        } while("".equals(result));
        return result; 
    }
}    