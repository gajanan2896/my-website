import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/signup/registration")
public class registration extends HttpServlet {

	
    // Handle POST request
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        // Set the character encoding for the request to UTF-8
        req.setCharacterEncoding("UTF-8");
        
        // Retrieve form parameters
        String username = req.getParameter("username");
        String dob = req.getParameter("dob");
        String contact = req.getParameter("contact");
        String password = req.getParameter("password");
        
        try {
			query(username, dob, contact, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Prepare the response (send confirmation to the user)
        res.setContentType("text/html");
        
        PrintWriter out = res.getWriter();
        
        res.sendRedirect("http://localhost:8080/signin/login.html");
    }
    
    public static void query(String username, String dob, String contact, String password) throws ClassNotFoundException, SQLException {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establishing connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/esusers?user=root&password=root");
        System.out.println("Connected to the database!");
         
        // Creating a statement
        Statement statement = connection.createStatement();
          
        // Use the database
        statement.executeUpdate("USE esusers");

        // Correct SQL query
        String query = "INSERT INTO esuserdata (username, dob, contact, password)" +
                       "VALUES ('" + username + "', '" + dob + "', '" + contact + "', '" + password + "')";

        // Executing the query
        statement.executeUpdate(query);

        System.out.println("Record inserted successfully!");

        // Closing the connection
        connection.close();
    }

}