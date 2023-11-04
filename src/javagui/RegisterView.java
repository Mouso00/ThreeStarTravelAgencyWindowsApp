package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    public RegisterView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register");

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Create Your Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 30, 300, 40);
        panel.add(titleLabel);

        // Labels and Text Fields
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fullNameLabel.setForeground(Color.WHITE);
        fullNameLabel.setBounds(100, 100, 120, 30);
        panel.add(fullNameLabel);

        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(250, 100, 300, 30);
        panel.add(fullNameField);

        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(100, 150, 150, 30);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(250, 150, 300, 30);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(100, 200, 100, 30);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(250, 200, 300, 30);
        panel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setBounds(100, 250, 160, 30);
        panel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(250, 250, 300, 30);
        panel.add(confirmPasswordField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(new Font("Arial", Font.BOLD, 16));
        phoneNumberLabel.setForeground(Color.WHITE);
        phoneNumberLabel.setBounds(100, 300, 150, 30);
        panel.add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(250, 300, 300, 30);
        panel.add(phoneNumberField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dobLabel.setForeground(Color.WHITE);
        dobLabel.setBounds(100, 350, 120, 30);
        panel.add(dobLabel);

        JTextField dobField = new JTextField();
        dobField.setBounds(250, 350, 300, 30);
        panel.add(dobField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 16));
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setBounds(100, 400, 80, 30);
        panel.add(genderLabel);

        // Gender ComboBox
        String[] genders = {"Male", "Female", "Other"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(250, 400, 150, 30);
        panel.add(genderComboBox);

        JButton registerButton = new JButton("Register");
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(54, 100, 139));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the registration logic here
            }
        });
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBounds(250, 450, 150, 40);
        panel.add(registerButton);

        setMinimumSize(new Dimension(800, 600)); // Set the minimum size of the frame
        setBounds(100, 100, 800, 600); // Set initial size
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterView registerView = new RegisterView();
                    registerView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
