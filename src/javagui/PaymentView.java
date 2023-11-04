package javagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentView extends JFrame {
    public PaymentView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Payment");

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

        JCheckBox trainRecord1Checkbox = new JCheckBox();
        trainRecord1Checkbox.setBounds(50, 100, 30, 30);
        panel.add(trainRecord1Checkbox);

        JLabel trainRecord1Label = new JLabel("Train Number: 12345  |  Seat Number: 12A  |  Time: 2023-11-10 10:00 AM  |  Price: $50");
        trainRecord1Label.setFont(new Font("Arial", Font.BOLD, 16));
        trainRecord1Label.setForeground(Color.WHITE);
        trainRecord1Label.setBounds(80, 100, 700, 30);
        panel.add(trainRecord1Label);

        JCheckBox trainRecord2Checkbox = new JCheckBox();
        trainRecord2Checkbox.setBounds(50, 150, 30, 30);
        panel.add(trainRecord2Checkbox);

        JLabel trainRecord2Label = new JLabel("Train Number: 54321  |  Seat Number: 7B  |  Time: 2023-11-11 2:30 PM  |  Price: $60");
        trainRecord2Label.setFont(new Font("Arial", Font.BOLD, 16));
        trainRecord2Label.setForeground(Color.WHITE);
        trainRecord2Label.setBounds(80, 150, 700, 30);
        panel.add(trainRecord2Label);

        JButton payButton = new JButton("Pay");
        payButton.setForeground(Color.WHITE);
        payButton.setBackground(new Color(54, 100, 139));
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the payment logic here
                String selectedRecords = "";
                if (trainRecord1Checkbox.isSelected()) {
                    selectedRecords += "Train Number: 12345  |  Seat Number: 12A  |  Time: 2023-11-10 10:00 AM  |  Price: $50\n";
                }
                if (trainRecord2Checkbox.isSelected()) {
                    selectedRecords += "Train Number: 54321  |  Seat Number: 7B  |  Time: 2023-11-11 2:30 PM  |  Price: $60\n";
                }

                PnrView pnrView = new PnrView("ABC12345", selectedRecords);
                pnrView.setVisible(true);
                dispose(); // Close the current PaymentView frame
            }
        });
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setBounds(350, 200, 100, 40);
        panel.add(payButton);

        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 800, 600);
    }
}
