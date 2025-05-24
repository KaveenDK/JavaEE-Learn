package lk.ijse.edu;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --------------------------------------------
 * @Author Dimantha Kaveen
 * @GitHub: https://github.com/KaveenDK
 * --------------------------------------------
 * @Created 5/19/2025
 * @Project JavaEE-learn
 * --------------------------------------------
 **/

@WebServlet("/event")
public class EventServlet extends HttpServlet {

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appone", "root", "Ijse@1234");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM events");
            ResultSet resultSet = statement.executeQuery();
            List<Map<String, String>> elist = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, String> event = new HashMap<>();
                event.put("eid", resultSet.getString("eid"));
                event.put("ename", resultSet.getString("ename"));
                event.put("edescription", resultSet.getString("edescription"));
                event.put("edate", resultSet.getString("edate"));
                event.put("eplace", resultSet.getString("eplace"));
                elist.add(event);
            }
            resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), elist);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appone", "root", "Ijse@1234");
            Map<String, String> event = new ObjectMapper().readValue(req.getReader(), Map.class);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO events (eid, ename, edescription, edate, eplace) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, event.get("eid"));
            statement.setString(2, event.get("ename"));
            statement.setString(3, event.get("edescription"));
            statement.setString(4, event.get("edate"));
            statement.setString(5, event.get("eplace"));
            statement.executeUpdate();
            resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appone", "root", "Ijse@1234");
            Map<String, String> event = new ObjectMapper().readValue(req.getReader(), Map.class);
            PreparedStatement statement = connection.prepareStatement("UPDATE events SET ename = ?, edescription = ?, edate = ?, eplace = ? WHERE eid = ?");
            statement.setString(1, event.get("ename"));
            statement.setString(2, event.get("edescription"));
            statement.setString(3, event.get("edate"));
            statement.setString(4, event.get("eplace"));
            statement.setString(5, event.get("eid"));
            statement.executeUpdate();
            resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appone", "root", "Ijse@1234");
            String eid = req.getParameter("eid");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM events WHERE eid = ?");
            statement.setString(1, eid);
            statement.executeUpdate();
            resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}