package com.ideas2it.productManagement.service.impl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import com.ideas2it.productManagement.dao.ProductDao;
import com.ideas2it.productManagement.dao.impl.ProductDaoImpl;
import com.ideas2it.productManagement.model.Product;
import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.service.impl.ProductServiceImpl;
import com.ideas2it.productManagement.service.ProductServiceDao;
import com.ideas2it.productManagement.util.Constant;
import com.ideas2it.productManagement.util.DateUtil;
import com.ideas2it.productManagement.util.Validation;
import com.ideas2it.productManagement.util.enumaration.Colour;
import com.ideas2it.productManagement.util.exception.ProductManagementException;
import com.ideas2it.productManagement.util.logger.ProductManagementLogger;

/** 
 * ProductService class gets the input from controller and return back after 
 * business logic related to Product 
 *
 * @author Thangapandiyan
 * @version 1.0 
 */
public class ProductServiceImpl implements ProductServiceDao {
    private ProductDao productDaoImpl = new ProductDaoImpl();

    /**
     * {@inherticdoc}
     */
    public Product createProduct(String name, int price, Colour colour, Date dateOfManufacture, Manufacturer manufactureId, Dealer dealerId) throws ProductManagementException { 
        String code = createId();
        Product product = new Product(code, name, price, colour, dateOfManufacture);
        product.setManufacturer(manufactureId);
        product.setDealer(dealerId);
        return productDaoImpl.insertProduct(product); 
    }
 
    /**
     * {@inherticdoc}
     */
    public List<Product> getProducts() throws ProductManagementException {
    	List<Product> products = productDaoImpl.retriveProducts();
        if(null == products) {
            throw new ProductManagementException("while get products not found");
        }
        return products;
    }
    
    /**
     * {@inherticdoc}
     */
    public String createId() throws ProductManagementException {
        int productCode = productDaoImpl.getLastId();
        return "PRODUCT00" + Integer.toString(++productCode);
    }
    
    /**
     * {@inherticdoc}
     */
    public boolean isValidId(int productId) throws ProductManagementException {
        if(null != getProductById(productId)) {
            return true;
        }
        return false;
    }
 
    /**
     * {@inherticdoc}
     */
    public boolean removeProductById(int id) throws ProductManagementException  {
        if(!productDaoImpl.deleteProductById(id)) {
        	throw new ProductManagementException("id is not deleted");   
        }
        return true;
    }
  
    
    /**
     * {@inherticdoc}
     */
    public boolean updateProductById(String details, int id, int choice) throws ProductManagementException {
        Product product = getProductById(id);
        switch(choice) {
            case 1 :
                product.setName(details);
                break;
            case 2 :
                Colour colour = Colour.valueOf(details);
                product.setColour(colour);
                break;
            case 3:
                int price = Integer.parseInt(details);
                product.setPrice(price);
                break; 
            case 4 :
                Date dateOfManufacture = DateUtil.getDate(details);
                product.setDate(dateOfManufacture);
                break;
            default :
                throw new ProductManagementException("Enter valid update option");
        }
        if(null == productDaoImpl.updateProductById(product)) {
        	throw new ProductManagementException("not updated");   
        }
        return true;
    }
  
    /**
     * {@inherticdoc}
     */
    public List<Product> searchProductById(String value) throws ProductManagementException {
        List<Product> products = productDaoImpl.searchProductById(value);
        if(null == products) {
        	 throw new ProductManagementException("while search id not found enter the valid id");
        }
        return products;
    }

    /**
     * {@inherticdoc}
     */
    public List<Product> getProductBetweenDate(Date startDate, Date endDate) throws ProductManagementException {
    	List<Product> products = productDaoImpl.retrieveProductBetweenDate(startDate, endDate);
    	if(null == products) {
    		 throw new ProductManagementException("while get date between details not found enter the valid date's");
        }
        return products;
    } 
   
    /**
     * {@inherticdoc}
     */
    public Product getProductById(int id) throws ProductManagementException {
    	Product product = productDaoImpl.retriveProductById(id);
    	if(null == product) {
    		 throw new ProductManagementException("while get id deatails is not found enter the valid id");
        }
        return product;
    }
    
    /**
     * {@inherticdoc}
     */
    public List<Product> getProductByIds(String result) throws ProductManagementException {
    	List<Product> products = productDaoImpl.retriveProductByIds(result);
        if(null == products) {
            throw new ProductManagementException("while get Id's not found enter the valid id's");
        }
        return products;
    }
}        