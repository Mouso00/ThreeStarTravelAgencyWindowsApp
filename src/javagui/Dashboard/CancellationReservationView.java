package javagui.Dashboard;

import javax.swing.*;

import models.Reservation;
import services.ReservationDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancellationReservationView extends JFrame {

    private String userId;

    public CancellationReservationView(String userId) {
        this.userId = userId;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cancel Reservation");
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
        initializeUI();
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeUI() {
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Cancel Reservation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(300, 30, 250, 40);
        panel.add(titleLabel);

        JLabel pnrLabel = new JLabel("Enter Passenger Reservation Number:");
        pnrLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pnrLabel.setForeground(Color.WHITE);
        pnrLabel.setBounds(250, 200, 250, 30);
        panel.add(pnrLabel);

        JTextField pnrField = new JTextField();
        pnrField.setBounds(500, 200, 150, 30);
        panel.add(pnrField);

        JButton searchButton = new JButton("Search");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(54, 100, 139));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pnr = pnrField.getText();

                // Fetch reservation information based on the entered PNR
                String pnrInfo = fetchPnrInformation(pnr);

                // Display the information in a dialog box
                int confirmation = JOptionPane.showConfirmDialog(null,
                        pnrInfo + "\n\nAre you sure you want to cancel this reservation?",
                        "Reservation Information", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    // Attempt to delete the reservation
                    boolean deletionSuccess = ReservationDAO.deleteReservationByPnr(pnr);

                    if (deletionSuccess) {
                        // Display success message
                        JOptionPane.showMessageDialog(null, "Reservation canceled successfully!", "Cancellation Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Display error message
                        JOptionPane.showMessageDialog(null, "Failed to cancel reservation.", "Cancellation Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchButton.setBounds(350, 250, 100, 40);
        panel.add(searchButton);

        JButton backButton = new JButton("Back to Menu");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(54, 100, 139));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the Menu
                Menu menuView = new Menu(userId);
                menuView.setVisible(true);
                dispose(); // Close the current CancellationView frame
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(50, 500, 150, 40);
        panel.add(backButton);
    }

    
    private String fetchPnrInformation(String pnr) {
        Reservation reservation = ReservationDAO.getReservationByPnr(pnr);

        if (reservation != null) {
            StringBuilder info = new StringBuilder();
            info.append("PNR: ").append(pnr).append("\n");
            info.append("Train Number: ").append(reservation.getTrainNumber()).append("\n");
            info.append("Class Type: ").append(reservation.getTravelClass()).append("\n");
            info.append("Date of Journey: ").append(reservation.getDate()).append("\n");
            info.append("Source Location: ").append(reservation.getFrom()).append("\n");
            info.append("Destination Location: ").append(reservation.getTo()).append("\n");
            info.append("Status: ").append(reservation.getStatus()).append("\n");
            info.append("Time of Journey: ").append(reservation.getTime()).append("\n");
            info.append("Seat: ").append(reservation.getSeat()).append("\n");
            info.append("Price: ").append(reservation.getPrice()).append("\n");

            return info.toString();
        } else {
            return "No reservation found for PNR: " + pnr;
        }
    }

   

}
