package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchTrainView extends JFrame {
    public SearchTrainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Search Train");

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Search for Trains");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(300, 30, 200, 40);
        panel.add(titleLabel);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setBounds(100, 100, 80, 30);
        panel.add(fromLabel);

        // Dropdown List for "From"
        String[] fromOptions = {"City A", "City B", "City C"};
        JComboBox<String> fromDropdown = new JComboBox<>(fromOptions);
        fromDropdown.setBounds(200, 100, 150, 30);
        panel.add(fromDropdown);

        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.BOLD, 16));
        toLabel.setForeground(Color.WHITE);
        toLabel.setBounds(100, 150, 80, 30);
        panel.add(toLabel);

        // Dropdown List for "To"
        String[] toOptions = {"City X", "City Y", "City Z"};
        JComboBox<String> toDropdown = new JComboBox<>(toOptions);
        toDropdown.setBounds(200, 150, 150, 30);
        panel.add(toDropdown);

        JLabel dateTimeLabel = new JLabel("Date and Time:");
        dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dateTimeLabel.setForeground(Color.WHITE);
        dateTimeLabel.setBounds(100, 200, 150, 30);
        panel.add(dateTimeLabel);

        // Dropdown List for "Date and Time"
        String[] dateTimeOptions = {"2023-11-10 10:00 AM", "2023-11-11 2:30 PM", "2023-11-12 8:00 AM"};
        JComboBox<String> dateTimeDropdown = new JComboBox<>(dateTimeOptions);
        dateTimeDropdown.setBounds(250, 200, 200, 30);
        panel.add(dateTimeDropdown);

        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(new Font("Arial", Font.BOLD, 16));
        classLabel.setForeground(Color.WHITE);
        classLabel.setBounds(100, 250, 80, 30);
        panel.add(classLabel);

        // Dropdown List for "Class"
        String[] classOptions = {"First Class", "Second Class", "Economy"};
        JComboBox<String> classDropdown = new JComboBox<>(classOptions);
        classDropdown.setBounds(200, 250, 150, 30);
        panel.add(classDropdown);

        JButton reservationButton = new JButton("Reserve");
        reservationButton.setForeground(Color.WHITE);
        reservationButton.setBackground(new Color(54, 100, 139));
        reservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the reservation logic here (if needed)

                // Navigate to the PaymentView
                PaymentView paymentView = new PaymentView();
                paymentView.setVisible(true);
                dispose(); // Close the current SearchTrainView frame
            }
        });
        reservationButton.setFont(new Font("Arial", Font.BOLD, 16));
        reservationButton.setBounds(300, 300, 150, 40);
        panel.add(reservationButton);

        setMinimumSize(new Dimension(800, 600)); // Set the minimum size of the frame
        setBounds(100, 100, 800, 600); // Set initial size
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SearchTrainView searchTrainView = new SearchTrainView();
                    searchTrainView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
