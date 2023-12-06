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
import models.User;

public class ReservationDAO {
    private static final String GET_CITIES_QUERY = "SELECT * FROM cities";
    private static final String INSERT_RESERVATION_QUERY = "INSERT INTO reservations (train_number, class_type, date_of_journey, source_location, destination_location, status, time_of_journey, seat, price,user_id) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";


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

    private static String generatePNR() {
        // Implement your logic to generate a unique PNR
        // This can be a combination of letters, numbers, or any format you prefer
        return "PNR" + System.currentTimeMillis();
    }

    public static void insertReservation(Reservation reservation) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS)) {

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
            preparedStatement.setString(10, reservation.getUserId());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedKey = generatedKeys.getInt(1);
                // Do something with the generated key if needed
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    private static final String INSERT_USER_QUERY = "INSERT INTO users (username, password, role, full_name, email_address, gender, date_of_birth) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static int insertUser(User user) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getFull_name());
            preparedStatement.setString(5, user.getEmail_address());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setDate(7, user.getDate_of_birth());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return -1; // Return -1 for failure
    }

    public static void insertReservationWithUser(Reservation reservation, User user) {
        int userId = insertUser(user);
        if (userId != -1) {
            reservation.setUserId(String.valueOf(userId));
            insertReservation(reservation);
        }
    }
}
