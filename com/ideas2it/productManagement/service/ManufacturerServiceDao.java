package com.ideas2it.productManagement.service;

import java.util.List;

import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

/**
  * This interface is used to call a ManufacturerServiceImpl class metohds
  *
  * @author Thangapandiyan
  * @version 1.0 
  */
public interface ManufacturerServiceDao {
  
    /**
     * This method used for create manufacturer
     */
    public Manufacturer createManufacturer(String brand, String place) throws ProductManagementException;
    
    /**
     * Used to remove the manufacturer details by using the id 
     *
     * @param id - an manufacturer id to be removed   
     * @return - true if any elements were removed
     *           false if any elements not removed
     */
    public boolean removeManufacturerById(int id) throws ProductManagementException;

    /**
     * It is used to update the manufacturer details.
     *
     * @param details - the value to be update
     * @param id - an employee id to be update
     * @param choice - a choice for which attribute need to be update 
     * @return true - the values update
     */
    public boolean updateManufacturerById(String details, int id ,int choice) throws ProductManagementException;

    /**
     * This method used to get all manufacturer details
     */
    public List<Manufacturer> getManufacturerDetails() throws ProductManagementException;
    
    /**
     * This method used to get manufacturer details based on user given id
     *
     * @param id - to get id details
     *
     * @return manufacturerDetails - if id valid
     */
    public Manufacturer getManufacturerById(int id) throws ProductManagementException;
    
    /**
     * It is used to get manufacturer details by manufacturerId
     *
     * @param manufacturerId - an manufacturer id to be checked 
     * @return - true if the prodcut id contain
     *           false if the prodcut id contain     
     */ 
    public boolean isValidId(int manufacturerId) throws ProductManagementException;
}