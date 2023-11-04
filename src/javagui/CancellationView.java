package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancellationView extends JFrame {
    public CancellationView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cancellation Form");

        setSize(800, 600);
        setResizable(false);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Cancellation Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(300, 30, 200, 40);
        panel.add(titleLabel);

        JLabel pnrLabel = new JLabel("Enter PNR Number:");
        pnrLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pnrLabel.setForeground(Color.WHITE);
        pnrLabel.setBounds(250, 200, 150, 30);
        panel.add(pnrLabel);

        JTextField pnrField = new JTextField();
        pnrField.setBounds(400, 200, 150, 30);
        panel.add(pnrField);

        JButton submitButton = new JButton("Submit");
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(54, 100, 139));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pnr = pnrField.getText(); // Get the entered PNR number

                int confirmation = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to cancel this ticket?",
                        "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    // User confirmed the cancellation
                    // You can implement the cancellation logic here
                    String pnrInfo = fetchPnrInformation(pnr);

                    // Display the information in a dialog box
                    JOptionPane.showMessageDialog(null, pnrInfo, "Ticket Cancellation", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBounds(350, 250, 100, 40);
        panel.add(submitButton);

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(54, 100, 139));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the Menu
                Menu menuView = new Menu();
                menuView.setVisible(true);
                dispose(); // Close the current CancellationView frame
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(50, 500, 100, 40);
        panel.add(backButton);
    }

    // Simulate fetching PNR information (Replace with your data source logic)
    private String fetchPnrInformation(String pnr) {
        // Replace this with your actual data retrieval logic based on the PNR number
        // Return the information related to the PNR
        return "PNR: " + pnr + "\nTrain Number: 12345\nSeat Number: 12A\nTime: 2023-11-10 10:00 AM\nPrice: $50";
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CancellationView cancellationView = new CancellationView();
                    cancellationView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
