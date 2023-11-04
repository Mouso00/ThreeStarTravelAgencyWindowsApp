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

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    Login window = new Login();
//                    window.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

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

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(250, 0, 550, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Welcome to 3* Travel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(150, 30, 300, 40);
        panel.add(titleLabel);

        user = new JTextField();
        user.setBounds(230, 240, 180, 30);
        panel.add(user);
        user.setColumns(10);

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
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(54, 100, 139));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle login logic here
                // If login is successful, navigate to the main menu
                Menu menuView = new Menu();
                menuView.setVisible(true);
                dispose(); // Close the current Login frame
            }
        });
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBounds(230, 340, 180, 40);
        panel.add(loginButton);

        setMinimumSize(new Dimension(800, 600)); // Set the minimum size of the frame
        setBounds(100, 100, 800, 600); // Set initial size
    }
}
