package lk.ijse.edu;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

/**
 * --------------------------------------------
 * @Author Dimantha Kaveen
 * @GitHub: https://github.com/KaveenDK
 * --------------------------------------------
 * @Created 6/2/2025
 * @Project JavaEE-learn
 * --------------------------------------------
 **/

@WebListener
public class ContextListener implements ServletContextListener {
    private BasicDataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/appone");
        ds.setUsername("root");
        ds.setPassword("Ijse@1234");
        ds.setInitialSize(5);
        ds.setMaxTotal(50);

        //add a dataSource instance to the ServletContext
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("dataSource", ds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //remove the dataSource instance from the ServletContext
        try {
            ServletContext servletContext = sce.getServletContext();
            BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dataSource");
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
