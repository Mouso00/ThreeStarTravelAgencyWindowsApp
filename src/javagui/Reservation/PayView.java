package javagui.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayView extends JFrame {

	 private String userId;
	    private String selectedFrom;
	    private String selectedTo;
	    private String selectedDate;
	    private String selectedTime;
	    private String selectedClass;
	    private String selectedSeat;
    public PayView(String from, String to, String date, String time, String travelClass, String selectedSeat) {
    	 this.selectedFrom = from;
         this.selectedTo = to;
         this.selectedDate = date;
         this.selectedTime = time;
         this.selectedClass = travelClass;
         this.selectedSeat = selectedSeat;
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

        // Add other components and UI setup for PayView here...

        int yOffset = 100;

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(54, 100, 139));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openConfirmAndProceedView(selectedFrom, selectedTo, selectedDate, selectedTime, selectedClass, selectedSeat);

                dispose();
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(350, yOffset, 100, 40);
        panel.add(backButton);

        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 800, 600);
    }

    private void openConfirmAndProceedView(String from, String to, String date, String time, String travelClass, String selectedSeat) {
        ConfirmAndProceedView confirmAndProceedView = new ConfirmAndProceedView(from, to, date, time, travelClass, selectedSeat);
        confirmAndProceedView.setVisible(true);
        dispose();
    }

}
