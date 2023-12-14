package javagui.Authentication;

import com.toedter.calendar.JDateChooser;

import dbConnection.DatabaseConnection;
import javagui.Dashboard.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterView extends JFrame {
    private JTextField fullNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> genderComboBox;
    private JDateChooser dateChooser;
    private JButton registerButton;
    private JButton backButton;

    public RegisterView() {
    	
    	  initializeUI();
    }
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register");
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        JPanel panel = new JPanel(null);  // Using null layout for custom positioning
        panel.setBackground(new Color(54, 100, 139));
        contentPane.add(panel, BorderLayout.CENTER);

        fullNameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        dateChooser = new JDateChooser();

        // Set the font for labels and text fields
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Color labelColor = Color.WHITE;

        int x = 150;
        int y = 50;
        int labelWidth = 100;
        int textFieldWidth = 180;
        int labelTextFieldGap = 10;

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(labelFont);
        fullNameLabel.setForeground(labelColor);
        fullNameLabel.setBounds(x, y, labelWidth, 30);
        panel.add(fullNameLabel);

        fullNameField.setBounds(x + labelWidth + labelTextFieldGap, y, textFieldWidth, 30);
        panel.add(fullNameField);

        y += 50;

        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(labelColor);
        emailLabel.setBounds(x, y, labelWidth, 30);
        panel.add(emailLabel);

        emailField.setBounds(x + labelWidth + labelTextFieldGap, y, textFieldWidth, 30);
        panel.add(emailField);

        y += 50;

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(labelColor);
        passwordLabel.setBounds(x, y, labelWidth, 30);
        panel.add(passwordLabel);

        passwordField.setBounds(x + labelWidth + labelTextFieldGap, y, textFieldWidth, 30);
        panel.add(passwordField);

        y += 50;

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordLabel.setForeground(labelColor);
        confirmPasswordLabel.setBounds(x, y, labelWidth, 30);
        panel.add(confirmPasswordLabel);

        confirmPasswordField.setBounds(x + labelWidth + labelTextFieldGap, y, textFieldWidth, 30);
        panel.add(confirmPasswordField);

        y += 50;

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        genderLabel.setForeground(labelColor);
        genderLabel.setBounds(x, y, labelWidth, 30);
        panel.add(genderLabel);

        genderComboBox.setBounds(x + labelWidth + labelTextFieldGap, y, textFieldWidth, 30);
        panel.add(genderComboBox);

        y += 50;

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(labelFont);
        dobLabel.setForeground(labelColor);
        dobLabel.setBounds(x, y, labelWidth, 30);
        panel.add(dobLabel);

        dateChooser.setBounds(x + labelWidth + labelTextFieldGap, y, textFieldWidth, 30);
        panel.add(dateChooser);

        y += 50;

        registerButton = new JButton("Register");
        customizeButton(registerButton);
        registerButton.addActionListener(e -> openWelcomeView());
        registerButton.setBounds(270, 400, 150, 40);
        panel.add(registerButton);

        backButton = new JButton("Back");
        customizeButton(backButton);
        backButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the Menu
                Login login = new Login();
                login.setVisible(true);
                dispose(); // Close the current MakeReservationView frame
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(50, 500, 150, 40);
        panel.add(backButton);
        pack();
        setLocationRelativeTo(null);
    }

    private void customizeButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(54, 100, 139));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void openWelcomeView() {
        String fullName = fullNameField.getText();
        String emailAddress = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String selectedGender = (String) genderComboBox.getSelectedItem();
        java.util.Date dobUtil = dateChooser.getDate();

        // Perform validation
        if (fullName.isEmpty() || emailAddress.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || dobUtil == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields, including Date of Birth.");
            return;
        }

        if (!isValidFullName(fullName)) {
            JOptionPane.showMessageDialog(null, "Full Name should only contain letters and spaces, and should have at least 3 characters.");
            return;
        }

        if (!emailAddress.contains("@")) {
            JOptionPane.showMessageDialog(null, "Invalid email address. Please include '@'.");
            return;
        }
        if (!isValidEmailAddress(emailAddress)) {
            JOptionPane.showMessageDialog(null, "Invalid email address. Please enter a valid email address.");
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(null, "Password must be at least 4 characters long.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
            return;
        }

        // Check if the user already exists
        if (userExists(emailAddress)) {
            JOptionPane.showMessageDialog(null, "User with the provided email address already exists.");
            return;
        }

        // Database insertion logic
        try (Connection connection = DatabaseConnection.getConnection()) {
            String insertQuery = "INSERT INTO users (username, password, role, created_at, full_name, email_address, gender, date_of_birth) VALUES (?, ?, ?, NOW(), ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, emailAddress);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, "user");  // Set the default role to "user"
                preparedStatement.setString(4, fullName);
                preparedStatement.setString(5, emailAddress);
                preparedStatement.setString(6, selectedGender);
                preparedStatement.setDate(7, new java.sql.Date(dobUtil.getTime()));

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    dispose();  // Close the RegisterView
                    // Open the WelcomeView (or any other desired view)
                    WelcomeView welcomeView = new WelcomeView();
                    welcomeView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Registration failed.");
        }
    }
    
    
    //Validations
    private boolean isValidEmailAddress(String email) {
        int count = 0;
        for (char c : email.toCharArray()) {
            if (c == '@') {
                count++;
                if (count > 1) {
                    return false; // More than one "@" found
                }
            }
        }
        return count == 1; // Exactly one "@" found
    }
   
    private boolean isValidFullName(String fullName) {
        return fullName.matches("^[a-zA-Z\\s]{3,}$");
    }
   
    private boolean userExists(String email) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM users WHERE email_address = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
