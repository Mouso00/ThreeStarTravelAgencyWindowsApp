package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DatabaseConnection;

public class ReservationDAO {
	private static final String GET_CITIES_QUERY = "SELECT * FROM cities";

	public static String[] getCityOptions() {
	    try (Connection connection = DatabaseConnection.getConnection();
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery(GET_CITIES_QUERY)) {

	        List<String> cityOptions = new ArrayList<>();

	        while (resultSet.next()) {
	            String cityName = resultSet.getString("city_name");
	            cityOptions.add(cityName);
	        }

	        return cityOptions.toArray(new String[0]);

	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle the exception according to your needs
	    }

	    return new String[]{""}; // Return a default value if there's an error
	}
	


}
