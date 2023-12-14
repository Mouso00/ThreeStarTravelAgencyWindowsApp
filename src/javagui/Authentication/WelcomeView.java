package javagui.Authentication;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JFrame {

    public WelcomeView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome");
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
        initializeUI();
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeUI() {
        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        
        // Create a panel to hold the image on the left
        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);
        contentPane.add(imagePanel, BorderLayout.WEST);
        
        // Load and scale the image
        ImageIcon originalImageIcon = new ImageIcon("img/welcomeImage.png");
        int newWidth = 340;
        int newHeight = 600;
        Image scaledImage = originalImageIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imagePanel.add(imageLabel);

        // Create a panel for the title and buttons on the right
        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add vertical glue to center the title and buttons
        panel.add(Box.createVerticalGlue());

        JLabel titleLabel = new JLabel("Welcome to 3* Travel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        //separate the title and buttons
        panel.add(Box.createRigidArea(new Dimension(0, 80)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        panel.add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());

        JButton loginButton = new JButton("Login");
        customizeButton(loginButton);
        loginButton.addActionListener(e -> openLoginView());
        buttonPanel.add(loginButton);

        JButton createAccountButton = new JButton("Create Account");
        customizeButton(createAccountButton);
        createAccountButton.addActionListener(e -> openRegisterView());
        buttonPanel.add(createAccountButton);

        // center the buttons
        panel.add(Box.createVerticalGlue());
    }

    private void customizeButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(54, 100, 139));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(180, 40));
        button.setFocusPainted(false); // Remove the button border
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover
    }

    private void openLoginView() {
        Login loginView = new Login();
        loginView.setVisible(true);
        dispose();
    }

    private void openRegisterView() {
        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WelcomeView welcomeView = new WelcomeView();
                welcomeView.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
