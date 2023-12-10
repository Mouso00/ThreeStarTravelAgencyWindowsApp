package javagui.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayView extends JFrame {

	private final String userId;
    private String selectedFrom;
    private String selectedTo;
    private String selectedDate;
    private String selectedTime;
    private String selectedClass;
    private String selectedSeat;
    private int generatedPnr;

    public PayView(String userId,String from, String to, String date, String time, String travelClass, String selectedSeat, int generatedPnr) {
        this.userId = userId;
    	this.selectedFrom = from;
        this.selectedTo = to;
        this.selectedDate = date;
        this.selectedTime = time;
        this.selectedClass = travelClass;
        this.selectedSeat = selectedSeat;
        this.generatedPnr =generatedPnr;
        initializeUI();
    }

    private void initializeUI() {
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

        JLabel titleLabel = new JLabel("Payment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(330, 30, 200, 40);
        panel.add(titleLabel);

        // Add JLabels to display receipt information
        JLabel fromLabel = new JLabel("From: " + selectedFrom);
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        fromLabel.setBounds(50, 100, 400, 30);
        panel.add(fromLabel);

        JLabel toLabel = new JLabel("To: " + selectedTo);
        toLabel.setForeground(Color.WHITE);
        toLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        toLabel.setBounds(50, 130, 400, 30);
        panel.add(toLabel);

        JLabel dateLabel = new JLabel("Date: " + selectedDate);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setBounds(50, 160, 400, 30);
        panel.add(dateLabel);

        JLabel timeLabel = new JLabel("Time: " + selectedTime);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timeLabel.setBounds(50, 190, 400, 30);
        panel.add(timeLabel);

        JLabel classLabel = new JLabel("Class: " + selectedClass);
        classLabel.setForeground(Color.WHITE);
        classLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        classLabel.setBounds(50, 220, 400, 30);
        panel.add(classLabel);

        JLabel seatLabel = new JLabel("Seat: " + selectedSeat);
        seatLabel.setForeground(Color.WHITE);
        seatLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        seatLabel.setBounds(50, 250, 400, 30);
        panel.add(seatLabel);

        // Placeholder for PNR
        JLabel pnrLabel = new JLabel("PNR:" + generatedPnr);
        pnrLabel.setForeground(Color.WHITE);
        pnrLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        pnrLabel.setBounds(50, 280, 400, 30);
        panel.add(pnrLabel);

        // Add other components and UI setup for PayView here...

        int yOffset = 350;

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(54, 100, 139));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement the action when the back button is clicked
                dispose();
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(150, yOffset, 100, 40);
        panel.add(backButton);

        JButton bookAgainButton = new JButton("Book Again");
        bookAgainButton.setForeground(Color.WHITE);
        bookAgainButton.setBackground(new Color(54, 100, 139));
        bookAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement the action when the "Book Again" button is clicked
                openConfirmAndProceedView(selectedFrom, selectedTo, selectedDate, selectedTime, selectedClass, selectedSeat);
                dispose();
            }
        });
        bookAgainButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookAgainButton.setBounds(300, yOffset, 150, 40);
        panel.add(bookAgainButton);

        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 800, 600);
    }

    private void openConfirmAndProceedView(String from, String to, String date, String time, String travelClass, String selectedSeat) {
        ConfirmAndProceedView confirmAndProceedView = new ConfirmAndProceedView(userId,from, to, date, time, travelClass, selectedSeat);
        confirmAndProceedView.setVisible(true);
    }


}
