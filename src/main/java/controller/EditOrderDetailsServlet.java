package controller;

import dao.OrderDetailsDbDAO;
import dao.ProductDbDAO;
import domain.OrderDetails;
import domain.Product;

import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/editorderdetails")
public class EditOrderDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public EditOrderDetailsServlet() { super(); try { new ConnectionProperty(); } catch (IOException e) { e.printStackTrace(); } }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        OrderDetailsDbDAO detailsDao = new OrderDetailsDbDAO();
        ProductDbDAO productDao = new ProductDbDAO();
        try {
            List<OrderDetails> details = detailsDao.findAll();
            List<Product> products = productDao.findAll();
            request.setAttribute("details", details);
            request.setAttribute("products", products);
            request.setAttribute("detailEdit", detailsDao.findById(parseId(request)));
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка загрузки товара в заказе: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/editorder-details.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        OrderDetailsDbDAO dao = new OrderDetailsDbDAO();
        try {
            OrderDetails details = new OrderDetails(parseId(request), parseLong(request, "product"), null, Integer.parseInt(trim(request, "count")));
            dao.update(details);
            response.sendRedirect(request.getContextPath() + "/order-details");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка редактирования товара в заказе: " + e.getMessage());
            e.printStackTrace();
            doGet(request, response);
        }
    }
    private Long parseId(HttpServletRequest request) { return Long.parseLong(request.getParameter("id")); }
    private Long parseLong(HttpServletRequest request, String name) { return Long.parseLong(trim(request, name)); }
    private String trim(HttpServletRequest request, String name) { String value = request.getParameter(name); return value == null ? "" : value.trim(); }
}
