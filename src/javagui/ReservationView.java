package javagui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationView extends JFrame {

    private JPanel contentPane;
    private JComboBox<String> fromComboBox, toComboBox, passengerComboBox, classComboBox;
    private JTextField dateField;

    public ReservationView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Train Reservation");
        setBounds(100, 100, 800, 700);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(54, 100, 139));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        contentPane.add(formPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("From:"), gbc);
        String[] fromOptions = {"Source1", "Source2", "Source3"};
        fromComboBox = new JComboBox<>(fromOptions);
        gbc.gridx = 1;
        formPanel.add(fromComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("To:"), gbc);
        String[] toOptions = {"Destination1", "Destination2", "Destination3"};
        toComboBox = new JComboBox<>(toOptions);
        gbc.gridx = 1;
        formPanel.add(toComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Passenger:"), gbc);
        String[] passengerOptions = {"1 Adult", "2 Adults", "1 Adult + 1 Child (Age 15)"};
        passengerComboBox = new JComboBox<>(passengerOptions);
        gbc.gridx = 1;
        formPanel.add(passengerComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Class:"), gbc);
        String[] classOptions = {"First Class", "Second Class", "Economy"};
        classComboBox = new JComboBox<>(classOptions);
        gbc.gridx = 1;
        formPanel.add(classComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Date of Journey:"), gbc);
        dateField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        // Reservation Button
        JButton reserveButton = createStyledButton("Reserve");
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement the reservation logic here (saving data to a database, for example)
                // After saving, you can display a confirmation message or navigate back to the main menu
                dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(reserveButton, gbc);

        // Navigation Button (Back to Main Menu)
        JButton backButton = createStyledButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and display the Menu frame (Main Menu)
                Menu menuFrame = new Menu();
                menuFrame.setVisible(true);

                // Close the current ReservationView frame
                dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets.top = 100;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(backButton, gbc);
        
        setMinimumSize(new Dimension(800, 700)); // Set the minimum size of the frame
        setBounds(100, 100, 800, 700); // Set the initial size
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(54, 100, 139));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReservationView frame = new ReservationView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
