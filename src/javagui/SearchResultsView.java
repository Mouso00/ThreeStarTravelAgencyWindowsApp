package javagui;

import javax.swing.*;

import models.TrainRecord;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchResultsView extends JFrame {

    private List<TrainRecord> trainRecords; // Add this variable

    public SearchResultsView(List<TrainRecord> trainRecords) {
        this.trainRecords = trainRecords;
        initializeUI();
    }

    public void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Train Records");

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Train Records");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(330, 30, 200, 40);
        panel.add(titleLabel);

        // Iterate over trainRecords and update UI components
        int yOffset = 100;
        for (TrainRecord record : trainRecords) {
            JCheckBox trainCheckbox = new JCheckBox();
            trainCheckbox.setBounds(50, yOffset, 30, 30);
            panel.add(trainCheckbox);

            JLabel trainLabel = new JLabel("Train Number: " + record.getTrainNumber() +
                    "  |  Seat Number: " + record.getSeatNumber() +
                    "  |  Time: " + record.getTime() +
                    "  |  Price: $" + record.getPrice());
            trainLabel.setFont(new Font("Arial", Font.BOLD, 16));
            trainLabel.setForeground(Color.WHITE);
            trainLabel.setBounds(80, yOffset, 700, 30);
            panel.add(trainLabel);

            yOffset += 50; // Adjust the vertical position for the next train record
        }

        JButton payButton = new JButton("Pay");
        payButton.setForeground(Color.WHITE);
        payButton.setBackground(new Color(54, 100, 139));
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the payment logic here
                String selectedRecords = "";
                // Iterate over trainRecords to build the selected records string
                for (int i = 0; i < trainRecords.size(); i++) {
                    JCheckBox checkBox = (JCheckBox) panel.getComponent(i * 2);
                    if (checkBox.isSelected()) {
                        selectedRecords += trainRecords.get(i).toString() + "\n";
                    }
                }

               
                dispose(); // Close the current SearchResultsView frame
            }
        });
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setBounds(350, yOffset, 100, 40);
        panel.add(payButton);

        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 800, 600);
    }
}
