package lk.ijse.edu;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
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

@WebServlet("/path/*")
public class PathVariableServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String pathInfo = req.getPathInfo();
    System.out.println("Path Info: " + pathInfo);
    String[] pathParts = pathInfo.split("/");
    System.out.println("Path Parts: " + pathParts[0]);
    System.out.println("Path Parts: " + pathParts[1]);
  }
}
