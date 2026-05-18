package controller;

import dao.OrderDetailsDbDAO;

import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/deleteorderdetails")
public class DeleteOrderDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DeleteOrderDetailsServlet() { super(); try { new ConnectionProperty(); } catch (IOException e) { e.printStackTrace(); } }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { new OrderDetailsDbDAO().delete(Long.parseLong(request.getParameter("id"))); } catch (DAOException | NumberFormatException e) { e.printStackTrace(); }
        response.sendRedirect(request.getContextPath() + "/order-details");
    }
}
