package javagui.Reservation;

import javax.swing.*;

import javagui.Dashboard.MakeReservationView;
import javagui.Dashboard.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceiptView extends JFrame {

	private final String userId;
    private String selectedFrom;
    private String selectedTo;
    private String selectedDate;
    private String selectedTime;
    private String selectedClass;
    private String selectedSeat;
    private int generatedPnr;

    public ReceiptView(String userId,String from, String to, String date, String time, String travelClass, String selectedSeat, int generatedPnr) {
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
        setTitle("Receipt");
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

        JLabel titleLabel = new JLabel("Thank you for your payment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 30, 400, 40);
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

        JLabel seatLabel = new JLabel("Seat: " + selectedClass + " " +  selectedSeat);
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

       
        int yOffset = 350;

        JButton backButton = new JButton("Back to Menu");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(54, 100, 139));
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                Menu menuView = new Menu(userId);
                menuView.setVisible(true);
                dispose(); // 
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(50, 500, 150, 40);
        panel.add(backButton);

        JButton bookAgainButton = new JButton("Book Again");
        bookAgainButton.setForeground(Color.WHITE);
        bookAgainButton.setBackground(new Color(54, 100, 139));
        bookAgainButton.setBounds(50, 400, 400, 40);
        bookAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
     
            	openMakeReservationView(userId);
                dispose();
            }
        });
        bookAgainButton.setFont(new Font("Arial", Font.BOLD, 16));
      
        panel.add(bookAgainButton);

      
    }

    private void openMakeReservationView(String userId) {
    	MakeReservationView makeReservationView = new MakeReservationView(userId);
    	makeReservationView.setVisible(true);
    }


}
