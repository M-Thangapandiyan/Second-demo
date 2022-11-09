package com.ideas2it.productManagement.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ideas2it.productManagement.dao.impl.ManufacturerDaoImpl;
import com.ideas2it.productManagement.dao.ManufacturerDao;
import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.util.Constant;
import com.ideas2it.productManagement.util.databaseConnection.DataBaseConnection;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

/**
 * This class used get the input from Manufacturer service to dispaly the
 * details from the database
 */
public class ManufacturerDaoImpl implements ManufacturerDao {
	private SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;
	/**
	 * {@inherticdoc}
	 */
	public Manufacturer insertManufacturer(Manufacturer manufacturer) throws ProductManagementException {
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(manufacturer);
			transaction.commit();
			if(0 == manufacturer.getId()) {
				throw new ProductManagementException(" manufacturer not inserted");
			}
			return manufacturer;
		} catch (HibernateException exception) {
			transaction.commit();
			throw new ProductManagementException(exception.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inherticdoc}
	 */
	public List<Manufacturer> retriveManufacturer() throws ProductManagementException {
		List<Manufacturer> manufacturers = null;
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			String hql = "FROM Manufacturer where isDeleted = 0";
			org.hibernate.query.Query<Manufacturer> query = session.createQuery(hql, Manufacturer.class);
			manufacturers = query.getResultList();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw new ProductManagementException("No manufacturer details ");
		} finally {
			session.close();
		}
		return manufacturers;
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean deleteManufacturerById(int id) throws ProductManagementException {
		try {
			sessionFactory = DataBaseConnection.getInstance();
		    session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update Manufacturer set isDeleted = 1 where id = :id");
			query.setParameter("id", id);
			int row = query.executeUpdate();
			transaction.commit();
			return row == 1;
		} catch (Exception sqlException) {
			throw new ProductManagementException(sqlException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public Manufacturer updateManufacturerById(Manufacturer manufacturer) throws ProductManagementException {
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(manufacturer);
			transaction.commit();
		} catch (Exception sqlException) {
			transaction.rollback();
			throw new ProductManagementException("invalid id");
		} finally {
			session.close();
		}
        return manufacturer;
	}

	/**
	 * {@inheritdoc}
	 */
	public Manufacturer retriveManufacturerById(int id) throws ProductManagementException {
		Manufacturer manufacturer = new Manufacturer();
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			manufacturer = session.get(Manufacturer.class, id);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw new ProductManagementException(Constant.NOT_MATCH_THE_ID);
		} finally {
			session.close();
		}
		return manufacturer;
	}

}