package controller;

import dao.ProductDbDAO;

import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/deleteproduct")
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DeleteProductServlet() { super(); try { new ConnectionProperty(); } catch (IOException e) { e.printStackTrace(); } }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { new ProductDbDAO().delete(Long.parseLong(request.getParameter("id"))); } catch (DAOException | NumberFormatException e) { e.printStackTrace(); }
        response.sendRedirect(request.getContextPath() + "/product");
    }
}
