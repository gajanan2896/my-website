<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, com.mysql.cj.jdbc.Driver" %>
<%
    String username = request.getParameter("username");
    String dob = request.getParameter("dob");
    String contact = request.getParameter("contact");
    String password = request.getParameter("password");

    if (username != null && dob != null && contact != null && password != null) {
        try {
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/esusers?user=root&password=root");

            // Insert data using prepared statement
            String query = "INSERT INTO esuserdata (username, dob, contact, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, dob);
            stmt.setString(3, contact);
            stmt.setString(4, password);
            stmt.executeUpdate();

            // Close the connection
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Examsquare - Registration</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #fff022a6;
            margin: 0;
            font-family: 'Arial', sans-serif;
        }

        .form-container {
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 100%;
            max-width: 450px;
        }

        h2 {
            background-color: #a90000;
            color: white;
            text-align: center;
            border-radius: 12px;
            padding: 12px 20px;
            font-size: 24px;
            margin-bottom: 20px;
        }

        label {
            font-size: 18px;
            color: #333;
            margin-bottom: 8px;
            display: inline-block;
        }

        .input {
            width: 100%;
            height: 40px;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #bdbdbd;
            border-radius: 12px;
            font-size: 16px;
            color: #333;
        }

        input[type="submit"],
        input[type="reset"] {
            width: 48%;
            padding: 12px;
            font-size: 18px;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 10px;
        }

        input[type="submit"] {
            background-color: #000000;
            color: white;
        }

        input[type="submit"]:hover {
            background-color: #555555;
        }

        input[type="reset"] {
            background-color: #e0e0e0;
            color: #333;
        }

        input[type="reset"]:hover {
            background-color: #b0b0b0;
        }

        .form-data {
            display: flex;
            flex-direction: column;
        }

        .buttons {
            display: flex;
            justify-content: space-between;
            gap: 10px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <form action="" method="post">
            <h2>Examsquare User Registration</h2>
            <div class="form-data">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" class="input" required>
                
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" class="input" required>
                
                <label for="contact">Contact No:</label>
                <input type="number" id="contact" name="contact" class="input" required>
                
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" class="input" required>
                
                <div class="buttons">
                    <input type="submit" value="Register">
                    <input type="reset" value="Reset">
                </div>
            </div>
        </form>

        <%
            if (username != null && dob != null && contact != null && password != null) {
        %>
            <div style="margin-top: 20px; text-align: center;">
                <h3>Registration Successful!</h3>
                <p>Welcome, <%= username %>!</p>
                <p>Your date of birth is: <%= dob %></p>
                <p>Your contact number is: <%= contact %></p>
                <p>Your password is: <%= password %></p>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
