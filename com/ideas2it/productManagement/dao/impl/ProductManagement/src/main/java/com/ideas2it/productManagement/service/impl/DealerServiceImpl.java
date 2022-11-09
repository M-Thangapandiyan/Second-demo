package com.ideas2it.productManagement.service.impl;

import java.util.List;

import com.ideas2it.productManagement.dao.DealerDao;
import com.ideas2it.productManagement.dao.impl.DealerDaoImpl;
import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.service.DealerService;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

public class DealerServiceImpl implements DealerService {
    private DealerDao dealerDaoImpl = new DealerDaoImpl();

    /**
     * {@inherticDoc}
     */
    public Dealer createDealer(String company, String location) throws ProductManagementException {
        Dealer dealer = new Dealer(company, location);
        return dealerDaoImpl.insertDealer(dealer); 
    }

    /**
     * {@inherticDoc}
     */
    public List<Dealer> getDealers() throws ProductManagementException {
        try {
            return dealerDaoImpl.retriveDealers();
        } catch(Exception exception) {
            throw new ProductManagementException("Invalid");
        }
    }
    
    /**
     * {@inherticDoc}
     */
    public boolean removeDealerById(int id) throws ProductManagementException  {
        return dealerDaoImpl.deleteDealerById(id);  
    }
    
    /**
     * {@inherticDoc}
     */
    public boolean updateDealerById(Dealer dealer) throws ProductManagementException {
        dealerDaoImpl.updateDealerById(dealer);
        return true;
    }
     
    /**
     * {@inherticDoc}
     */
    public boolean isValidId(int dealerId) throws ProductManagementException {
        if(null != getDealerById(dealerId)) {
            return true;
        }
        return false;
    }  
    
    /**
     * {@inherticDoc}
     */
    public Dealer getDealerById(int dealerId) throws ProductManagementException {
        return dealerDaoImpl.retriveDealerById(dealerId);
    }
    
    /**
     * {@inherticDoc}
     */
    public List<Dealer> getDealerInformation() throws ProductManagementException {
        List<Dealer> dealers = dealerDaoImpl.retriveDealerInformation();
        if(null == dealers) {
            throw new ProductManagementException("Not Found");
        }
        return dealers;
    }
}
     
