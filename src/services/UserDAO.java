package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import dbConnection.DatabaseConnection;
import javagui.Dashboard.Menu;
import models.User;

public class UserDAO {
   
    private static final String GET_USER_FULL_NAME = "SELECT full_name FROM users where id = ?";
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

