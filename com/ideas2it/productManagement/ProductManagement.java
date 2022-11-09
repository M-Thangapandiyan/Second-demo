package com.ideas2it.productManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.productManagement.controller.DealerController;
import com.ideas2it.productManagement.controller.ManufacturerController;
import com.ideas2it.productManagement.controller.ProductController;
import com.ideas2it.productManagement.util.Constant;
import com.ideas2it.productManagement.util.logger.ProductManagementLogger;

public class ProductManagement {
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		ProductManagement productManagement = new ProductManagement();
		ProductController productController = new ProductController();
		ManufacturerController manufactureController = new ManufacturerController();
		DealerController dealerController = new DealerController();
		int value = 0;
		try {
			do {
				System.out.println(Constant.PRODUCT);
				value = productManagement.getInt();
				switch (value) {
				case 1:
					productController.showOption();
					break;

				case 2:
					manufactureController.showOption();
					break;

				case 3:
					dealerController.showOption();
					break;

				case 4:
					System.out.println("thank you");

				default:
					ProductManagementLogger.loggerInfo(Constant.ENTER_VALID_VALUE);
				}
			} while (4 != value);
		} catch (Exception exception) {
			ProductManagementLogger.loggerError(exception);
		}
	}

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
}
