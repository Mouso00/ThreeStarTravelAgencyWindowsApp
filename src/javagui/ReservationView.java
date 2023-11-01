package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationView extends JFrame {

    private JTextField nameField;
    private JTextField contactField;
    private JTextField dateField;
    private JTextField classField;
    private JTextField fromField;
    private JTextField toField;

    public ReservationView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Reservation");

        // Set the size and location similar to the Login and Menu views
        setBounds(100, 100, 800, 600); // Match the dimensions and position

        // Create a main panel to hold both the Reservation form and Search Results
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel for the Reservation form on the left
        JPanel reservationPanel = new JPanel();
        reservationPanel.setLayout(new GridLayout(7, 2, 10, 10));
        reservationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        reservationPanel.setBackground(new Color(240, 240, 240));

        createFieldWithLabel(reservationPanel, "Name:", nameField = new JTextField(20));
        createFieldWithLabel(reservationPanel, "Contact:", contactField = new JTextField(20));
        createFieldWithLabel(reservationPanel, "Date:", dateField = new JTextField(20));
        createFieldWithLabel(reservationPanel, "Class:", classField = new JTextField(20));
        createFieldWithLabel(reservationPanel, "From:", fromField = new JTextField(20));
        createFieldWithLabel(reservationPanel, "To:", toField = new JTextField(20));

        // Search button with a stylish look
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(54, 100, 139));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle search logic here
                // Show search results in a dialog
                showSearchResultsDialog();
            }
        });

        // Add components to the main panel
        mainPanel.add(new JLabel("Reservation"), BorderLayout.NORTH);
        mainPanel.add(reservationPanel, BorderLayout.WEST);
        mainPanel.add(searchButton, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void createFieldWithLabel(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBackground(new Color(220, 220, 220));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.add(label);
        panel.add(textField);
    }

    private void showSearchResultsDialog() {
        // You can implement the search logic here and populate the search results
        // For this example, we use placeholder search results
        String[] results = {"Train ABC", "Train XYZ", "Train PQR", "Train 123", "Train 456", "Train 789"};

        // Create a panel to display search results
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        resultsPanel.setBackground(new Color(240, 240, 240));

        // Create labels for the search results
        JLabel resultsLabel = new JLabel("Search Results");
        resultsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultsPanel.add(resultsLabel);

        // Add search results to the panel
        for (String result : results) {
            createResultLabel(resultsPanel, result);
        }

        // Create a dialog to display the search results
        JDialog searchResultsDialog = new JDialog(this, "Search Results", true);
        searchResultsDialog.setContentPane(resultsPanel);
        searchResultsDialog.pack();
        searchResultsDialog.setLocationRelativeTo(this); // Center the dialog on the parent frame
        searchResultsDialog.setVisible(true);
    }

    private void createResultLabel(JPanel panel, String resultText) {
        JLabel resultLabel = new JLabel(resultText);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(resultLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReservationView frame = new ReservationView();
            frame.setVisible(true);
        });
    }
}
