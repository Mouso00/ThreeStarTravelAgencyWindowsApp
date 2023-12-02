package javagui.Reservation;

import javax.swing.*;

import models.Reservation;
import services.ReservationDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmAndProceedView extends JFrame {
	
    private String userIdString;
    private String selectedFrom;
    private String selectedTo;
    private String selectedDate;
    private String selectedTime;
    private String selectedClass;
    private String selectedSeat;

    
    public ConfirmAndProceedView(String userIdString, String from, String to, String date, String time, String travelClass, String selectedSeat) {
        this.userIdString = userIdString;
        this.selectedFrom = from;
        this.selectedTo = to;
        this.selectedDate = date;
        this.selectedTime = time;
        this.selectedClass = travelClass;
        this.selectedSeat = selectedSeat;

        initializeUI();
    }



    public void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Confirm and Proceed");

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Confirm and Proceed");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(280, 30, 300, 40);
        panel.add(titleLabel);

        JLabel fromLabel = new JLabel("From: " + selectedFrom);
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setBounds(50, 100, 400, 20);
        panel.add(fromLabel);

        JLabel toLabel = new JLabel("To: " + selectedTo);
        toLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        toLabel.setForeground(Color.WHITE);
        toLabel.setBounds(50, 130, 400, 20);
        panel.add(toLabel);

        JLabel dateLabel = new JLabel("Date: " + selectedDate);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setBounds(50, 160, 400, 20);
        panel.add(dateLabel);

        JLabel timeLabel = new JLabel("Time: " + selectedTime);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(50, 190, 400, 20);
        panel.add(timeLabel);

        JLabel classLabel = new JLabel("Class: " + selectedClass);
        classLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        classLabel.setForeground(Color.WHITE);
        classLabel.setBounds(50, 220, 400, 20);
        
        panel.add(classLabel);
        JLabel seatLabel = new JLabel("Seat: " + selectedSeat);
        seatLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        seatLabel.setForeground(Color.WHITE);
        seatLabel.setBounds(50, 280, 400, 20);
        panel.add(seatLabel);

        JLabel priceLabel = new JLabel("Price: $" + calculatePrice());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setBounds(50, 250, 400, 20);
        panel.add(priceLabel);

        int yOffset = 300;

        JButton agreeAndPayButton = new JButton("Agree and Pay");
        agreeAndPayButton.setForeground(Color.WHITE);
        agreeAndPayButton.setBackground(new Color(54, 100, 139));
        agreeAndPayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showConfirmationMessage();
                // Call the method to insert reservation into the database
                insertReservationIntoDatabase(selectedSeat);
            }
        });
        agreeAndPayButton.setFont(new Font("Arial", Font.BOLD, 16));
        agreeAndPayButton.setBounds(350, yOffset, 150, 40);
        panel.add(agreeAndPayButton);

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(54, 100, 139));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchResultsView();
                dispose();
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(50, yOffset, 100, 40);
        panel.add(backButton);

        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 800, 600);
    }
    private double calculatePrice() {
        // Basic pricing logic based on the selected class
        double basePrice;
        switch (selectedClass) {
            case "Economy":
                basePrice = 100.0;
                break;
            case "Premium":
                basePrice = 150.0;
                break;
            case "Salon":
                basePrice = 200.0;
                break;
            default:
                basePrice = 0.0;
        }

        // You can add more logic based on other factors if needed

        return basePrice;
    }
    private void showConfirmationMessage() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to book this trip?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            openPayView();
            dispose();
        }
    }
    private void insertReservationIntoDatabase(String selectedSeat) {
        int userId = 0;  // Default value

        try {
            if (userIdString != null && !userIdString.isEmpty()) {
                userId = Integer.parseInt(userIdString);
            }
        } catch (NumberFormatException e) {
            // Handle the case where userIdString is not a valid integer
            // You can log the error or show a message to the user
            e.printStackTrace();
        }

        // Create a Reservation object with the selected information
        Reservation reservation = new Reservation(selectedFrom, selectedTo, selectedDate, selectedTime, selectedClass, selectedSeat, calculatePrice(), userId);

        // Call the insertReservation method from ReservationDAO
        ReservationDAO.insertReservation(reservation);
    }





    private void openSearchResultsView() {
        SearchResultsView searchResultsView = new SearchResultsView(selectedFrom, selectedTo, selectedDate, selectedTime, selectedClass);
        searchResultsView.setVisible(true);
        dispose();
    }

    private void openPayView() {
        PayView payView = new PayView();
        payView.setVisible(true);
    }
}

