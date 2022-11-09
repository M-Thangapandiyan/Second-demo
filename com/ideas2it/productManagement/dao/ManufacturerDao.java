package com.ideas2it.productManagement.dao;

import java.util.List;

import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

 /**
  * This interface is used to call a ManufacturerDaoImpl class metohds
  *
  * @author Thangapandiyan
  * @version 1.0 
  */
public interface ManufacturerDao {
    
    /**
     * This method used to insert Manufacturer details
     *
     * @retrun storedvalue - if values are inserted  
     */
    public Manufacturer insertManufacturer(Manufacturer manufacturer) throws ProductManagementException;
     
    /**
     * This method used to delete the manufacturers details by id from database
     *
     * @param id - an manufacturer id to be removed   
     * @return - true if any elements were removed
     *           false if any elements not removed
     */
    public boolean deleteManufacturerById(int id) throws ProductManagementException;

    /**
     * This method used to delete the manufacturer details by id from database
     *
     * @param id - to update the id values 
     * @rparam manufacturer - to update 
     */
    public Manufacturer updateManufacturerById(Manufacturer manufacturer) throws ProductManagementException;

    /**
     * It used to display the manufacturer details.
     *
     * @retrun manufactureDetails - if id is valid 
     */
   public List<Manufacturer> retriveManufacturer() throws ProductManagementException;
  
    /**
     * It is used to get the details based on user given id
     *
     * @retrun manufactureDetails - if id is valid 
     */
   public Manufacturer retriveManufacturerById(int manufacturerId) throws ProductManagementException;
}