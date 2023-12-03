package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	
	private static final String INSERT_RESERVATION_QUERY = "INSERT INTO reservations (train_number, class_type, date_of_journey, source_location, destination_location, status,time_of_journey,seat,price) VALUES (?,?,?,?, ?, ?, ?, ?, ?)";

	public static void insertReservation(Reservation reservation) {
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATION_QUERY)) {

	    	 preparedStatement.setInt(1, reservation.getTrainNumber());
	            preparedStatement.setString(2, reservation.getTravelClass());

	        // Convert the date string to the MySQL date format 'YYYY-MM-DD'
	        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
	        Date parsedDate = new Date(dateFormat.parse(reservation.getDate()).getTime());
	        preparedStatement.setDate(3, parsedDate);

	        preparedStatement.setString(4, reservation.getFrom());
	        preparedStatement.setString(5, reservation.getTo());
	        preparedStatement.setString(6, reservation.getStatus());
	        preparedStatement.setString(7, reservation.getTime());
	        preparedStatement.setString(8, reservation.getSeat());
	        preparedStatement.setDouble(9, reservation.getPrice());


	        preparedStatement.executeUpdate();

	    } catch (SQLException | ParseException e) {
	        e.printStackTrace(); // Handle the exception according to your needs
	    }
	}

	


}
