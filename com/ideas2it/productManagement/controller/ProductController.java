package com.ideas2it.productManagement.controller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.model.Product;
import com.ideas2it.productManagement.service.impl.DealerServiceImpl;
import com.ideas2it.productManagement.service.impl.ManufacturerServiceImpl;
import com.ideas2it.productManagement.service.impl.ProductServiceImpl;
import com.ideas2it.productManagement.util.Constant;
import com.ideas2it.productManagement.util.DateUtil;
import com.ideas2it.productManagement.util.enumaration.Colour;
import com.ideas2it.productManagement.util.exception.ProductManagementException;
import com.ideas2it.productManagement.util.logger.ProductManagementLogger;
import com.ideas2it.productManagement.util.Validation;

/**
 * Controller class is used for all input and output functions related to
 * product operation
 *
 * @author Thangapandiyan
 * @version 1.0
 */
public class ProductController {
	private Scanner scanner = new Scanner(System.in);
	private ProductServiceImpl productService = new ProductServiceImpl();
	private ManufacturerServiceImpl manufacturerServiceImpl = new ManufacturerServiceImpl();
	private DealerServiceImpl dealerService = new DealerServiceImpl();

	/**
	 * Read the string value from the user
	 * 
	 * @param message - to display user context message
	 * @return - the element at the specified value
	 */
	public String getString(String message) {
		System.out.println(message);
		return scanner.next();
	}

	/**
	 * Read the integer value from the user
	 *
	 * @return the specified value
	 */
	public int getInt() {
		int value = 0;
		boolean result = true;
		do {
			try {
				value = scanner.nextInt();
				break;
			} catch (InputMismatchException inputMismatchException) {
				ProductManagementLogger.loggerError(Constant.INVALID_OPTION);
				scanner.nextLine();
				result = false;
			}
		} while (!result);
		return value;
	}

	/**
	 * Read the float value from the user
	 *
	 * @return the specified value
	 */
	public float getFloat() {
		return scanner.nextFloat();
	}

	/**
	 * This method used to show the product menu
	 */
	public void showOption() {
		int value = 0;
		do {
			try {
				System.out.println(Constant.PRODUCT_MENU);
				value = getInt();
				switch (value) {
				case 1:
					createProduct();
					break;
				case 2:
					displayProduct();
					break;
				case 3:
					getProductById();
					break;
				case 4:
					removeProduct();
					break;
				case 5:
					updateProduct();
					break;
				case 6:
					searchProduct();
					break;
				case 7:
					getProductRangeValues();
					break;
				case 8:
					displayProductByIds();
					break;
				case 9:
					System.out.println(Constant.THANK_YOU);
					break;
				default:
					throw new ProductManagementException(Constant.INVALID_OPTION);
				}
			} catch (ProductManagementException productManagementException) {
				ProductManagementLogger.loggerError(productManagementException.toString());
			}
			System.out.println(Constant.CONTINUE);
		} while (canContinue());
	}

	/**
	 * Used to get the input details for the product and create the product
	 */
	public void createProduct() {
		try {
			String name = getName();
			Colour colour = getValidColour();
			int price = getPrice();
			Date dateOfManufacture = getDate();
			Manufacturer manufactureId = getManufactureId();
			Dealer dealerId = getDealerId();
			Product product = productService.createProduct(name, price, colour, dateOfManufacture, manufactureId,
					dealerId);
			System.out.println(product.getName() + " product inserted");
		} catch (Exception ex) {
			ProductManagementLogger.loggerError(ex);
		}
	}

	/**
	 * Used to read the value for continue the process
	 *
	 * @return - true if the value is one false otherwise
	 */
	public boolean canContinue() {
		boolean isValid;
		while (true) {
			try {
				isValid = (1 == scanner.nextInt());
				break;
			} catch (InputMismatchException exception) {
				ProductManagementLogger.loggerError(exception);
				scanner.nextLine();
			}
		}
		return isValid;
	}

	/**
	 * Used to display the product details
	 */
	public void displayProduct() {
		try {
			List<Product> products = productService.getProducts();
			products.forEach(product -> System.out.println(product));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Removes the product based on the product id
	 */
	public void removeProduct() {
		try {
			ProductManagementLogger.loggerInfo(Constant.ENTER_THE_PRODUCT_ID);
			int id = getInt();
			if (productService.removeProductById(id)) {
				ProductManagementLogger.loggerInfo(Constant.DELETED);
			} else {
				throw new ProductManagementException(Constant.INVALID_OPTION);
			}
		} catch (ProductManagementException productManagementException) {
			ProductManagementLogger.loggerError(productManagementException);
		}
	}

	/**
	 * Update the product based on the product id
	 */
	public void updateProduct() throws ProductManagementException {
		do {
			try {
				System.out.println(Constant.ENTER_THE_PRODUCT_ID);
				int productId = getInt();
				if (productService.isValidId(productId)) {
					System.out.println(Constant.PRODUCT_ATTRIBUTE_MENU);
					System.out.println(Constant.ENTER_THE_UPDATE_OPTION);
					int choice = getInt();
					switch (choice) {
					case 1:
						String name = getString(Constant.ENTER_THE_NAME);
						productService.updateProductById(name, productId, choice);
						System.out.println(name + " is updated successfully");
						break;
					case 2:
						String colour = getString(Constant.ENTER_THE_COLOUR);
						productService.updateProductById(colour, productId, choice);
						System.out.println(colour + " is updated successfully");
						break;
					case 3:
						String price = getString(Constant.ENTER_THE_PRICE);
						productService.updateProductById(price, productId, choice);
						System.out.println(price + " is updated successfully");
						break;
					case 4:
						String date = getString(Constant.ENTER_THE_UPDATE_DATE);
						productService.updateProductById(date, productId, choice);
						System.out.println(date + " is updated successfully");
						break;
					default:
						ProductManagementLogger.loggerInfo(Constant.INVALID_OPTION);
						break;
					}
				}
			} catch (ProductManagementException productManagementException) {
				ProductManagementLogger.loggerError(productManagementException);
			}
			System.out.println(Constant.CONTINUE);
		} while (canContinue());
	}

	/**
	 * Used to search the product by brand name.
	 */
	public void searchProduct() {
		String value = null;
		List<Product> products = null;
		do {
			try {
				value = getString(Constant.ENTER_THE_NAME);
				products = productService.searchProductById(value);
				products.forEach(p -> System.out.println(p));
			} catch (ProductManagementException exception) {
				ProductManagementLogger.loggerError(exception);
			}
			ProductManagementLogger.loggerInfo(Constant.CONTINUE);
		} while (canContinue());
	}

	/**
	 * Used to get the validated colour from the user
	 *
	 * @return colour - the validated colour
	 */
	public Colour getValidColour() {
		Colour result = null;
		do {
			try {
				System.out.println(Constant.PRODUCT_COLOUR_LIST);
				result = Colour.getColour(getInt());
				if (result == null) {
					throw new ProductManagementException(Constant.INVALID_OPTION);
				}
			} catch (InputMismatchException exception) {
				ProductManagementLogger.loggerError(exception);
			} catch (ProductManagementException exception) {
				ProductManagementLogger.loggerError(exception);
			}
		} while (result == null);
		return result;
	}

	/**
	 * Used to get the validated price from the user
	 * 
	 * @return the validated price
	 */
	public int getPrice() {
		int price = 0;
		boolean result = true;
		System.out.println(Constant.PRICE_LIST);
		price = getInt();
		result = Validation.isValidPrice(price);
		return price;
	}

	/**
	 * Used to show the manufacturers
	 * 
	 * @return value - manufacturer⁯ id
	 */
	public Manufacturer getManufactureId() {
		int value = 0;
		int id = 0;
		Manufacturer manufacturer = null;
		try {
			List<Manufacturer> manufacturers = null;
			manufacturers = manufacturerServiceImpl.getManufacturerDetails();
			manufacturers.forEach(display -> {
				System.out.println("Id = " + display.getId() + ",");
				System.out.print("Brand = " + display.getBrand());
			});
			do {
				System.out.println("enter the manufacturer id :");
				id = getInt();
				if (0 <= id && id <= manufacturers.size()) {
					value = id;
				} else {
					System.out.println("you entered wrong id enter the correct option ");
				}
				manufacturer = manufacturers.get(value - 1);
			} while (value != id);
		} catch (Exception exception) {
			ProductManagementLogger.loggerError(exception);
		}
		return manufacturer;
	}

	/**
	 * Used to show the dealer
	 * 
	 * @return value - dealer id
	 */
	public Dealer getDealerId() {
		int value = 0;
		int id = 0;
		List<Dealer> dealers = null;
		Dealer dealer = null;
		try {
			dealers = dealerService.getDealers();
			dealers.forEach(display -> {
				System.out.println("Id = " + display.getId());
				System.out.println("company = " + display.getCompany());
			});
			do {
				System.out.println("enter the dealer option :");
				id = getInt();
				if (0 <= id && id <= dealers.size()) {
					value = id;
				} else {
					System.out.println("you entered wrong option enter the correct option ");
				}
				dealer = dealers.get(value - 1);
			} while (value != id);
		} catch (Exception exception) {
			ProductManagementLogger.loggerError(exception);
		}
		return dealer;
	}

	/**
	 * Used to get the validated name from the user
	 *
	 * @return name - the validated name
	 */
	public String getName() {
		String name = "";
		boolean result = true;
		do {
			try {
				name = getString(Constant.ENTER_THE_NAME);
				result = Validation.containsOnlyAlphabet(name);
				if (!result) {
					throw new ProductManagementException(Constant.INVALID_OPTION);
				}
			} catch (ProductManagementException productManagementException) {
				ProductManagementLogger.loggerError(productManagementException);
			}
		} while (!result);
		return name;
	}

	/**
	 * Used to get the date from the user
	 *
	 * @return the validated date
	 */
	public Date getDate() {
		String result = " ";
		Date date = null;
		while (true) {
			try {
				result = getString(Constant.ENTER_THE_DATE);
				if (Validation.isValidDate(result)) {
					date = DateUtil.getDate(result);
				}
				break;
			} catch (Exception productManagementException) {
				ProductManagementLogger.loggerError(productManagementException);
			}
		}
		return date;
	}

	/**
	 * Used to get a product details by given user range values
	 */
	public void getProductRangeValues() {
		try {
			Date startDate = getDate();
			Date endDate = getDate();
			List<Product> products = productService.getProductBetweenDate(startDate, endDate);
			products.forEach(product -> System.out.println(product));
		} catch (Exception productManagementException) {
			ProductManagementLogger.loggerError(productManagementException);
		}
	}

	/**
	 * This methos used to get a product details by given user product id
	 */
	public void getProductById() {
		try {
			System.out.println(Constant.ENTER_PRODUCT_ID);
			int id = getInt();
			Product product = productService.getProductById(id);
			System.out.println(product);
			System.out.println(product.getManufacturer());
			System.out.println(product.getDealer());
		} catch (Exception productManagementException) {
			ProductManagementLogger.loggerError(productManagementException);
		}
	}

	/**
	 * This method display the value of given no of ids
	 */
	public void displayProductByIds() {
		try {
			int value = 0;
			System.out.println(Constant.HOW_MANY_IDS);
			int choice = getInt();
			System.out.println(Constant.ENTER_IDS);
			String id[] = new String[choice];
			do {
				if (id.length - 1 == value) {
					id[value] = getString("Id-" + (value + 1));
				} else {
					id[value] = getString("Id-" + (value + 1)) + ", ";
				}
				value++;
			} while (--choice > 0);
			String result = String.join("", id);// 
			System.out.println(result);
			productService.getProductByIds(result).forEach(product -> System.out.println(product));
		} catch (Exception exception) {
			ProductManagementLogger.loggerError(exception);
		}
	}
}