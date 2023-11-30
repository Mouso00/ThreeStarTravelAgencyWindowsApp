package javagui;

import com.toedter.calendar.JDateChooser;
import dbConnection.DatabaseConnection;
import models.TrainRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchTrainView extends JFrame {

    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JDateChooser dateChooser;
    private JComboBox<String> timeComboBox;
    private JComboBox<String> classComboBox;

    public SearchTrainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Search Train");
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Search for Trains");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(300, 30, 200, 40);
        panel.add(titleLabel);

        int labelX = 100;
        int labelY = 100;
        int comboBoxX = 250;
        int comboBoxY = 100;
        int labelComboBoxGap = 50;
        int comboBoxWidth = 150;
        int comboBoxHeight = 30;

        // Use actual data from the database instead of dummy data
        createLabelAndComboBox(panel, "From:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getCityOptions());
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        createLabelAndComboBox(panel, "To:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getCityOptions());
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        // Add a JDateChooser for date selection
        dateChooser = new JDateChooser();
        dateChooser.setBounds(comboBoxX, comboBoxY, 150, 30);
        panel.add(dateChooser);

        // Add a separate field for date and time
        createLabelAndComboBox(panel, "Date of Journey:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getDateOptions());
        labelY += labelComboBoxGap;
        comboBoxX += 20;
        comboBoxY += labelComboBoxGap;

        timeComboBox = createLabelAndComboBox(panel, "Time:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getTimeOptions());
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        classComboBox = createLabelAndComboBox(panel, "Class:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getClassOptions());

        JButton reservationButton = new JButton("Search");
        reservationButton.setForeground(Color.WHITE);
        reservationButton.setBackground(new Color(54, 100, 139));
        reservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the reservation logic here (if needed)
                List<TrainRecord> trainRecords = getTrainRecordsFromDatabase();
                displaySearchResults(trainRecords);
            }
        });
        reservationButton.setFont(new Font("Arial", Font.BOLD, 16));
        reservationButton.setBounds(300, 400, 150, 40);
        panel.add(reservationButton);

        pack();
        setLocationRelativeTo(null);
    }

    private JComboBox<String> createLabelAndComboBox(JPanel panel, String labelText, int labelX, int labelY, int comboBoxX, int comboBoxY, int comboBoxWidth, int comboBoxHeight, String[] options) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setBounds(labelX, labelY, 120, 30);
        panel.add(label);

        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight);
        panel.add(comboBox);

        return comboBox;
    }

    private String[] getCityOptions() {
        List<String> cityList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT city_name FROM cities";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String cityName = resultSet.getString("city_name");
                        cityList.add(cityName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or show an error message
        }

        // Convert the list of cities to an array
        return cityList.toArray(new String[0]);
    }


    private String[] getDateOptions() {
        List<String> dateList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT DISTINCT date_of_journey FROM reservations";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        java.sql.Date dateOfJourney = resultSet.getDate("date_of_journey");
                        // You may want to format the date to a user-friendly string using SimpleDateFormat
                        dateList.add(dateOfJourney.toString());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or show an error message
        }

        // Convert the list of dates to an array
        return dateList.toArray(new String[0]);
    }


    private String[] getTimeOptions() {
        List<String> timeList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT DISTINCT departure_time FROM trains";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String departureTime = resultSet.getString("departure_time");
                        timeList.add(departureTime);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or show an error message
        }

        // Convert the list of times to an array
        return timeList.toArray(new String[0]);
    }


    private String[] getClassOptions() {
        List<String> classList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT DISTINCT class_type FROM trains";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String classType = resultSet.getString("class_type");
                        classList.add(classType);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or show an error message
        }

        // Convert the list of class types to an array
        return classList.toArray(new String[0]);
    }


    private List<TrainRecord> getTrainRecordsFromDatabase() {
        List<TrainRecord> trainRecords = new ArrayList<>();

        // Replace this with actual logic to retrieve train records from the database
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Modify the query according to your database schema
            String query = "SELECT train_number, seat_number, departure_time, price FROM trains";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int trainNumber = resultSet.getInt("train_number");
                        String seatNumber = resultSet.getString("seat_number");
                        String departureTime = resultSet.getString("departure_time");
                        double price = resultSet.getDouble("price");

                        TrainRecord trainRecord = new TrainRecord(trainNumber, seatNumber, departureTime, price); // Create a TrainRecord object with the retrieved data
                                

                                // Add the TrainRecord to the list
                                trainRecords.add(trainRecord);
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception or show an error message
                }

                return trainRecords;
            }

    private void displaySearchResults(List<TrainRecord> trainRecords) {
        // Pass the trainRecords to the SearchResultsView
        SearchResultsView searchResultsView = new SearchResultsView(trainRecords);
        
        // Update the UI components in SearchResultsView to display the search results
        searchResultsView.initializeUI();
        
        // Make the SearchResultsView visible
        searchResultsView.setVisible(true);
        
        // Close the current SearchTrainView frame
        dispose();
    }

}