package javagui.Reservation;

import javax.swing.*;

import models.TrainRecord;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class SearchResultsView extends JFrame {

    private List<TrainRecord> trainRecords;
    private String selectedFrom;
    private String selectedTo;
    private String selectedDate;
    private String selectedTime;
    private String selectedClass;
   
    private JComboBox<String> seatComboBox;

    public SearchResultsView(String from, String to, String date, String time, String travelClass) {
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
                    // Perform any additional logic with the selected seat if needed
                    // For example, you can pass it to the ConfirmAndProceedView

                    openConfirmAndProceedView(selectedSeat);
                    dispose();
                } else {
                    // Handle the case where no seat is selected (show a message, etc.)
                    JOptionPane.showMessageDialog(SearchResultsView.this, "Please select a seat.", "Seat Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        confirmSeatButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmSeatButton.setBounds(350, yOffset, 150, 40);
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
        backButton.setBounds(50, yOffset + 50, 100, 40);
        panel.add(backButton);

        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 800, 600);
    }

    private void openSearchTrainView() {
        SearchTrainView searchTrainView = new SearchTrainView();
        searchTrainView.setVisible(true);
    }

    private void openConfirmAndProceedView(String selectedSeat) {
        ConfirmAndProceedView confirmAndProceedView = new ConfirmAndProceedView(selectedFrom, selectedTo, selectedDate, selectedTime, selectedClass, selectedSeat);
        confirmAndProceedView.setVisible(true);
    }



    private String[] getSeatOptions() {
        // Replace this with your own logic to get seat options
        // For example, you can retrieve seat information from a database
        return new String[]{"Seat 1", "Seat 2", "Seat 3", "Seat 4"};
    }
}
