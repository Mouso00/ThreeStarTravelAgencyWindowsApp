package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JFrame {
    public WelcomeView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome");
     // Set a fixed size for the WelcomeView
        setSize(800, 600);
        setResizable(false); // Prevent resizing


        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Welcome to 3* Travel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(150, 30, 300, 40);
        panel.add(titleLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(54, 100, 139));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the action when the "Login" button is clicked
                Login loginView = new Login();
                loginView.setVisible(true);
                dispose(); // Close the current WelcomeView frame
            }
        });
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBounds(230, 240, 180, 40);
        panel.add(loginButton);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setBackground(new Color(54, 100, 139));
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterView registerView = new RegisterView();
                registerView.setVisible(true);
                dispose();
            }
        });
        createAccountButton.setFont(new Font("Arial", Font.BOLD, 16));
        createAccountButton.setBounds(230, 300, 180, 40);
        panel.add(createAccountButton);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WelcomeView welcomeView = new WelcomeView();
                    welcomeView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
