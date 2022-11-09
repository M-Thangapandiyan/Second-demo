package com.ideas2it.productManagement.util.databaseConnection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.productManagement.model.Product;
import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.model.BaseModel;

/**
 * This class used for database connection 
 *
 * @author Thangapandiyan
 * @version 1.0 
 */
public class DataBaseConnection {
	private static SessionFactory sessionFactory;
    private static DataBaseConnection dataBaseConnection;
  
    private DataBaseConnection () {
    	sessionFactory = new Configuration().configure("hibernate.cfg.xml")
    			.addAnnotatedClass(BaseModel.class)
    			.addAnnotatedClass(Product.class)
    			.addAnnotatedClass(Manufacturer.class)
    			.addAnnotatedClass(Dealer.class)
    			.buildSessionFactory();
    }
  
   /**
    * This method is uesd to create object
    * @return sessionFactory - if object created 
    */
    public static SessionFactory getInstance() {
        if (sessionFactory == null) {         
        	dataBaseConnection = new DataBaseConnection();
        } 
        return sessionFactory;
    }
   
}