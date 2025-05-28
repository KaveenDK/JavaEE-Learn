package lk.ijse.edu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * --------------------------------------------
 * @Author Dimantha Kaveen
 * @GitHub: https://github.com/KaveenDK
 * --------------------------------------------
 * @Created 5/26/2025
 * @Project JavaEE-learn
 * --------------------------------------------
 **/

@WebServlet("/lifecycles")
public class ServletLIfeCycles extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init method called : servlet is being initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet method called : servlet is being executed");
        resp.getWriter().write("Hello World");
    }
    @Override
    public void destroy() {
        System.out.println("destroy method called : servlet is being destroyed");
    }
}
