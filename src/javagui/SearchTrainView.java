package javagui;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchTrainView extends JFrame {

    public SearchTrainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Search Train");
        setPreferredSize(new Dimension(800, 600));
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

        JLabel titleLabel = new JLabel("Search for Trains");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(300, 30, 200, 40);
        panel.add(titleLabel);

        int labelX = 100;
        int labelY = 100;
        int comboBoxX = 250;
        int comboBoxY = 100;
        int labelComboBoxGap = 50;
        int comboBoxWidth = 150;
        int comboBoxHeight = 30;

        createLabelAndComboBox(panel, "From:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, new String[]{"City A", "City B", "City C"});
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        createLabelAndComboBox(panel, "To:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, new String[]{"City X", "City Y", "City Z"});
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        // Add a JDateChooser for date selection
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(comboBoxX, comboBoxY, 150, 30);
        panel.add(dateChooser);

        // Add a separate field for date and time
        createLabelAndComboBox(panel, "Date of Journey:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, new String[]{"2023-11-10"});
        labelY += labelComboBoxGap;
        comboBoxX += 20;
        comboBoxY += labelComboBoxGap;

        createLabelAndComboBox(panel, "Time:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, new String[]{
            "12:00 AM", "1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM",
            "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM",
            "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM",
            "9:00 PM", "10:00 PM", "11:00 PM"
        });
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        createLabelAndComboBox(panel, "Class:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, new String[]{"First Class", "Second Class", "Economy"});

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
        reservationButton.setBounds(300, 400, 150, 40);
        panel.add(reservationButton);

        pack();
        setLocationRelativeTo(null);
    }

    private void createLabelAndComboBox(JPanel panel, String labelText, int labelX, int labelY, int comboBoxX, int comboBoxY, int comboBoxWidth, int comboBoxHeight, String[] options) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setBounds(labelX, labelY, 120, 30);
        panel.add(label);

        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight);
        panel.add(comboBox);
    }
}
