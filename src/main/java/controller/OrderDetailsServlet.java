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
 * Servlet for order details view.
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
        doGet(request, response);
    }
}
