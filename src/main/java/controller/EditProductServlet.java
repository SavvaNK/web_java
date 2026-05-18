package controller;

import dao.CategoryDbDAO;
import dao.ProductDbDAO;
import domain.Category;
import domain.Product;

import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@WebServlet("/editproduct")
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public EditProductServlet() { super(); try { new ConnectionProperty(); } catch (IOException e) { e.printStackTrace(); } }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        ProductDbDAO productDao = new ProductDbDAO();
        CategoryDbDAO categoryDao = new CategoryDbDAO();
        try {
            List<Product> products = productDao.findAll();
            List<Category> categories = categoryDao.findAll();
            request.setAttribute("products", products);
            request.setAttribute("categories", categories);
            request.setAttribute("productEdit", productDao.findById(parseId(request)));
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка загрузки продукта: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/editproduct.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ProductDbDAO dao = new ProductDbDAO();
        try {
            Product product = new Product(parseId(request), trim(request, "productName"), trim(request, "description"), parseDecimal(request, "price"), parseLong(request, "category"), null);
            dao.update(product);
            response.sendRedirect(request.getContextPath() + "/product");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка редактирования продукта: " + e.getMessage());
            e.printStackTrace();
            doGet(request, response);
        }
    }
    private Long parseId(HttpServletRequest request) { return Long.parseLong(request.getParameter("id")); }
    private Long parseLong(HttpServletRequest request, String name) { return Long.parseLong(trim(request, name)); }
    private BigDecimal parseDecimal(HttpServletRequest request, String name) { return new BigDecimal(trim(request, name).replace(',', '.')); }
    private String trim(HttpServletRequest request, String name) { String value = request.getParameter(name); return value == null ? "" : value.trim(); }
}
