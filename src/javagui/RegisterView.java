package javagui;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        registerButton.setBounds(x + labelWidth, y, textFieldWidth, 40);
        panel.add(registerButton);

        backButton = new JButton("Back");
        customizeButton(backButton);
        backButton.addActionListener(e -> openWelcomeView());
        backButton.setBounds(x, y, labelWidth, 40);
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
        // Access the data from the fields
        String fullName = fullNameField.getText();
        String emailAddress = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String selectedGender = (String) genderComboBox.getSelectedItem();
        // You can similarly access other fields as needed.

        // Perform validation and registration logic here

        WelcomeView welcomeView = new WelcomeView();
        welcomeView.setVisible(true);
        dispose();
    }
}
