package com.ideas2it.productManagement.dao;

import java.util.List;

import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

/**
 * This interface is used to call a DealerDaoImpl class metohds
 *
 * @author Thangapandiyan
 * @version 1.0
 */
public interface DealerDao {

	/**
	 * This method used to insert dealer details
	 *
	 * @retrun storedvalue - if values are inserted
	 */
	public Dealer insertDealer(Dealer dealer) throws ProductManagementException;

	/**
	 * This method used to delete the dealers details by id from database
	 *
	 * @param id - an dealer id to be removed
	 * @return - true if any elements were removed false if any elements not removed
	 */
	public boolean deleteDealerById(int id) throws ProductManagementException;

	/**
	 * This method used to delete the dealer details by id from database
	 *
	 * @param id - to update the id values
	 * @rparam dealer - to update
	 */
	public Dealer updateDealerById(Dealer dealer) throws ProductManagementException;

	/**
	 * Tis method used to display all the dealer details
	 *
	 * @retrun dealerDetails - all the dealer details
	 */
	public List<Dealer> retriveDealers() throws ProductManagementException;

	/**
	 * This method used to get the details based on user given id
	 *
	 * @retrun dealerDetails - the dealer details
	 */
	public Dealer retriveDealerById(int dealerId) throws ProductManagementException;

	/**
	 * It is used to get the dealer details
	 */
	public List<Dealer> retriveDealerInformation() throws ProductManagementException;
}