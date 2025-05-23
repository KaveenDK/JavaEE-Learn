package lk.ijse.edu;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --------------------------------------------
 * @Author Dimantha Kaveen
 * @GitHub: https://github.com/KaveenDK
 * --------------------------------------------
 * @Created 5/19/2025
 * @Project JavaEE-learn
 * --------------------------------------------
 **/

@WebServlet("/mime")
//@MultipartConfig
public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("MIME TYPE TEST");
    }
    // method to test the raw request body
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String body = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));
//
//        resp.setContentType("text/plain");
//        resp.getWriter().write("Received as text/plain:\n" + body);
//    }

    // method to test the x-www-form-urlencoded request body
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        String address = req.getParameter("address");
//
//        resp.setContentType("text/plain");
//        resp.getWriter().println("Received as x-www-form-urlencoded:\nName:" + name + "\nAddress:" + address);
//    }

    //read multipart /form-data from httpRequest body
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        Part part = req.getPart("file");
//        String fileName = part.getSubmittedFileName();
//
//        resp.setContentType("text/plain");
//        resp.getWriter().println("Received as multipart/form-data:\nName: " + name + "\nFile Name: " + fileName);
//    }

    // read JSON data form httpRequest body
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(req.getReader());
//
//        String name = jsonNode.get("name").asText();
//        String address = jsonNode.get("address").asText();
//
//        resp.setContentType("text/plain");
//        resp.getWriter().println("Received as application/json:\nName: " + name + "\nAddress: " + address);
//    }

    // read JSON Array data form httpRequest body - using forEach
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> users = mapper.readValue(req.getReader(), new TypeReference<List<JsonNode>>() {
        });
        for (JsonNode user : users) {
            String name = user.get("name").asText();
            String address = user.get("address").asText();
            System.out.println("Name: " + name + "\nAddress: " + address);
        }
        resp.getWriter().write("OK");
    }

//        JsonNode jsonNode = mapper.readTree(req.getReader());
//
//        StringBuilder sb = new StringBuilder();
//        jsonNode.forEach(node -> {
//            String name = node.get("name").asText();
//            String address = node.get("address").asText();
//            sb.append("Name: ").append(name).append("\nAddress: ").append(address).append("\n");
//        });
//
//                resp.setContentType("text/plain");
//        resp.getWriter().println("Received as application/json:\n" + sb.toString());
//    }
}
