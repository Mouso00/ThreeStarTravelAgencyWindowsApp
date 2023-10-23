package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class CancellationView extends JFrame {

    private JTextField pnrField;

    // Simulated database of canceled PNRs
    private Set<String> canceledPNRs = new HashSet<>();

    public CancellationView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Cancel Reservation");

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(54, 100, 139)); // Set the background color
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Title label
        JLabel titleLabel = new JLabel("Cancel Reservation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 1; // Centered horizontally
        gbc.gridy = 0;
        gbc.insets.top = 20; // Add space at the top
        contentPane.add(titleLabel, gbc);

        // New label with instructions
        JLabel instructionLabel = new JLabel("To cancel a reservation, enter the reservation number and press the button");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setForeground(Color.WHITE);
        gbc.gridx = 1; // Centered horizontally
        gbc.gridy = 1; // Below the title label
        gbc.insets.top = 10; // Add space at the top
        contentPane.add(instructionLabel, gbc);

        // PNR number field
        pnrField = new JTextField(15); // Reduced the number of columns
        pnrField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1; // Centered horizontally
        gbc.gridy = 2;
        gbc.insets.top = 30; // Adjusted to be 10 more
        gbc.insets.bottom = 20;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow the field to expand horizontally
        contentPane.add(pnrField, gbc);

        // Cancel button
        JButton btnCancel = createStyledButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pnr = pnrField.getText();

                if (pnr.isEmpty()) {
                    showMessage("PNR cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                } else if (!pnr.matches("\\d{6,}")) {
                    showMessage("PNR should be 6 digits or more.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                } else if (canceledPNRs.contains(pnr)) {
                    showMessage("PNR " + pnr + " is already canceled.", "Already Canceled", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int choice = showCancellationConfirmation(pnr);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Valid PNR, proceed with cancellation
                        String cancellationInfo = getCancellationInfo(pnr);
                        canceledPNRs.add(pnr); // Simulate adding to canceled PNRs
                        showMessage("Cancellation Successful for PNR: " + pnr, "Cancellation Successful", JOptionPane.INFORMATION_MESSAGE);

                        choice = showConfirm("Return to Main Menu?", "Return to Main Menu");
                        if (choice == JOptionPane.YES_OPTION) {
                            Menu mainMenu = new Menu();
                            mainMenu.setVisible(true);
                            dispose();
                        }
                    }
                }
            }
        });

        gbc.gridx = 1; // Centered horizontally
        gbc.gridy = 3;
        gbc.insets.top = 20; // Add space at the top
        contentPane.add(btnCancel, gbc);

        // Back to Main Menu button
        JButton btnBackToMainMenu = createStyledButton("Back to Main Menu");
        btnBackToMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu mainMenu = new Menu();
                mainMenu.setVisible(true);
                dispose();
            }
        });

        gbc.gridx = 1; // Centered horizontally
        gbc.gridy = 4;
        gbc.insets.top = 20; // Add space at the top
        contentPane.add(btnBackToMainMenu, gbc);

        setMinimumSize(new Dimension(800, 700)); // Set the minimum size of the frame
        setBounds(100, 100, 800, 700); // Set initial size
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(54, 100, 139)); // Set the background color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private String getCancellationInfo(String pnr) {
        return "Cancellation Successful for PNR: " + pnr;
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    private int showConfirm(String message, String title) {
        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
    }

    private int showCancellationConfirmation(String pnr) {
        String message = "Reservation with PNR " + pnr + " found. Are you sure you want to cancel this reservation?";
        return showConfirm(message, "Confirmation");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CancellationView frame = new CancellationView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
