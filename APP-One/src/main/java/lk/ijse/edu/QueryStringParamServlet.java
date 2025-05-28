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

@WebServlet("/querystring")
public class QueryStringParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eid = req.getParameter("eid");
        String ename = req.getParameter("ename");
        System.out.println("Event ID: " + eid);
        System.out.println("Event Name: " + ename);
    }
}
