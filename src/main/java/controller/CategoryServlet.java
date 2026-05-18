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
 * Servlet for category view and category creation.
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
        request.setCharacterEncoding("UTF-8");

        String categoryName = trimParameter(request, "categoryName");
        String description = trimParameter(request, "description");

        if (categoryName == null || categoryName.isEmpty()) {
            request.setAttribute("errorMessage", "Введите название категории.");
            doGet(request, response);
            return;
        }

        CategoryDbDAO dao = new CategoryDbDAO();
        Category category = new Category(categoryName, description);
        try {
            Long id = dao.insert(category);
            System.out.println("Adding category result: " + id);
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка добавления категории: " + e.getMessage());
            e.printStackTrace();
        }

        doGet(request, response);
    }

    private String trimParameter(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? null : value.trim();
    }
}
