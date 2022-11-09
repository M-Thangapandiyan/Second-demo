package com.ideas2it.productManagement;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.service.impl.ManufacturerServiceImpl;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

@WebServlet(urlPatterns = { "/InsertManufacturer", "/getManufacturers", "/getManufacturer", "/deleteManufacturerById",
		"/getManufacturerById", "/updateManufacturerById", "/assignManufacturer" })
public class ManufacturerServlet extends HttpServlet {
	ManufacturerServiceImpl manufacturerService = new ManufacturerServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch (action) {

		case "/InsertManufacturer":
			createManufacturer(request, response);
			break;

		case "/getManufacturerById":
			try {
				getManufacturerById(request, response,action);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;

		case "/deleteManufacturerById":
			try {
				deleteManufacturerById(request, response);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/getManufacturers":
			try {
				getManufacturers(request, response);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;

		case "/assignManufacturer":
			try {
				assignManufacturer(request, response);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;

		case "/getManufacturer":
			try {
				getManufacturerById(request, response,action);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;

		case "/updateManufacturerById":
			try {
				updateManufacturerById(request, response);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	private void assignManufacturer(HttpServletRequest request, HttpServletResponse response)
			throws ProductManagementException, IOException {
		List<Manufacturer> manufacturers = manufacturerService.getManufacturers();
		HttpSession session = request.getSession();
		session.setAttribute("manufacturers", manufacturers);
		response.sendRedirect("createProduct.jsp");
	}

	private void getManufacturers(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ProductManagementException {
		List<Manufacturer> manufacturers = manufacturerService.getManufacturers();
		HttpSession session = request.getSession();
		session.setAttribute("manufacturers", manufacturers);
		response.sendRedirect("getManufacturers.jsp");
	}
	
	private void createManufacturer(HttpServletRequest request, HttpServletResponse response) {
		try {
			manufacturerService.createManufacturer(request.getParameter("brand"), request.getParameter("place"));
		} catch (NumberFormatException | ProductManagementException e) {
			e.printStackTrace();
		}
	}

	private void deleteManufacturerById(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, ProductManagementException, IOException {
		boolean isDeleted = manufacturerService.removeManufacturerById(Integer.parseInt(request.getParameter("id")));
		HttpSession session = request.getSession();
		session.setAttribute("isDeleted", isDeleted);
		response.sendRedirect("deleteManufacturerById.jsp");
	}

	private void getManufacturerById(HttpServletRequest request, HttpServletResponse response,String action)
			throws IOException, NumberFormatException, ProductManagementException {
		Manufacturer manufacturer = manufacturerService
				.getManufacturerById(Integer.parseInt(request.getParameter("id")));
		HttpSession session = request.getSession();
		session.setAttribute("manufacturer", manufacturer);
		if(action.equals("/getManufacturer")) {
		    response.sendRedirect("updateManufacturerById.jsp");
		} else
		response.sendRedirect("getManufacturerById.jsp");
	}

	private void updateManufacturerById(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, ProductManagementException, IOException {
		Manufacturer manufacturer = manufacturerService
				.getManufacturerById(Integer.parseInt(request.getParameter("id")));
		manufacturer.setBrand(request.getParameter("brand"));
		manufacturer.setPlace(request.getParameter("place"));
		boolean isThere = manufacturerService.updateManufacturerById(manufacturer);
		HttpSession session = request.getSession();
		session.setAttribute("isThere", isThere);
	    response.sendRedirect("updateManufacturerById.jsp");
	}
}
