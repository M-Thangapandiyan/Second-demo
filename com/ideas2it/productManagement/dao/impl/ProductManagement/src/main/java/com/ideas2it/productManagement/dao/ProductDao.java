package com.ideas2it.productManagement.dao;

import java.util.Date;
import java.util.List;

import com.ideas2it.productManagement.model.Product;
import com.ideas2it.productManagement.util.exception.ProductManagementException;
 
/**
 * This interface is used to call a productDaoImpl class methods
 *
 * @author Thangapandiyan
 * @version 1.0 
 */
public interface ProductDao {

    /**
     * This method used to insert the product details from database
     *
     * @param product - to insert the database 
     * @return storedValue - if values are inserted  
     */
    public Product insertProduct(Product product) throws ProductManagementException; 
  
    /**
     * This method used to delete the product details by id from database
     *
     * @param id - an product id to be removed   
     * @return - true if any elements were removed
     *           false if any elements not removed
     */
    public boolean deleteProductById(int id) throws ProductManagementException;

    /**
     * This method used to delete the product details  by id from database
     *
     * @param id - to update the id values 
     * @param product - to update the product 
     */
    public Product updateProductById(Product product) throws ProductManagementException;
    
    /**
     * This method used to retrieve the product details from database 
     */
    public List<Product> retriveProducts() throws ProductManagementException;

    /**
     * This method used to retrieve the product details by id from database
     *
     * @param id - to get details
     * @return product - if id match.
     */
    public Product retriveProductById(int id) throws ProductManagementException;

    /**
     * This method used to get the  product details for the given product id from database.
     *
     * @param valueOne - to get details
     * @param valueTwo - to get details
     * @return products - valueOne between valueTwo
     */
    public List<Product> retrieveProductBetweenDate(Date startDate, Date endDate) throws ProductManagementException;

    /**
     * This method used to search the product details by id from database.
     *
     * @param updateValue - to get details
     * @return products - if id is match.
     */
    public List<Product> searchProductById(String value) throws ProductManagementException;
    
    /**
     * This method used to retrieve the product details by id from database
     *
     * @param result - to get details
     * @return products - if id's are match.
     */
    public List<Product> retriveProductByIds(String[] ids) throws ProductManagementException;
    
    /**
     * This method used to retrieve the last id from database
     *
     */
    public int getLastId() throws ProductManagementException;

}