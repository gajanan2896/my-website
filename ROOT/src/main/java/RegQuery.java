import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegQuery {
    public static void main(String[] args) throws SQLException {
        
            // Establishing connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
            System.out.println("Connected to the database!");

            // Creating a statement
            Statement statement = connection.createStatement();
             
            // Use the database
            statement.executeUpdate("USE esusers");

            // Correct SQL query
            String query = "INSERT INTO esuserdata (username, dob, contact, password) " +
                           "VALUES ('Poonam', '1996-04-28', '9545000828', 'GajaGaja')";
            
            // Executing the query
            statement.executeUpdate(query);

            System.out.println("Record inserted successfully!");

            // Closing the connection
            connection.close();

    }
}
