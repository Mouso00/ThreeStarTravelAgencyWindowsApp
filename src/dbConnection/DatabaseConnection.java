package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	
	    private Connection connection;
	    private final String url = "jdbc:mysql://localhost:3306/threestartravelagencydb";
	    private final String username = "your_username";
	    private final String password = "your_password";

	    public DatabaseConnection() 
	    {
	        try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public boolean checkLogin(String inputUsername, String inputPassword) 
	    {
	        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, inputUsername);
	            preparedStatement.setString(2, inputPassword);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            return resultSet.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    public void closeConnection() 
	    {
	        try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}


