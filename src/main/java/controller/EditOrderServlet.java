package controller;

import dao.OrderDbDAO;
import dao.OrderDetailsDbDAO;
import domain.Order;
import domain.OrderDetails;

import dao.ConnectionProperty;
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


@WebServlet("/editorder")
public class EditOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public EditOrderServlet() { super(); try { new ConnectionProperty(); } catch (IOException e) { e.printStackTrace(); } }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        OrderDbDAO orderDao = new OrderDbDAO();
        OrderDetailsDbDAO detailsDao = new OrderDetailsDbDAO();
        try {
            List<Order> orders = orderDao.findAll();
            List<OrderDetails> details = detailsDao.findAll();
            request.setAttribute("orders", orders);
            request.setAttribute("details", details);
            request.setAttribute("orderEdit", orderDao.findById(parseId(request)));
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка загрузки заказа: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/editorder.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        OrderDbDAO dao = new OrderDbDAO();
        try {
            Order order = new Order(parseId(request), trim(request, "numberOrder"), LocalDate.parse(trim(request, "orderDate")), trim(request, "status"), parseDecimal(request, "total"), parseLong(request, "detail"), null);
            dao.update(order);
            response.sendRedirect(request.getContextPath() + "/order");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка редактирования заказа: " + e.getMessage());
            e.printStackTrace();
            doGet(request, response);
        }
    }
    private Long parseId(HttpServletRequest request) { return Long.parseLong(request.getParameter("id")); }
    private Long parseLong(HttpServletRequest request, String name) { return Long.parseLong(trim(request, name)); }
    private BigDecimal parseDecimal(HttpServletRequest request, String name) { return new BigDecimal(trim(request, name).replace(',', '.')); }
    private String trim(HttpServletRequest request, String name) { String value = request.getParameter(name); return value == null ? "" : value.trim(); }
}
