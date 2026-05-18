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
import java.math.BigDecimal;
import java.util.List;

/**
 * Servlet for product view and product creation.
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
        request.setCharacterEncoding("UTF-8");

        try {
            Long categoryId = parseLongParameter(request, "category");
            String productName = trimParameter(request, "productName");
            String description = trimParameter(request, "description");
            BigDecimal price = parseBigDecimalParameter(request, "price");

            if (productName == null || productName.isEmpty()) {
                request.setAttribute("errorMessage", "Введите название продукта.");
                doGet(request, response);
                return;
            }

            Product product = new Product(productName, description, price, categoryId, null);
            ProductDbDAO dao = new ProductDbDAO();
            Long id = dao.insert(product);
            System.out.println("Adding product result: " + id);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Проверьте категорию и цену продукта.");
            e.printStackTrace();
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка добавления продукта: " + e.getMessage());
            e.printStackTrace();
        }

        doGet(request, response);
    }

    private String trimParameter(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? null : value.trim();
    }

    private Long parseLongParameter(HttpServletRequest request, String name) {
        String value = trimParameter(request, name);
        if (value == null || value.isEmpty() || value.startsWith("Выберите")) {
            throw new NumberFormatException("Empty long parameter: " + name);
        }
        return Long.parseLong(value);
    }

    private BigDecimal parseBigDecimalParameter(HttpServletRequest request, String name) {
        String value = trimParameter(request, name);
        if (value == null || value.isEmpty()) {
            throw new NumberFormatException("Empty decimal parameter: " + name);
        }
        return new BigDecimal(value.replace(',', '.'));
    }
}
