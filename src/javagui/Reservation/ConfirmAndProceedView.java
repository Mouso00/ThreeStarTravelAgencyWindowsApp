package javagui.Reservation;

import javax.swing.*;

import models.Reservation;
import services.ReservationDAO;
import services.UserDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

public class ConfirmAndProceedView extends JFrame {
	
    private final String userId;
    private String selectedFrom;
    private String selectedTo;
    private String selectedDate;
    private String selectedTime;
    private String selectedClass;
    private String selectedSeat;
    private static final Random random = new Random();
    private static boolean isPnrGenerated = false;
    private static int generatedPnr;
 
    public ConfirmAndProceedView(String userId,String from, String to, String date, String time, String travelClass, String selectedSeat) {
        this.userId = userId;
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
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

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
        
        String fullName = UserDAO.getUserFullName(userId);
        JLabel fullNameLabel = new JLabel("Name: " + fullName);
        fullNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fullNameLabel.setForeground(Color.WHITE);
        fullNameLabel.setBounds(50, 70, 400, 20);
        panel.add(fullNameLabel);

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
        backButton.setBounds(50, 500, 150, 40);
        panel.add(backButton);
   
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
                basePrice = 70.0;
                break;
            default:
                basePrice = 0.0;
        }
        return basePrice;
    }
    private void showConfirmationMessage() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to book this trip?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (insertReservation()) {
                openPayView();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to book the trip. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
  
    private boolean insertReservation() {
        try {
        	Reservation reservation = new Reservation(userId, selectedFrom, selectedTo, selectedDate, selectedTime, selectedClass);

            reservation.setTrainNumber(generateRandomTrainNumber());
            reservation.setSeat(generateRandomSeatNumber());
            reservation.setFrom(selectedFrom);
            reservation.setTo(selectedTo);
            reservation.setDate(selectedDate);
            reservation.setTime(selectedTime);
            reservation.setTravelClass(selectedClass);
            reservation.setPrice(calculatePrice()); 
            reservation.setStatus("Pending"); 
           
			reservation.setGenaretedPnr(generatePnr());

            ReservationDAO.insertReservation(reservation);
            return true; // Return true for success

        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return false; // Return false for failure
        }
    }

    private int generateRandomTrainNumber() {
        // Generate a random 3-digit train number
        return (int) (Math.random() * 900) + 100;
    }
    
 
    private static int generatePnr() {
        if (!isPnrGenerated) {
            generatedPnr = 100_000_000 + random.nextInt(900_000_000);
            isPnrGenerated = true;
        }
        return generatedPnr;
    }

    private String generateRandomSeatNumber() {
        String seatClassPrefix;
        
        switch (selectedClass) {
            case "Economy":
                seatClassPrefix = "E" + selectedSeat  ;
                break;
            case "Premium":
                seatClassPrefix = "P" + selectedSeat;
                break;
            case "Salon":
                seatClassPrefix = "S" + selectedSeat;
                break;
            default:
                seatClassPrefix = "U"; // U for unknown or default class
        }
        // Concatenate the seat class prefix and seat number
        return seatClassPrefix ;
    }

    private void openSearchResultsView() {
        TrainRecordsView searchResultsView = new TrainRecordsView(userId,selectedFrom, selectedTo, selectedDate, selectedTime, selectedClass);
        searchResultsView.setVisible(true);
        dispose();
    }
    private void openPayView() {
    	ReceiptView payView = new ReceiptView(userId,selectedFrom, selectedTo, selectedDate, selectedTime, selectedClass, selectedSeat,generatePnr());

        payView.setVisible(true);
    }
}

