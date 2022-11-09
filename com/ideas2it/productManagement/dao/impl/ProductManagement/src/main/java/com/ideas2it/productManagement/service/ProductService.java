package com.ideas2it.productManagement.service;

import java.util.Date;
import java.util.List;

import com.ideas2it.productManagement.model.Product;
import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.util.enumaration.Colour;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

 /**
  * This interface is used to call a ProductServiceImpl class methods
  *
  * @author Thangapandiyan
  * @version 1.0 
  */
public interface ProductService {
     
    /**
     * Create the product and added the product to the map with 
     * product id as key and return the created product
     *
     * @param name - it is used to store the name value from the user   
     * @param colour - it is used to store the colour value from the user  
     * @param price - it is used to store the price value from the user  
     * @param dateOfManufacture - it is used to store the dateOfManufacture value from the user
     *
     * @return product - if values are updateOfManufactured
     */
    public Product createProduct(String name, int price, Colour colour, Date dateOfManufacture, Manufacturer manufactureId, Dealer dealerId) throws ProductManagementException; 
     
    /**
     * This method used to get product details 
     *
     * @return diplayProduct details
     */
    public List<Product> getProducts() throws ProductManagementException;
    
    /**
     * It used to create the product id 
     *
     * @return an id with prefix as WATCH00
     */
     public String createId() throws ProductManagementException;
    
    /**
     * It used to get the product details for the given product id.
     *
     * @param productId - to get details
     * @retrun productId deatails - if employee id is valid
     */
    public Product getProductById(int id) throws ProductManagementException;
   
    /**
     * It is used to get product details by productId
     *
     * @param productId - an manufacturer id to be checked 
     * @return - true if the prodcut id contain
     *           false if the prodcut id contain     
     */ 
    public boolean isValidId(int productId) throws ProductManagementException;
     
    /**
     * Used to remove the product details by using the id 
     *
     * @param id - an product id to be removed   
     * @return - true if any elements were removed
     *           false if any elements not removed
     */
    public boolean removeProductById(int id) throws ProductManagementException;

    /**
     * It is used to update the product details.
     *
     * @param details - the value to be update
     * @param id - an employee id to be update
     * @param choice - a choice for which attribute need to be update 
     * @return true - the values update
     */
    public boolean updateProductById(Product product) throws ProductManagementException;
    
    /**
     * Used to search the name in the object
     *
     * @param productName - to be search
     * @return product names - if the name is present
     */
    public List<Product> searchProduct(String value) throws ProductManagementException;
      
    /**
     * This method is used to get given range values
     *
     * @param startDate - to get the details from 
     * @param endDate - to get the details till 
     *
     * @return products - if the range value is present
     */
    public List<Product>  getProductBetweenDate(Date startDate, Date endDate) throws ProductManagementException;
   
    /**
     * This method is uesd to get given id values
     *
     * @param  result - to get the details from 
     *
     * @return products - if the range value is present
     */
    public List<Product> getProductByIds(String[] result) throws ProductManagementException;
}