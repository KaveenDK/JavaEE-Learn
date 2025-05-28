package lk.ijse.edu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.jdbc.DatabaseMetaData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

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
 * @Created 5/26/2025
 * @Project JavaEE-learn
 * --------------------------------------------
 **/

@WebServlet("/connectionpool")
public class ConnectionPoolExampleServlet extends HttpServlet {

    private BasicDataSource ds;

    @Override
    public void init() throws ServletException {
        // 50
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/appone");
        ds.setUsername("root");
        ds.setPassword("Ijse@1234");
        ds.setInitialSize(5); // Initial number of connections
        ds.setMaxTotal(50); // Maximum number of connections in the pool
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Connection connection = ds.getConnection();
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
            resp.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), elist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = ds.getConnection();
            Map<String, String> event = new ObjectMapper().readValue(req.getReader(), Map.class);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO events (eid, ename, edescription, edate, eplace) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, event.get("eid"));
            statement.setString(2, event.get("ename"));
            statement.setString(3, event.get("edescription"));
            statement.setString(4, event.get("edate"));
            statement.setString(5, event.get("eplace"));
            statement.executeUpdate();
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = ds.getConnection();
            Map<String, String> event = new ObjectMapper().readValue(req.getReader(), Map.class);
            PreparedStatement statement = connection.prepareStatement("UPDATE events SET ename = ?, edescription = ?, edate = ?, eplace = ? WHERE eid = ?");
            statement.setString(1, event.get("ename"));
            statement.setString(2, event.get("edescription"));
            statement.setString(3, event.get("edate"));
            statement.setString(4, event.get("eplace"));
            statement.setString(5, event.get("eid"));
            statement.executeUpdate();
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = ds.getConnection();
            String eid = req.getParameter("eid");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM events WHERE eid = ?");
            statement.setString(1, eid);
            statement.executeUpdate();
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
