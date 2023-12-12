package javagui.UserReservations;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javagui.Dashboard.*;
import javagui.Dashboard.Menu;
import models.Reservation;
import services.ReservationDAO;

public class SeeYourReservationView extends JFrame {
	private final String userId;
    private JTextArea enquiryInfoArea;

    public SeeYourReservationView(String userId) {
    	this.userId = userId;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Your reservations");
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
        
        initializeUI();

        pack();
        setLocationRelativeTo(null);
    }
        private void initializeUI() {
        	JPanel contentPane = new JPanel();
            contentPane.setBackground(new Color(54, 100, 139));
            setContentPane(contentPane);
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("Your Reservations");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPane.add(titleLabel);

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPane.add(scrollPane);

            enquiryInfoArea = new JTextArea();
            enquiryInfoArea.setEditable(false);
            enquiryInfoArea.setWrapStyleWord(true);
            enquiryInfoArea.setLineWrap(true);
            enquiryInfoArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            enquiryInfoArea.setFont(new Font("Arial", Font.PLAIN, 16));
            enquiryInfoArea.setForeground(Color.WHITE);
            enquiryInfoArea.setBackground(new Color(54, 100, 139));
            scrollPane.setViewportView(enquiryInfoArea);

            JButton btnBackToMainMenu = createStyledButton("Back to Main Menu");
            btnBackToMainMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the current window
                    Menu menu = new Menu(userId);
                    menu.setVisible(true); // Show the main menu
                }
            });        btnBackToMainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPane.add(btnBackToMainMenu);

            // Retrieve user reservations and display them
            List<Reservation> userReservations = ReservationDAO.getReservationsByUserId(userId);
            displayUserReservations(userReservations);
        }

        private JButton createStyledButton(String text) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(new Color(54, 100, 139));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            return button;
        }

        private void displayUserReservations(List<Reservation> userReservations) {
            if (userReservations.isEmpty()) {
                showMessage("No reservations found for the current user.", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder enquiryInfo = new StringBuilder();
                for (Reservation reservation : userReservations) {
                    enquiryInfo.append("Train Number: ").append(reservation.getTrainNumber()).append("\n");
                    enquiryInfo.append("Date of Journey: ").append(reservation.getDate()).append("\n");
                    enquiryInfo.append("Source Location: ").append(reservation.getFrom()).append("\n");
                    enquiryInfo.append("Time of Journey: ").append(reservation.getTime()).append("\n");
                    enquiryInfo.append("Destination Location: ").append(reservation.getTo()).append("\n");
                    enquiryInfo.append("PNR: ").append(reservation.getGenaretedPnr()).append("\n\n");
                    
                }
                enquiryInfoArea.setText(enquiryInfo.toString());
                enquiryInfoArea.setVisible(true);
            }
        }

        private void showMessage(String message, String title, int messageType) {
            JOptionPane.showMessageDialog(this, message, title, messageType);
        }

        
        
}
