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
    private static final String GET_RESERVATION_BY_PNR = "SELECT * FROM reservations WHERE pnr_of_reservation = ?";
    private static final String INSERT_RESERVATION_QUERY = "INSERT INTO reservations (train_number, class_type, date_of_journey, source_location, destination_location, status, time_of_journey, seat, price,user_id,pnr_of_reservation) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    private static final String GET_RESERVATIONS_BY_USER_ID = "SELECT * FROM reservations WHERE user_id = ?";
    private static final String CHECK_RESERVATION_EXISTS_QUERY = "SELECT * FROM reservations WHERE user_id = ? AND source_location = ? AND destination_location = ? AND date_of_journey = ? AND time_of_journey = ? AND class_type = ? AND seat = ?";

    public static List<Reservation> getReservationsByUserId(String userId) {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATIONS_BY_USER_ID)) {

            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    int trainNumber = resultSet.getInt("train_number");
                    String dateOfJourney = resultSet.getString("date_of_journey");
                    String sourceLocation = resultSet.getString("source_location");
                    String destinationLocation = resultSet.getString("destination_location");
                    int pnr = resultSet.getInt("pnr_of_reservation");
                    String time = resultSet.getString("time_of_journey");

                    reservation.setTrainNumber(trainNumber);
                    reservation.setDate(dateOfJourney);
                    reservation.setFrom(sourceLocation);
                    reservation.setTo(destinationLocation);
                    reservation.setGenaretedPnr(pnr);
                    reservation.setTime(time);

                    reservations.add(reservation);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return reservations;
    }

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

    public static Reservation getReservationByPnr(String pnr) {
        Reservation reservation = new Reservation();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATION_BY_PNR)) {

            preparedStatement.setString(1, pnr);  // Set the value for the placeholder

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int trainNumber = resultSet.getInt("train_number");
                    String classType = resultSet.getString("class_type");
                    String dateOfJourney = resultSet.getString("date_of_journey");
                    String sourceLocation = resultSet.getString("source_location");
                    String destinationLocation = resultSet.getString("destination_location");
                    String status = resultSet.getString("status");
                    String time = resultSet.getString("time_of_journey");
                    String seat = resultSet.getString("seat");
                    double price = resultSet.getDouble("price");

                    reservation.setTrainNumber(trainNumber);
                    reservation.setTravelClass(classType);
                    reservation.setDate(dateOfJourney);
                    reservation.setFrom(sourceLocation);
                    reservation.setTo(destinationLocation);
                    reservation.setStatus(status);
                    reservation.setTime(time);
                    reservation.setSeat(seat);
                    reservation.setPrice(price);
                    // Set other fields as needed
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return reservation;
    }

    public static String getReservationInfoByPnr(String pnr) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATION_BY_PNR)) {

            preparedStatement.setString(1, pnr);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Modify this part to extract and format the relevant information from the result set
                    int trainNumber = resultSet.getInt("train_number");
                    String seat = resultSet.getString("seat");
                    // ... other fields

                    return "PNR: " + pnr + "\nTrain Number: " + trainNumber + "\nSeat: " + seat;
                } else {
                    return null; // No reservation found for the given PNR
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return null; // Return null in case of an error
        }
    }

    public static void insertReservation(Reservation reservation) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, reservation.getTrainNumber());
            preparedStatement.setString(2, reservation.getTravelClass());

            // Convert the date string to the MySQL date format 'YYYY-MM-DD'
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

            Date parsedDate = new Date(dateFormat.parse(reservation.getDate()).getTime());
            preparedStatement.setDate(3, parsedDate);

            preparedStatement.setString(4, reservation.getFrom());
            preparedStatement.setString(5, reservation.getTo());
            preparedStatement.setString(6, reservation.getStatus());
            preparedStatement.setString(7, reservation.getTime());
            preparedStatement.setString(8, reservation.getSeat());
            preparedStatement.setDouble(9, reservation.getPrice());
            preparedStatement.setString(10, reservation.getUserId());
            preparedStatement.setInt(11, reservation.getGenaretedPnr());

            preparedStatement.executeUpdate();

        } catch (SQLException | ParseException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static boolean deleteReservationByPnr(String pnr) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM reservations WHERE pnr_of_reservation = ?")) {

            preparedStatement.setString(1, pnr);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return false; // Return false in case of an error
        }
    }

    public static boolean isReservationExists(String userId, String from, String to, String date, String time, String travelClass, String seat) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_RESERVATION_EXISTS_QUERY)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, from);
            preparedStatement.setString(3, to);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, time);
            preparedStatement.setString(6, travelClass);
            preparedStatement.setString(7, seat);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If there is a result, the reservation already exists
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return true; // Return true in case of an error
        }
    }

   
}
