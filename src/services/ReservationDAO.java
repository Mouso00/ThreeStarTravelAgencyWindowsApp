package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DatabaseConnection;
import models.Reservation;

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
	
	private static final String INSERT_RESERVATION_QUERY = "INSERT INTO reservations (user_id, passenger_id, train_number, class_type, date_of_journey, source_location, destination_location, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";




	    public static void insertReservation(Reservation reservation) {
	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATION_QUERY)) {

	            preparedStatement.setString(1, reservation.getFrom());
	            preparedStatement.setString(2, reservation.getTo());
	            preparedStatement.setString(3, reservation.getDate());
	            preparedStatement.setString(4, reservation.getTime());
	            preparedStatement.setString(5, reservation.getTravelClass());
	            preparedStatement.setString(6, reservation.getSeat());
	            preparedStatement.setDouble(7, reservation.getPrice());
	            preparedStatement.setString(8, reservation.getStatus());
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception according to your needs
	        }
	    }
	


}
