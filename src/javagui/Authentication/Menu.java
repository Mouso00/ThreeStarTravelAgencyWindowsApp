package javagui.Authentication;

import javax.swing.*;

import javagui.PnrEnquiryView;
import javagui.Reservation.CancellationView;
import javagui.Reservation.SearchTrainView;

import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
	private final String userId;


    public Menu(String userId) {
    	this.userId = userId;
        JOptionPane.showMessageDialog(null, userId);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Online Train Reservation");
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);

        initializeUI();

        pack();
        setLocationRelativeTo(null);
    }
    private void initializeUI() {
        // Create the main content pane
        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(54, 100, 139));
        JLabel lblTitle = new JLabel("Online Train Reservation");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        contentPane.add(headerPanel, BorderLayout.NORTH);

        // Middle Panel
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBackground(new Color(240, 240, 240));

        // Create a sub-panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));

        createImageAndButton(buttonPanel, "img/reservation.png", "Make a Reservation", 150, 150, e -> openSearchTrainView(userId));
        createImageAndButton(buttonPanel, "img/cancellation.png", "Cancel a Reservation", 150, 150, e -> openCancellationView());
        createImageAndButton(buttonPanel, "img/pnrenquiry.png", "PNR Enquiry", 150, 150, e -> openPnrEnquiryView());

        // Add the sub-panel for buttons to the middle panel
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 0;
        middlePanel.add(buttonPanel, buttonConstraints);

        // Create an image label
        ImageIcon imageIcon = new ImageIcon("img/loginbackground.jpg");
        JLabel imageLabel = new JLabel(imageIcon);

        // Add the image label to the middle panel and make it fill the available space
        GridBagConstraints imageConstraints = new GridBagConstraints();
        imageConstraints.gridx = 0;
        imageConstraints.gridy = 1;
        imageConstraints.fill = GridBagConstraints.BOTH;
        imageConstraints.weightx = 1.0;
        imageConstraints.weighty = 1.0;
        middlePanel.add(imageLabel, imageConstraints);

        contentPane.add(middlePanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        JButton btnExit = createStyledButton("Exit");
        btnExit.addActionListener(e -> System.exit(0));
        footerPanel.add(btnExit);
        contentPane.add(footerPanel, BorderLayout.SOUTH);
    }

    private void createImageAndButton(JPanel panel, String imagePath, String buttonText, int width, int height, ActionListener actionListener) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        JButton button = new JButton(buttonText);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(54, 100, 139));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setIcon(icon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.addActionListener(actionListener);
        panel.add(button);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(54, 100, 139));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void openSearchTrainView(String userId) {
        SearchTrainView searchTrainView = new SearchTrainView(userId);
        searchTrainView.setVisible(true);
        dispose();
    }

    private void openCancellationView() {
        CancellationView cancellationView = new CancellationView(userId);
        cancellationView.setVisible(true);
        dispose();
    }

    private void openPnrEnquiryView() {
        PnrEnquiryView pnrEnquiryView = new PnrEnquiryView(userId);
        pnrEnquiryView.setVisible(true);
        dispose();
    }
}
