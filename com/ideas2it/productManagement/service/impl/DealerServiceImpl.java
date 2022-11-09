package com.ideas2it.productManagement.service.impl;

import java.util.List;

import com.ideas2it.productManagement.dao.DealerDao;
import com.ideas2it.productManagement.dao.impl.DealerDaoImpl;
import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.service.DealerServiceDao;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

public class DealerServiceImpl implements DealerServiceDao {
    private DealerDao dealerDaoImpl = new DealerDaoImpl();

    /**
     * {@inherticdoc}
     */
    public Dealer createDealer(String company, String location) throws ProductManagementException {
        Dealer dealer = new Dealer(company, location);
        return dealerDaoImpl.insertDealer(dealer); 
    }

    /**
     * {@inherticdoc}
     */
    public List<Dealer> getDealers() throws ProductManagementException {
        try {
            return dealerDaoImpl.retriveDealers();
        } catch(Exception exception) {
            throw new ProductManagementException("Invalid");
        }
    }
    
    /**
     * {@inherticdoc}
     */
    public boolean removeDealerById(int id) throws ProductManagementException  {
        return dealerDaoImpl.deleteDealerById(id);  
    }
    
    /**
     * {@inherticdoc}
     */
    public boolean updateDealerById(String details, int id, int choice) throws ProductManagementException {
        Dealer dealer = getDealerById(id);
        switch(choice) {
            case 1 :
                dealer.setCompany(details);
                break;
            case 2 :
                dealer.setLocation(details);
                break;
            default :
                throw new ProductManagementException("Invalid option");
        }
        dealerDaoImpl.updateDealerById(dealer);
        return true;
    }
     
    /**
     * {@inherticdoc}
     */
    public boolean isValidId(int dealerId) throws ProductManagementException {
        if(null != getDealerById(dealerId)) {
            return true;
        }
        return false;
    }  
    
    /**
     * {@inherticdoc}
     */
    public Dealer getDealerById(int dealerId) throws ProductManagementException {
        return dealerDaoImpl.retriveDealerById(dealerId);
    }
    
    /**
     * {@inherticdoc}
     */
    public List<Dealer> getDealerInformation() throws ProductManagementException {
        List<Dealer> dealers = dealerDaoImpl.retriveDealerInformation();
        if(null == dealers) {
            throw new ProductManagementException("Not Found");
        }
        return dealers;
    }
}
     