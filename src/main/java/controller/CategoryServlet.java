package controller;

import dao.CategoryDbDAO;
import dao.ConnectionProperty;
import domain.Category;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for category view.
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CategoryServlet() {
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
        CategoryDbDAO dao = new CategoryDbDAO();
        try {
            List<Category> categories = dao.findAll();
            request.setAttribute("categories", categories);
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка загрузки категорий из базы данных: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
