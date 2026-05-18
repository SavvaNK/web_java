package controller;

import dao.ConnectionProperty;
import dao.OrderDbDAO;
import dao.OrderDetailsDbDAO;
import domain.Order;
import domain.OrderDetails;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Servlet for order view and order creation.
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderServlet() {
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
        OrderDbDAO orderDao = new OrderDbDAO();
        OrderDetailsDbDAO orderDetailsDao = new OrderDetailsDbDAO();
        try {
            List<Order> orders = orderDao.findAll();
            List<OrderDetails> details = orderDetailsDao.findAll();
            request.setAttribute("orders", orders);
            request.setAttribute("details", details);
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка загрузки заказов из базы данных: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            Long orderDetailId = parseLongParameter(request, "detail");
            String numberOrder = trimParameter(request, "numberOrder");
            LocalDate orderDate = parseDateParameter(request, "orderDate");
            String status = trimParameter(request, "status");
            BigDecimal total = parseBigDecimalParameter(request, "total");

            if (numberOrder == null || numberOrder.isEmpty()) {
                request.setAttribute("errorMessage", "Введите номер заказа.");
                doGet(request, response);
                return;
            }

            Order order = new Order(numberOrder, orderDate, status, total, orderDetailId, null);
            OrderDbDAO dao = new OrderDbDAO();
            Long id = dao.insert(order);
            System.out.println("Adding order result: " + id);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Проверьте товар в заказе и сумму заказа.");
            e.printStackTrace();
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка добавления заказа: " + e.getMessage());
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

    private LocalDate parseDateParameter(HttpServletRequest request, String name) {
        String value = trimParameter(request, name);
        if (value == null || value.isEmpty()) {
            return LocalDate.now();
        }
        return LocalDate.parse(value);
    }
}
