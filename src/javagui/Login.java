package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Login extends JFrame {

    private JTextField user;
    private JPasswordField pass;

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);

        JPanel contentPane = new JPanel(null);
        setContentPane(contentPane);

        // Left panel for decorative image
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(255, 255, 255));
        leftPanel.setBounds(0, 0, 250, 600);
        contentPane.add(leftPanel);

        try {
            // Load the background image and resize it
            BufferedImage originalImage = ImageIO.read(new File("img/loginbackground.jpg"));
            int newWidth = 250;
            int newHeight = 600;
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon backgroundImage = new ImageIcon(scaledImage);

            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setBounds(0, 0, 250, 600);
            leftPanel.add(backgroundLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(250, 0, 550, 600);
        contentPane.add(panel);

        JLabel titleLabel = new JLabel("Welcome to 3* Travel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(150, 30, 300, 40);
        panel.add(titleLabel);

        user = new JTextField();
        user.setBounds(230, 240, 180, 30);
        panel.add(user);

        pass = new JPasswordField();
        pass.setBounds(230, 280, 180, 30);
        panel.add(pass);

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(150, 240, 80, 30);
        panel.add(userLabel);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(150, 280, 80, 30);
        panel.add(passLabel);

        JButton loginButton = new JButton("Login");
        customizeButton(loginButton);
        loginButton.addActionListener(e -> openMenuView());
        loginButton.setBounds(230, 340, 180, 40);
        panel.add(loginButton);

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

    private void openMenuView() {
        Menu menuView = new Menu();
        menuView.setVisible(true);
        dispose();
    }
}
