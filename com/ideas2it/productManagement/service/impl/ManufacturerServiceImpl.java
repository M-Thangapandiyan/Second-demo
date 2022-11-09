 package com.ideas2it.productManagement.service.impl;

import java.util.List;

import com.ideas2it.productManagement.dao.ManufacturerDao;
import com.ideas2it.productManagement.dao.impl.ManufacturerDaoImpl;
import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.service.ManufacturerServiceDao;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

/** 
 * This class gets the input from Manufacturercontroller and return back after 
 * business logic related to Manufacturer 
 */
public class ManufacturerServiceImpl implements ManufacturerServiceDao {
    ManufacturerDao manufacturerDao = new ManufacturerDaoImpl();

    /**
     * {@inherticdoc}
     */
    public Manufacturer createManufacturer(String brand, String place) throws ProductManagementException { 
        Manufacturer manufacturer = new Manufacturer(brand, place);
        return manufacturerDao.insertManufacturer(manufacturer); 
    }
   
    /**
     * {@inherticdoc}
     */
    public List<Manufacturer> getManufacturerDetails() throws ProductManagementException {
        try {
            return manufacturerDao.retriveManufacturer();
        } catch(Exception exception) {
            throw new ProductManagementException("Invalid");
        }
    }
   
    /**
     * {@inherticdoc}
     */
    public boolean removeManufacturerById(int id) throws ProductManagementException  {
        return manufacturerDao.deleteManufacturerById(id);  
    }
    
    /**
     * {@inherticdoc}
     */
    public boolean updateManufacturerById(String details, int id, int choice) throws ProductManagementException {
        Manufacturer manufacturer = getManufacturerById(id);
        switch(choice) {
            case 1 :
                manufacturer.setBrand(details);
                break;
            case 2 :
                manufacturer.setPlace(details);
                break;
            default :
                throw new ProductManagementException("Invalid option");
        }
        manufacturerDao.updateManufacturerById(manufacturer);
        return true;
    }  
    
    /**
     * {@inherticdoc}
     */
    public boolean isValidId(int manufacturerId) throws ProductManagementException {
        if(null != getManufacturerById(manufacturerId)) {
            return true;
        }
        return false;
    }  
    
    /**
     * {@inherticdoc}
     */
    public Manufacturer getManufacturerById(int id) throws ProductManagementException {
        Manufacturer manufacturer = manufacturerDao.retriveManufacturerById(id);
        if(0 == manufacturer.getId()) {
            throw new ProductManagementException("Not Found");
        }
        return manufacturer;
    }
}