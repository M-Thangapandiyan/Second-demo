package com.ideas2it.productManagement.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ideas2it.productManagement.dao.DealerDao;
import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.model.Product;
import com.ideas2it.productManagement.util.Constant;
import com.ideas2it.productManagement.util.databaseConnection.DataBaseConnection;
import com.ideas2it.productManagement.util.exception.ProductManagementException;
import com.ideas2it.productManagement.util.logger.ProductManagementLogger;

/**
 * This class used get the input from dealer service to dispaly the details from
 * the database
 *
 * @author Thangapandiyan
 * @version 1.0
 */
public class DealerDaoImpl implements DealerDao {
	private SessionFactory sessionFactory;
	Session session = null;
	Transaction transaction = null;

	/**
	 * {@inherticDoc}
	 */
	public Dealer insertDealer(Dealer dealer) throws ProductManagementException {
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(dealer);
			transaction.commit();
			if(0 == dealer.getId()) {
				throw new ProductManagementException(" dealer not inserted");
			}
			return dealer;
		} catch (HibernateException exception) {
			throw new ProductManagementException(exception.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inherticDoc}
	 */
	public List<Dealer> retriveDealers() throws ProductManagementException {
		List<Dealer> dealers ;
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Dealer> criteriaQuery = builder.createQuery(Dealer.class);
			Root<Dealer> root = criteriaQuery.from(Dealer.class);
			criteriaQuery.select(root).where(builder.equal(root.get("isDeleted"), false));
			Query<Dealer> query = session.createQuery(criteriaQuery);
			dealers = query.getResultList();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw new ProductManagementException(Constant.NO_DEALERS_DETAILS);
		} finally {
			session.close();
		}
		return dealers;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteDealerById(int id) throws ProductManagementException {
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaUpdate<Dealer> criteriaUpdate = builder.createCriteriaUpdate(Dealer.class);
			Root<Dealer> root = criteriaUpdate.from(Dealer.class);
			criteriaUpdate.set("isDeleted",id).where(root.get("id").in(id));
			int row = session.createQuery(criteriaUpdate).executeUpdate();
			return row == 1;
			} catch (Exception sqlException) {
			throw new ProductManagementException(sqlException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Dealer updateDealerById(Dealer dealer) throws ProductManagementException {
		Transaction transaction = null;
		try {
			
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(dealer);
			transaction.commit();
		} catch (Exception exception) {
			transaction.rollback();
			throw new ProductManagementException(exception.getMessage());
		} finally {
			try {
				if (null != session) {
					session.close();
				}
			} catch (Exception exception) {
				ProductManagementLogger.loggerError(exception);
			}
		}
		return dealer;
	}
	/*
	 * public Dealer retriveDealerById(int id) throws ProductManagementException {
	 * Dealer dealer = new Dealer(); try { sessionFactory =
	 * DataBaseConnection.getInstance(); Session session =
	 * sessionFactory.openSession(); transaction = session.beginTransaction();
	 * dealer = session.get(Dealer.class, id); transaction.commit(); } catch
	 * (Exception exception) { System.out.println(exception.getMessage()); throw new
	 * ProductManagementException(Constant.NOT_MATCH_THE_ID); } finally {
	 * session.close(); } return dealer; }
	 */

	/**
	 * {@inheritDoc}
	 */
	public Dealer retriveDealerById(int id) throws ProductManagementException {
		Dealer dealer = new Dealer();
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			dealer = session.get(Dealer.class, id);
		} catch (Exception exception) {
			throw new ProductManagementException(Constant.NOT_MATCH_THE_ID);
		} finally {
			session.close();
		}
		return dealer;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Dealer> retriveDealerInformation() throws ProductManagementException {
		List<Dealer> dealers;
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String hql = "SELECT id,brand FROM Manufacturer where and isDeleted = 0 ";
			org.hibernate.query.Query<Dealer> query = session.createQuery(hql, Dealer.class);
			dealers = query.list();
			transaction.commit();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw new ProductManagementException("Not Found");
		} finally {
			session.close();
		}
		return dealers;
	}
}