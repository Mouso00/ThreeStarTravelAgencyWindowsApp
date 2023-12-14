package javagui.Reservation;

import javax.swing.*;

import javagui.Dashboard.MakeReservationView;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TrainRecordsView extends JFrame {

	private final String userId;

    private String selectedFrom;
    private String selectedTo;
    private String selectedDate;
    private String selectedTime;
    private String selectedClass;
   
    private JComboBox<String> seatComboBox;

    public TrainRecordsView(String userId,String from, String to, String date, String time, String travelClass) {
        this.userId = userId;
    	this.selectedFrom = from;
        this.selectedTo = to;
        this.selectedDate = date;
        this.selectedTime = time;
        this.selectedClass = travelClass;

        initializeUI();
    }

    public void initializeUI() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Train Records");
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

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

        JLabel selectedOptionsLabel = new JLabel("Selected Options:");
        selectedOptionsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectedOptionsLabel.setForeground(Color.WHITE);
        selectedOptionsLabel.setBounds(50, 100, 150, 30);
        panel.add(selectedOptionsLabel);

        JLabel fromLabel = new JLabel("From: " + selectedFrom);
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setBounds(50, 140, 400, 20);
        panel.add(fromLabel);

        JLabel toLabel = new JLabel("To: " + selectedTo);
        toLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        toLabel.setForeground(Color.WHITE);
        toLabel.setBounds(50, 170, 400, 20);
        panel.add(toLabel);

        JLabel dateLabel = new JLabel("Date: " + selectedDate);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setBounds(50, 200, 400, 20);
        panel.add(dateLabel);

        JLabel timeLabel = new JLabel("Time: " + selectedTime);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(50, 230, 400, 20);
        panel.add(timeLabel);

        JLabel classLabel = new JLabel("Class: " + selectedClass);
        classLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        classLabel.setForeground(Color.WHITE);
        classLabel.setBounds(50, 260, 400, 20);
        panel.add(classLabel);

        int yOffset = 300;

        // Create and populate the seat selection combo box
        seatComboBox = new JComboBox<>(getSeatOptions());
        seatComboBox.setBounds(50, yOffset, 150, 30);
        panel.add(seatComboBox);

        JButton confirmSeatButton = new JButton("Confirm Seat");
        confirmSeatButton.setForeground(Color.WHITE);
        confirmSeatButton.setBackground(new Color(54, 100, 139));
        confirmSeatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve the selected seat
                String selectedSeat = (String) seatComboBox.getSelectedItem();

                // Check if a seat is selected
                if (selectedSeat != null && !selectedSeat.isEmpty()) {
               

                    openConfirmAndProceedView(selectedSeat);
                    dispose();
                } else {
                
                    JOptionPane.showMessageDialog(TrainRecordsView.this, "Please select a seat.", "Seat Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        confirmSeatButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmSeatButton.setBounds(330, 350, 150, 40);
        panel.add(confirmSeatButton);

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(54, 100, 139));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchTrainView();
                dispose();
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(50, 500, 150, 40);
        panel.add(backButton);

        
      
    }

    private void openSearchTrainView() {
        MakeReservationView searchTrainView = new MakeReservationView(userId);
        searchTrainView.setVisible(true);
    }

    private void openConfirmAndProceedView(String selectedSeat) {
        ConfirmAndProceedView confirmAndProceedView = new ConfirmAndProceedView(userId,selectedFrom, selectedTo, selectedDate, selectedTime, selectedClass, selectedSeat);
        confirmAndProceedView.setVisible(true);
    }



    private String[] getSeatOptions() {
        
    	return generateSeatOptions(25);
    }
    private String[] generateSeatOptions(int limit) {
        String[] seatOptions = new String[limit];
        
        for (int i = 1; i <= limit; i++) {
            seatOptions[i - 1] = String.valueOf(i);
        }
        
        return seatOptions;
    }
}
