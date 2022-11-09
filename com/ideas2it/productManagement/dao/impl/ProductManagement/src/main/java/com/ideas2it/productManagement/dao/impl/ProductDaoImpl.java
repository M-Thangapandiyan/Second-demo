package com.ideas2it.productManagement.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ideas2it.productManagement.dao.ProductDao;
import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.model.Product;
import com.ideas2it.productManagement.util.Constant;
import com.ideas2it.productManagement.util.DateUtil;
import com.ideas2it.productManagement.util.databaseConnection.DataBaseConnection;
import com.ideas2it.productManagement.util.exception.ProductManagementException;
import com.ideas2it.productManagement.util.logger.ProductManagementLogger;

/**
 * This class used to get the input from product service to insert, delete,
 * update, search, display action from the database
 */
public class ProductDaoImpl implements ProductDao {
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;

	/**
	 * {@inheritDoc}
	 */
	public Product insertProduct(Product product) throws ProductManagementException {
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
			if (0 == product.getId()) {
				throw new ProductManagementException(" prodcut is not inserted");
			}
			return product;
		} catch (Exception exception) {
			transaction.rollback();
			throw new ProductManagementException(exception.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Product> retriveProducts() throws ProductManagementException {
		List<Product> products;
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Product where is_deleted = 0 ", Product.class);
			products = query.list();
		} catch (Exception exception) {
			throw new ProductManagementException(Constant.CAN_NOT_RETRIVE);
		} finally {
			try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException exception) {
				ProductManagementLogger.loggerError(exception);
			}
		}
		return products;
	}

	/**
	 * {@inheritDoc}
	 */
	public Product updateProductById(Product product) throws ProductManagementException {
		Transaction transaction = null;
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(product);
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
		return product;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteProductById(int id) throws ProductManagementException {
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update Manufacturer set isDeleted = 1 where id= :id");
			query.setParameter("id", id);
			int row = query.executeUpdate();
			transaction.commit();
			return row == 1;
		} catch (Exception sqlException) {
			transaction.rollback();
			throw new ProductManagementException(sqlException.getMessage());
		} finally {
			try {
				if (null != session) {
					session.close();
				}
			} catch (Exception exception) {
				ProductManagementLogger.loggerError(exception);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Product> searchProductById(String value) throws ProductManagementException {
		List<Product> prodcuts = null;
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Product where name like :name and isDeleted = 0");
			query.setParameter("name", "%" + value + "%");
			prodcuts = query.getResultList();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw new ProductManagementException(Constant.NOT_MATCH_THE_ID);
		} finally {
			try {
				if (null != session) {
					session.close();
				}
			} catch (Exception exception) {
				ProductManagementLogger.loggerError(exception);
			}
		}
		return prodcuts;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Product> retrieveProductBetweenDate(java.util.Date startDate, java.util.Date endDate)
			throws ProductManagementException {
		List<Product> products = null;
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Product where dateOfManufacture between '"
					+ DateUtil.getSqlDate(startDate) + "' and '" + DateUtil.getSqlDate(endDate) + "'");
			products = query.getResultList();
			System.out.println(products);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw new ProductManagementException(Constant.NOT_MATCH_THE_DATE);
		} finally {
			try {
				if (null != session) {
					session.close();
				}
			} catch (Exception exception) {
				ProductManagementLogger.loggerError(exception);
			}
		}
		return products;

	}

	/**
	 * {@inheritDoc}
	 */
	public Product retriveProductById(int id) throws ProductManagementException {
		Product product = new Product();
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			product = session.get(Product.class, id);
		} catch (Exception exception) {
			throw new ProductManagementException(Constant.NOT_MATCH_THE_ID);
		} finally {
			session.close();
		}
		return product;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getLastId() throws ProductManagementException {
		long row = 0;
		try {
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			Query query = session.createQuery("select count(id) from Product where isDeleted = 0");
			row = (Long) query.uniqueResult();
		} catch (Exception exception) {
			throw new ProductManagementException(exception.getMessage());
		} finally {
			try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException exception) {
				ProductManagementLogger.loggerError(exception);
			}
		}
		return (int) row;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Product> retriveProductByIds(String[] ids) throws ProductManagementException {
		try {
			List<String> list = Arrays.asList(ids);
			sessionFactory = DataBaseConnection.getInstance();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
			Root<Product> root = criteriaQuery.from(Product.class);
			criteriaQuery.select(root).where(root.get("id").in(list), builder.equal(root.get("isDeleted"), false));
			Query<Product> query = session.createQuery(criteriaQuery);
			return query.getResultList();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw new ProductManagementException(Constant.NOT_MATCH_THE_IDS);
		} finally {
			session.close();
		}
	}

}
