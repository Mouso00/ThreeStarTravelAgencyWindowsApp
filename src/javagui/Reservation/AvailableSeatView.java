package javagui.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvailableSeatView extends JFrame {

    private Map<String, Integer> seatQuantities;
    private Map<String, List<JCheckBox>> seatCheckBoxes;

    public AvailableSeatView() {
        initializeUI();
       
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Available Seats");

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 100, 139));
        panel.setBounds(0, 0, 800, 600);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Available Seats");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(330, 30, 200, 40);
        panel.add(titleLabel);

        seatCheckBoxes = new HashMap<>();
        int x = 100;
        int y = 100;

        for (String seatType : seatQuantities.keySet()) {
            JLabel typeLabel = new JLabel(seatType + ":");
            typeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            typeLabel.setForeground(Color.WHITE);
            typeLabel.setBounds(x - 40, y - 30, 100, 30);
            panel.add(typeLabel);

            seatCheckBoxes.put(seatType, new ArrayList<>());

            for (int i = 1; i <= seatQuantities.get(seatType); i++) {
                JCheckBox seatCheckBox = new JCheckBox(seatType.charAt(0) + String.valueOf(i));
                seatCheckBox.setBounds(x, y, 80, 20);
                seatCheckBox.setEnabled(true);
                seatCheckBoxes.get(seatType).add(seatCheckBox);
                panel.add(seatCheckBox);

                y += 30;

                if (i % 5 == 0) {
                    x += 150;
                    y = 100;
                }
            }

            x += 200;
            y = 100;
        }

        int yOffset = 400;

        JButton payButton = new JButton("Pay");
        payButton.setForeground(Color.WHITE);
        payButton.setBackground(new Color(54, 100, 139));
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPayView();
                dispose();
            }
        });
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setBounds(350, yOffset, 100, 40);
        panel.add(payButton);

        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 800, 600);
    }

   

    private void openPayView() {
        PayView payView = new PayView();
        payView.setVisible(true);
    }

    
}
