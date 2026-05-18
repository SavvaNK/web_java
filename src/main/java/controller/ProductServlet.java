package controller;

import dao.CategoryDbDAO;
import dao.ConnectionProperty;
import dao.ProductDbDAO;
import domain.Category;
import domain.Product;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for product view.
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductServlet() {
        super();
        try {
            new ConnectionProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        ProductDbDAO productDao = new ProductDbDAO();
        CategoryDbDAO categoryDao = new CategoryDbDAO();
        try {
            List<Product> products = productDao.findAll();
            List<Category> categories = categoryDao.findAll();
            request.setAttribute("products", products);
            request.setAttribute("categories", categories);
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка загрузки продуктов из базы данных: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
