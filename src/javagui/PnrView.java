package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnrView extends JFrame {
    private final String pnrCode;
    private final String selectedRecords;

    public PnrView(String pnrCode, String selectedRecords) {
        this.pnrCode = pnrCode;
        this.selectedRecords = selectedRecords;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PNR Details");

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("PNR Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(330, 30, 200, 40);
        panel.add(titleLabel);

        JLabel pnrLabel = new JLabel("PNR Code: " + pnrCode);
        pnrLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pnrLabel.setForeground(Color.WHITE);
        pnrLabel.setBounds(50, 100, 700, 30);
        panel.add(pnrLabel);

        JLabel selectedRecordsLabel = new JLabel("Selected Train Records:");
        selectedRecordsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectedRecordsLabel.setForeground(Color.WHITE);
        selectedRecordsLabel.setBounds(50, 150, 200, 30);
        panel.add(selectedRecordsLabel);

        JLabel recordsLabel = new JLabel(selectedRecords);
        recordsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        recordsLabel.setForeground(Color.WHITE);
        recordsLabel.setBounds(50, 190, 700, 60);
        panel.add(recordsLabel);

        JButton backButton = new JButton("Back to Menu");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(54, 100, 139));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the Menu
                Menu menuView = new Menu();
                menuView.setVisible(true);
                dispose(); // Close the current PnrView frame
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(350, 300, 150, 40);
        panel.add(backButton);

        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 800, 600);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String pnrCode = "ABC12345"; // Replace with your PNR code
                    String selectedRecords = "Train Number: 12345  |  Seat Number: 12A  |  Time: 2023-11-10 10:00 AM  |  Price: $50\nTrain Number: 54321  |  Seat Number: 7B  |  Time: 2023-11-11 2:30 PM  |  Price: $60"; // Replace with selected records
                    PnrView pnrView = new PnrView(pnrCode, selectedRecords);
                    pnrView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
