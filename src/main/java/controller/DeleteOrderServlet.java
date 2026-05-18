package controller;

import dao.OrderDbDAO;

import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/deleteorder")
public class DeleteOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DeleteOrderServlet() { super(); try { new ConnectionProperty(); } catch (IOException e) { e.printStackTrace(); } }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { new OrderDbDAO().delete(Long.parseLong(request.getParameter("id"))); } catch (DAOException | NumberFormatException e) { e.printStackTrace(); }
        response.sendRedirect(request.getContextPath() + "/order");
    }
}
