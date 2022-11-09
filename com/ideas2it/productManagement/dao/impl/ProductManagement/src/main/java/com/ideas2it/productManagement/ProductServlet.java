package com.ideas2it.productManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.productManagement.model.Dealer;
import com.ideas2it.productManagement.model.Manufacturer;
import com.ideas2it.productManagement.model.Product;
import com.ideas2it.productManagement.service.impl.ProductServiceImpl;
import com.ideas2it.productManagement.util.Constant;
import com.ideas2it.productManagement.util.DateUtil;
import com.ideas2it.productManagement.util.enumaration.Colour;
import com.ideas2it.productManagement.util.exception.ProductManagementException;

@WebServlet(urlPatterns = { "/InsertProduct", "/getProducts", "/getProduct", "/GetChoice", "/searchProduct",
		"/getProductById", "/deleteProductById", "/getProductBetweenDate", "/updateProductById", "/getProductByIds" })
public class ProductServlet extends HttpServlet {
	ProductServiceImpl productService = new ProductServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/InsertProduct":
			createProduct(request, response);
			break;

		case "/getProductById":
			try {
				getProductById(request, response);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;

		case "/updateProductById":
			try {
				updateProductById(request, response);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;

		case "/deleteProductById":
			try {
				deleteProductById(request, response);
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
		case "/getProducts":
			try {
				getProducts(request, response);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;

		case "/getProduct":
			try {
				getProduct(request, response);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			}
			break;

		case "/searchProduct":
			try {
				searchProduct(request, response);
			} catch (IOException | ProductManagementException e) {
				e.printStackTrace();
			} 
			break;

		case "/getProductBetweenDate":
			try {
				getProductBetweenDate(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case "/getProductByIds":
			try {
				try {
					getProductByIds(request, response);
				} catch (NumberFormatException | ProductManagementException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	private void getProducts(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ProductManagementException {
		List<Product> products = productService.getProducts();
		HttpSession session = request.getSession();
		session.setAttribute("products", products);
		response.sendRedirect("getProducts.jsp");
	}

	private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Product product = productService.createProduct(request.getParameter("name"),
					Integer.parseInt(request.getParameter("Price")), Colour.valueOf(request.getParameter("colour")),
					DateUtil.getDate(request.getParameter("dob")), new Manufacturer(), new Dealer());
			HttpSession session = request.getSession();
			session.setAttribute("product", product);
			response.sendRedirect("createProduct.jsp");
		} catch (NumberFormatException | ProductManagementException e) {
			e.printStackTrace();
		}
	}

	private void getProductBetweenDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			PrintWriter a = response.getWriter();
			a.print(request.getParameter("startDate"));
			List<Product> products = productService.getProductBetweenDate(
					DateUtil.getDate(request.getParameter("startDate")),
					DateUtil.getDate(request.getParameter("endDate")));
			HttpSession session = request.getSession();
			session.setAttribute("products", products);
			response.sendRedirect("getProductBetweenDate.jsp");
		} catch (NumberFormatException | ProductManagementException e) {
			e.printStackTrace();
		}
	}

	private void getProductById(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NumberFormatException, ProductManagementException {
		Product product = productService.getProductById(Integer.parseInt(request.getParameter("id")));
		HttpSession session = request.getSession();
		session.setAttribute("product", product);
		response.sendRedirect("getProductById.jsp");
	}

	private void getProductByIds(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NumberFormatException, ProductManagementException {
		int choice = Integer.parseInt(request.getParameter("choice"));
		String id[] = new String[choice];
		StringBuffer stringBuffer = new StringBuffer();
		int index = 0;
		do {
			id[index] = request.getParameter("id-" + (index + 1));
			stringBuffer.setLength(0);
			index++;
		} while (--choice > 0);
		List<Product> products = productService.getProductByIds(id);
		HttpSession session = request.getSession();
		session.setAttribute("products", products);
		response.sendRedirect("getProductByIds.jsp");
	}

	private void getProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NumberFormatException, ProductManagementException {
		Product product = productService.getProductById(Integer.parseInt(request.getParameter("id")));
		HttpSession session = request.getSession();
		session.setAttribute("product", product);
		response.sendRedirect("updateProductById.jsp");
	}

	private void searchProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ProductManagementException {
		List<Product> products = productService.searchProduct(request.getParameter("name"));
		HttpSession session = request.getSession();
		session.setAttribute("products", products);
		response.sendRedirect("searchProduct.jsp");
	}

	private void updateProductById(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NumberFormatException, ProductManagementException {
		Product product = productService.getProductById(Integer.parseInt(request.getParameter("id")));
		product.setDate(DateUtil.getDate(request.getParameter("dob")));
		product.setName(request.getParameter("name"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setColour(Colour.valueOf(request.getParameter("colour")));
		boolean a = productService.updateProductById(product);
		HttpSession session = request.getSession();
		session.setAttribute("isThere", a);
		response.sendRedirect("updateProductById.jsp");
	}

	private void deleteProductById(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, ProductManagementException, IOException {
		boolean isDeleted = productService.removeProductById(Integer.parseInt(request.getParameter("id")));
		HttpSession session = request.getSession();
		session.setAttribute("isDeleted", isDeleted);
		response.sendRedirect("deleteProductById.jsp");
	}
}
