package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Online Train Reservation");

        // Set a background color for the main panel
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background color

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(54, 100, 139));
        JLabel lblTitle = new JLabel("Online Train Reservation");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Middle Panel
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBackground(new Color(240, 240, 240)); // Light gray background color

        // Create a sub-panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        createImageAndButton(buttonPanel, "img/reservation.png", "Make a Reservation", 150, 150, e -> {
            SearchTrainView searchTrainView = new SearchTrainView();
            searchTrainView.setVisible(true);
            dispose();
        });
        createImageAndButton(buttonPanel, "img/cancellation.png", "Cancel a Reservation", 150, 150, e -> {
            CancellationView cancellationView = new CancellationView();
            cancellationView.setVisible(true);
            dispose();
        });
        createImageAndButton(buttonPanel, "img/pnrenquiry.png", "PNR Enquiry", 150, 150, e -> {
            PnrEnquiryView pnrEnquiryView = new PnrEnquiryView();
            pnrEnquiryView.setVisible(true);
            dispose();
        });

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
        imageConstraints.fill = GridBagConstraints.BOTH; // Fill both horizontal and vertical space
        imageConstraints.weightx = 1.0;
        imageConstraints.weighty = 1.0;
        middlePanel.add(imageLabel, imageConstraints);

        getContentPane().add(middlePanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        JButton btnExit = createStyledButton("Exit");
        btnExit.addActionListener(e -> System.exit(0));
        footerPanel.add(btnExit);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);

        // Set the size and location similar to the Login view
        setBounds(100, 100, 800, 600); // Match the dimensions and position

        setMinimumSize(new Dimension(800, 600)); // Set the minimum size of the frame
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu frame = new Menu();
            frame.setVisible(true);
        });
    }
}
