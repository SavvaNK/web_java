package controller;

import dao.ConnectionProperty;
import dao.OrderDetailsDbDAO;
import dao.ProductDbDAO;
import domain.OrderDetails;
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
 * Servlet for order details view and order details creation.
 */
@WebServlet("/order-details")
public class OrderDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderDetailsServlet() {
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
        OrderDetailsDbDAO orderDetailsDao = new OrderDetailsDbDAO();
        ProductDbDAO productDao = new ProductDbDAO();
        try {
            List<OrderDetails> details = orderDetailsDao.findAll();
            List<Product> products = productDao.findAll();
            request.setAttribute("details", details);
            request.setAttribute("products", products);
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка загрузки товаров в заказах из базы данных: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/order-details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            Long productId = parseLongParameter(request, "product");
            Integer count = parseIntegerParameter(request, "count");

            OrderDetails details = new OrderDetails(productId, null, count);
            OrderDetailsDbDAO dao = new OrderDetailsDbDAO();
            Long id = dao.insert(details);
            System.out.println("Adding order details result: " + id);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Проверьте выбранный продукт и количество.");
            e.printStackTrace();
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка добавления товара в заказ: " + e.getMessage());
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

    private Integer parseIntegerParameter(HttpServletRequest request, String name) {
        String value = trimParameter(request, name);
        if (value == null || value.isEmpty()) {
            throw new NumberFormatException("Empty integer parameter: " + name);
        }
        return Integer.parseInt(value);
    }
}
