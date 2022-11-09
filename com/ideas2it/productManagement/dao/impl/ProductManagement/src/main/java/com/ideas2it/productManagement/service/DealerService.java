package com.ideas2it.productManagement.service;

import java.util.List;

import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

 /**
  * This interface is used to call a DealerServiceImpl class methods
  */
public interface DealerService{

    /**
     * This method used for create dealer
     */
    public Dealer createDealer(String company, String location) throws ProductManagementException;

    /**
     * Used to remove the dealer details by using the id 
     *
     * @param id - an dealer id to be removed   
     * @return - true if any elements were removed
     *           false if any elements not removed
     */
    public boolean removeDealerById(int id) throws ProductManagementException;

    /**
     * It is used to update the dealer details.
     *
     * @param details - the value to be update
     * @param id - an employee id to be update
     * @param choice - a choice for which attribute need to be update 
     * @return true - the values update
     */
    public boolean updateDealerById(Dealer dealer) throws ProductManagementException;

    /**
     * This method used to get all dealers details
     */
    public List<Dealer> getDealers() throws ProductManagementException;
    
    /**
     * This method used to get dealers details based on user given id
     *
     * @param dealerId - to get id details
     *
     * @return dealerDetails - if id valid
     */
    public Dealer getDealerById(int dealerId) throws ProductManagementException;
    
    /**
     * It is used to get product details by productId
     *
     * @param dealerId - an dealer id to be checked 
     * @return - true if the dealer id contain
     *           false if the dealer id contain     
     */ 
    public boolean isValidId(int dealerId) throws ProductManagementException;
    
    /**
     * It is used to get product details
     *
     * @return - dealer dealer details     
     */ 
    public List<Dealer> getDealerInformation() throws ProductManagementException;
}