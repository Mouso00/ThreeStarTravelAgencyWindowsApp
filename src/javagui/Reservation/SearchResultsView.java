package javagui.Reservation;

import javax.swing.*;

import models.TrainRecord;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchResultsView extends JFrame {

    private List<TrainRecord> trainRecords;

    public SearchResultsView() {
       
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

        int yOffset = 100;
        
        JButton payButton = new JButton("Pay");
        payButton.setForeground(Color.WHITE);
        payButton.setBackground(new Color(54, 100, 139));
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                dispose();
            }
        });
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setBounds(350, yOffset, 100, 40);
        panel.add(payButton);

        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 800, 600);
    }
}
