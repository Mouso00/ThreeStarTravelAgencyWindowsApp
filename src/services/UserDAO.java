package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import dbConnection.DatabaseConnection;
import javagui.Authentication.Menu;
import models.User;

public class UserDAO {
    private static final String INSERT_USER_QUERY = "INSERT INTO users (username, password, role, created_at) VALUES (?, ?, ?, NOW())";

    private static final String GET_USER_FULL_NAME = "SELECT full_name FROM users where id = ?";
    		
    
    public static void insertUser(Connection connection, User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }            
            
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        
       
    }

    public static String getUserFullName(String userId) {
    	String fullName = "";
    	 try (Connection connection = DatabaseConnection.getConnection();
    			 PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_FULL_NAME);){
    		 preparedStatement.setString(1, userId);
    	 
    		  try (ResultSet resultSet = preparedStatement.executeQuery()) {
                  if (resultSet.next()) {
                      // Authentication successful
                      fullName = resultSet.getString(1);
                  }
    		  }
    	 }catch (SQLException e) {
             e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error.");
    	 }
    	return fullName;
    }

}

