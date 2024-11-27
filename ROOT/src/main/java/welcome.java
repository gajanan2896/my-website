import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signin/welcome")
public class welcome extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            // Call the query method to fetch the password
            String fetchedPassword = query(username);

            if (fetchedPassword == null) {
                // Username not found
                res.sendRedirect("http://localhost:8080/ROOT/signup/newsignup.html");
            } else if (fetchedPassword.equals(password)) {
                // Password matches
                res.sendRedirect("http://localhost:8080/ROOT/index.html");
            } else {
                // Password does not match
                res.sendRedirect("http://localhost:8080/ROOT/signin/failedlogin.html");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Send error response in case of an exception
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

    public String query(String username) throws SQLException, ClassNotFoundException {
        // Load the MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/esusers?user=root&password=root");

        // Create a statement
        Statement statement = connection.createStatement();

        // Use the database
        statement.executeUpdate("USE esusers");

        // Create the SQL query
        String sqlQuery = "SELECT password FROM esuserdata WHERE username='" + username + "';";

        // Execute the query and get the ResultSet
        ResultSet qPassword = statement.executeQuery(sqlQuery);

        String fetchedPassword = null;
        if (qPassword.next()) {
            fetchedPassword = qPassword.getString("password"); // Fetch the "password" column
        }

        // Clean up resources
        qPassword.close();
        statement.close();
        connection.close();

        return fetchedPassword;
    }
}
