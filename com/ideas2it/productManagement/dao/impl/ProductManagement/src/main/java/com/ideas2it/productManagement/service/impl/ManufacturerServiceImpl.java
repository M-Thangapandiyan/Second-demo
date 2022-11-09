 package com.ideas2it.productManagement.service.impl;

import java.util.List;

import com.ideas2it.productManagement.dao.ManufacturerDao;
import com.ideas2it.productManagement.dao.impl.ManufacturerDaoImpl;
import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.service.ManufacturerService;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

/** 
 * This class gets the input from Manufacturer controller and return back after 
 * business logic related to Manufacturer 
 */
public class ManufacturerServiceImpl implements ManufacturerService {
    ManufacturerDao manufacturerDao = new ManufacturerDaoImpl();

    /**
     * {@inherticDoc}
     */
    public Manufacturer createManufacturer(String brand, String place) throws ProductManagementException { 
        Manufacturer manufacturer = new Manufacturer(brand, place);
        return manufacturerDao.insertManufacturer(manufacturer); 
    }
   
    /**
     * {@inherticDoc}
     */
    public List<Manufacturer> getManufacturers() throws ProductManagementException {
        try {
            return manufacturerDao.retriveManufacturer();
        } catch(Exception exception) {
            throw new ProductManagementException("Invalid");
        }
    }
   
    /**
     * {@inherticDoc}
     */
    public boolean removeManufacturerById(int id) throws ProductManagementException  {
        return manufacturerDao.deleteManufacturerById(id);  
    }
    
    /**
     * {@inherticDoc}
     */
    public boolean updateManufacturerById(Manufacturer manufacturer) throws ProductManagementException {
        manufacturerDao.updateManufacturerById(manufacturer);
        return true;
    }  
    
    /**
     * {@inherticDoc}
     */
    public boolean isValidId(int manufacturerId) throws ProductManagementException {
        if(null != getManufacturerById(manufacturerId)) {
            return true;
        }
        return false;
    }  
    
    /**
     * {@inherticDoc}
     */
    public Manufacturer getManufacturerById(int id) throws ProductManagementException {
        Manufacturer manufacturer = manufacturerDao.retriveManufacturerById(id);
        if(0 == manufacturer.getId()) {
            throw new ProductManagementException("Not Found");
        }
        return manufacturer;
    }
}
