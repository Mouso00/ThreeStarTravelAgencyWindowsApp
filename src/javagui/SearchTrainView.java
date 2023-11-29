package javagui;

import com.toedter.calendar.JDateChooser;
import dbConnection.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

public class SearchTrainView extends JFrame {

    private JComboBox<String> trainComboBox;

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

        // Add a JComboBox for train selection
        trainComboBox = new JComboBox<>();
        trainComboBox.setBounds(comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight);
        panel.add(trainComboBox);

        // Add a JDateChooser for date selection
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(comboBoxX, comboBoxY + labelComboBoxGap, 150, 30);
        panel.add(dateChooser);

        // Add a separate field for date and time (if needed)
        // ...

        JButton reservationButton = new JButton("Search");
        reservationButton.setForeground(Color.WHITE);
        reservationButton.setBackground(new Color(54, 100, 139));
        reservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the reservation logic here (if needed)

                // Navigate to the SearchResultsView
                SearchResultsView searchResultsView = new SearchResultsView();
                searchResultsView.setVisible(true);
                dispose(); // Close the current SearchTrainView frame
            }
        });
        reservationButton.setFont(new Font("Arial", Font.BOLD, 16));
        reservationButton.setBounds(300, 400, 150, 40);
        panel.add(reservationButton);

        // Retrieve train records from the database
        getTrainRecordsFromDatabase();

        pack();
        setLocationRelativeTo(null);
    }

    private void getTrainRecordsFromDatabase() {
        // Clear existing data
        trainComboBox.removeAllItems();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT train_id, train_number, seat_number, departure_time, price FROM trains";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int trainId = resultSet.getInt("train_id");
                        int trainNumber = resultSet.getInt("train_number");
                        String seatNumber = resultSet.getString("seat_number");
                        Timestamp departureTime = resultSet.getTimestamp("departure_time");
                        BigDecimal price = resultSet.getBigDecimal("price");

                        // Format the departure time (you can adjust the format as needed)
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedTime = dateFormat.format(departureTime);

                        // Create a display string
                        String displayString = String.format("Train ID: %d | Train Number: %d | Seat Number: %s | Departure Time: %s | Price: $%.2f",
                                trainId, trainNumber, seatNumber, formattedTime, price);

                        // Add the display string to the combo box
                        trainComboBox.addItem(displayString);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to retrieve train records.");
        }
    }
}



//package javagui;
//
//import com.toedter.calendar.JDateChooser;
//import dbConnection.DatabaseConnection;
//import models.TrainRecord;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SearchTrainView extends JFrame {
//
//    private JComboBox<String> fromComboBox;
//    private JComboBox<String> toComboBox;
//    private JDateChooser dateChooser;
//    private JComboBox<String> timeComboBox;
//    private JComboBox<String> classComboBox;
//
//    public SearchTrainView() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("Search Train");
//        setPreferredSize(new Dimension(800, 600));
//        setResizable(false);
//
//        JPanel contentPane = new JPanel();
//        setContentPane(contentPane);
//        contentPane.setBackground(new Color(248, 248, 248));
//        contentPane.setLayout(null);
//
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(54, 100, 139));
//        panel.setBounds(0, 0, 800, 600);
//        contentPane.add(panel);
//        panel.setLayout(null);
//
//        JLabel titleLabel = new JLabel("Search for Trains");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        titleLabel.setForeground(Color.WHITE);
//        titleLabel.setBounds(300, 30, 200, 40);
//        panel.add(titleLabel);
//
//        int labelX = 100;
//        int labelY = 100;
//        int comboBoxX = 250;
//        int comboBoxY = 100;
//        int labelComboBoxGap = 50;
//        int comboBoxWidth = 150;
//        int comboBoxHeight = 30;
//
//        createLabelAndComboBox(panel, "From:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getCityOptions());
//        labelY += labelComboBoxGap;
//        comboBoxY += labelComboBoxGap;
//
//        createLabelAndComboBox(panel, "To:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getCityOptions());
//        labelY += labelComboBoxGap;
//        comboBoxY += labelComboBoxGap;
//
//        // Add a JDateChooser for date selection
//        dateChooser = new JDateChooser();
//        dateChooser.setBounds(comboBoxX, comboBoxY, 150, 30);
//        panel.add(dateChooser);
//
//        // Add a separate field for date and time
//        createLabelAndComboBox(panel, "Date of Journey:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getDateOptions());
//        labelY += labelComboBoxGap;
//        comboBoxX += 20;
//        comboBoxY += labelComboBoxGap;
//
//        timeComboBox = createLabelAndComboBox(panel, "Time:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getTimeOptions());
//        labelY += labelComboBoxGap;
//        comboBoxY += labelComboBoxGap;
//
//        classComboBox = createLabelAndComboBox(panel, "Class:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getClassOptions());
//
//        JButton reservationButton = new JButton("Search");
//        reservationButton.setForeground(Color.WHITE);
//        reservationButton.setBackground(new Color(54, 100, 139));
//        reservationButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Handle the reservation logic here (if needed)
//                List<TrainRecord> trainRecords = getTrainRecordsFromDatabase();
//                displaySearchResults(trainRecords);
//            }
//        });
//        reservationButton.setFont(new Font("Arial", Font.BOLD, 16));
//        reservationButton.setBounds(300, 400, 150, 40);
//        panel.add(reservationButton);
//
//        pack();
//        setLocationRelativeTo(null);
//    }
//
//    private JComboBox<String> createLabelAndComboBox(JPanel panel, String labelText, int labelX, int labelY, int comboBoxX, int comboBoxY, int comboBoxWidth, int comboBoxHeight, String[] options) {
//        JLabel label = new JLabel(labelText);
//        label.setFont(new Font("Arial", Font.BOLD, 16));
//        label.setForeground(Color.WHITE);
//        label.setBounds(labelX, labelY, 120, 30);
//        panel.add(label);
//
//        JComboBox<String> comboBox = new JComboBox<>(options);
//        comboBox.setBounds(comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight);
//        panel.add(comboBox);
//
//        return comboBox;
//    }
//
//    private String[] getCityOptions() {
//        // Replace this with actual logic to get city options from the database
//        return new String[]{"City A", "City B", "City C"};
//    }
//
//    private String[] getDateOptions() {
//        // Replace this with actual logic to get date options from the database
//        return new String[]{"2023-11-10"};
//    }
//
//    private String[] getTimeOptions() {
//        // Replace this with actual logic to get time options from the database
//        return new String[]{
//                "12:00 AM", "1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM",
//                "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM",
//                "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM",
//                "9:00 PM", "10:00 PM", "11:00 PM"
//        };
//    }
//
//    private String[] getClassOptions() {
//        // Replace this with actual logic to get class options from the database
//        return new String[]{"First Class", "Second Class", "Economy"};
//    }
//
//    private List<TrainRecord> getTrainRecordsFromDatabase() {
//        List<TrainRecord> trainRecords = new ArrayList<>();
//
//        // Replace this with actual logic to retrieve train records from the database
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            String query = "SELECT train_number, seat_number, time, price FROM trains";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        int trainNumber = resultSet.getInt("train_number");
//                        String seatNumber = resultSet.getString("seat_number");
//                        String time = resultSet.getString("time");
//                        double price = resultSet.getDouble("price");
//
//                        TrainRecord trainRecord = new TrainRecord(trainNumber, seatNumber, time, price);
//                        trainRecords.add(trainRecord);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle the exception or show an error message
//        }
//
//        return trainRecords;
//    }
//
//    private void displaySearchResults(List<TrainRecord> trainRecords) {
//        // Replace this with actual logic to display search results in the UI
//        // For simplicity, just printing the records to the console here
//        for (TrainRecord record : trainRecords) {
//            System.out.println(record);
//        }
//
//        // You can update this part to display the results in a new window or update the current UI
//        SearchResultsView searchResultsView = new SearchResultsView();
//        searchResultsView.setVisible(true);
//        dispose(); // Close the current SearchTrainView frame
//    }
//}