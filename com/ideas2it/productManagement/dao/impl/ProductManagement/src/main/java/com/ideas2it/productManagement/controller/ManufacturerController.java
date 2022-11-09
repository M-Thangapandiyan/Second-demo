package com.ideas2it.productManagement.controller;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.service.impl.ManufacturerServiceImpl;
import com.ideas2it.productManagement.util.Constant;
import com.ideas2it.productManagement.util.exception.ProductManagementException;
import com.ideas2it.productManagement.util.logger.ProductManagementLogger;
import com.ideas2it.productManagement.util.Validation;

/**
 * This class is used for all input and output functions related to manufacture
 * operation
 *
 * @author Thangapandiyan
 * @version 1.0
 */
public class ManufacturerController {
	Scanner scanner = new Scanner(System.in);
	ManufacturerServiceImpl manufacturerService = new ManufacturerServiceImpl();

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
	 * Used to read the value for continue the process
	 *
	 * @return isvalid - true if the value is one false otherwise
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
	 * This method used to show the option
	 */
	public void showOption() {
		try {
			do {
				System.out.println(Constant.MANUFACTURER_MENU);
				int value = scanner.nextInt();
				switch (value) {
				case 1:
					createManufacturer();
					break;
				case 2:
					displayManufacturer();
					break;
				case 3:
					dispalyManufacturerById();
					break;
				case 4:
					removeManufacturer();
					break;
				case 5:
					updateManufacturer();
					break;
				case 6:
					System.out.println(Constant.THANK_YOU);
					break;
				default:
					ProductManagementLogger.loggerError(Constant.ENTER_VALID_VALUE);
				}
				System.out.println(Constant.CONTINUE);
			} while (canContinue());
		} catch (Exception exception) {
			ProductManagementLogger.loggerError(exception);
		}
	}

	/**
	 * This method used to dispaly the manufactuers
	 */
	public void displayManufacturer() throws ProductManagementException {
		try {
			List<Manufacturer> manufacturers = manufacturerService.getManufacturers();
			manufacturers.forEach(manufacturer -> System.out.println(manufacturer));
		} catch (Exception exception) {
			ProductManagementLogger.loggerError(Constant.NO_MANUFACTURER);
		}
	}

	/**
	 * This method used to get the id to dispaly id details
	 */
	public void dispalyManufacturerById() {
		int manufacturerId = 0;
		do {
			try {
				System.out.println(Constant.ENTER_THE_ID);
				manufacturerId = scanner.nextInt();
				Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);
				System.out.println(manufacturer);
				manufacturer.getProducts().forEach(product -> {
					System.out.println(product);
					System.out.println(product.getDealer());
				});
			} catch (ProductManagementException exception) {
				System.out.println(exception);
			}
			System.out.println(Constant.CONTINUE);
		} while (canContinue());
	}

	/**
	 * Used to get the input details for the product and create the manufacturer
	 */
	public void createManufacturer() {
		try {
			String brand = getBrand();
			String place = getPlace();
			Manufacturer manufacturer = manufacturerService.createManufacturer(brand, place);
			System.out.println(manufacturer.getBrand()+" manufacturer is succesfully inserted");
		} catch (ProductManagementException productManagementException) {
			ProductManagementLogger.loggerError(productManagementException.getMessage());
		}
	}

	/**
	 * Used to get the validated company from the user
	 *
	 * @return company - the validated company
	 */
	public String getBrand() {
		String name = "";
		boolean result = true;
		do {
			try {
				name = getString(Constant.ENTER_THE_BRAND);
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
	 * Used to get the validated company from the user
	 *
	 * @return place - the validated company
	 */
	public String getPlace() {
		String place = "";
		boolean result = true;
		do {
			try {
				place = getString(Constant.ENTER_THE_PLACE);
				result = Validation.containsOnlyAlphabet(place);
				if (!result) {
					throw new ProductManagementException(Constant.INVALID_OPTION);
				}
			} catch (ProductManagementException productManagementException) {
				ProductManagementLogger.loggerError(productManagementException);
			}
		} while (!result);
		return place;
	}

	/**
	 * Removes the product based on the product id
	 */
	public void removeManufacturer() {
		try {
			System.out.println((Constant.ENTER_THE_ID));
			int id = getInt();
			if (manufacturerService.removeManufacturerById(id)) {
				System.out.println("Succesfully deleted");
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
	public void updateManufacturer() throws ProductManagementException {
		int manufacturerId = 0;
		do {
			try {
				System.out.println(Constant.ENTER_THE_ID);
				manufacturerId = getInt();
				if (manufacturerService.isValidId(manufacturerId)) {
					System.out.println(Constant.ENTER_UPDATE_THE_OPTION);
					int choice = getInt();
					switch (choice) {
					case 1:
						String brand = getString(Constant.ENTER_THE_BRAND);
						//manufacturerService.updateManufacturerById(brand, manufacturerId, choice);
						System.out.println("This " + brand + " is updated successfully");
						break;
					case 2:
						String place = getString(Constant.ENTER_THE_PLACE);
						//manufacturerService.updateManufacturerById(place, manufacturerId, choice);
						System.out.println("This " + place + " is updated successfully");
						break;
					default:
						System.out.println(Constant.INVALID_OPTION);
						break;
					}
				}
			} catch (ProductManagementException productManagementException) {
				ProductManagementLogger.loggerError(productManagementException);
			}
			System.out.println(Constant.CONTINUE);
		} while (canContinue());
	}
}