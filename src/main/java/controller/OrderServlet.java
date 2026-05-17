package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet template for Order entity.
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang='ru'>");
            writer.println("<head>");
            writer.println("<meta charset='UTF-8'>");
            writer.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            writer.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            writer.println("<title>OrderServlet</title>");
            writer.println("</head>");
            writer.println("<body class='bg-light'>");
            writer.println("<main class='container py-5'>");
            writer.println("<div class='card shadow-sm'>");
            writer.println("<div class='card-body'>");
            writer.println("<h2 class='card-title'>Привет OrderServlet</h2>");
            writer.println("<p class='card-text'>Шаблон сервлета для работы с заказами интернет-магазина.</p>");
            writer.println("<a class='btn btn-primary' href='./'>На главную</a>");
            writer.println("</div>");
            writer.println("</div>");
            writer.println("</main>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
