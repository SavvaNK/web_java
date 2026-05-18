package controller;

import dao.CategoryDbDAO;
import domain.Category;

import dao.ConnectionProperty;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/editcategory")
public class EditCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditCategoryServlet() {
        super();
        try { new ConnectionProperty(); } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        CategoryDbDAO dao = new CategoryDbDAO();
        try {
            List<Category> categories = dao.findAll();
            request.setAttribute("categories", categories);
            request.setAttribute("categoryEdit", dao.findById(parseId(request)));
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка загрузки категории: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/editcategory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CategoryDbDAO dao = new CategoryDbDAO();
        try {
            Category category = new Category(parseId(request), trim(request, "categoryName"), trim(request, "description"));
            dao.update(category);
            response.sendRedirect(request.getContextPath() + "/category");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка редактирования категории: " + e.getMessage());
            e.printStackTrace();
            doGet(request, response);
        }
    }

    private Long parseId(HttpServletRequest request) { return Long.parseLong(request.getParameter("id")); }
    private String trim(HttpServletRequest request, String name) { String value = request.getParameter(name); return value == null ? "" : value.trim(); }
}
