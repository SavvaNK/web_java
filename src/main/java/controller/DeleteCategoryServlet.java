package controller;

import dao.CategoryDbDAO;

import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/deletecategory")
public class DeleteCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DeleteCategoryServlet() { super(); try { new ConnectionProperty(); } catch (IOException e) { e.printStackTrace(); } }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            new CategoryDbDAO().delete(id);
        } catch (DAOException | NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/category");
    }
}
