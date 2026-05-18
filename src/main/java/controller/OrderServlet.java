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
import java.util.List;

/**
 * Servlet for order view.
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
        doGet(request, response);
    }
}
