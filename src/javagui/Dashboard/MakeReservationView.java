package javagui.Dashboard;

import com.toedter.calendar.JDateChooser;

import javagui.Reservation.TrainRecordsView;
import services.ReservationDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MakeReservationView extends JFrame {

	private final String userId;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JDateChooser dateChooser;
    private JComboBox<String> timeComboBox;
    private JComboBox<String> classComboBox;

    public MakeReservationView(String userId) {
    	this.userId = userId;
        initializeUI(); 
	}

	private void initializeUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Make a Reservation");
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

        JLabel titleLabel = new JLabel("Make a Reservation");
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

        fromComboBox = createLabelAndComboBox(panel, "From:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getCityOptions());
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        toComboBox = createLabelAndComboBox(panel, "To:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getCityOptions());
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        dateChooser = new JDateChooser();
        dateChooser.setBounds(comboBoxX, comboBoxY, 150, 30);
        panel.add(dateChooser);

        createLabelAndComboBox(panel, "Date of Journey:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getDateOptions());
        labelY += labelComboBoxGap;
        comboBoxX += 0;
        comboBoxY += labelComboBoxGap;

        timeComboBox = createLabelAndComboBox(panel, "Time:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getTimeOptions());
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        classComboBox = createLabelAndComboBox(panel, "Class:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getClassOptions());

        JButton reservationButton = new JButton("Search");
        reservationButton.setForeground(Color.WHITE);
        reservationButton.setBackground(new Color(54, 100, 139));
        reservationButton.setFont(new Font("Arial", Font.BOLD, 16));
        reservationButton.setBounds(300, 400, 150, 40);
        reservationButton.addActionListener(e -> openSearchResultsView(userId));
        panel.add(reservationButton);
        
        JButton backButton = new JButton("Back to Menu");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(54, 100, 139));
        backButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the Menu
                Menu menuView = new Menu(userId);
                menuView.setVisible(true);
                dispose(); // Close the current MakeReservationView frame
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(50, 500, 150, 40);
        panel.add(backButton);
    }
    
	private void openSearchResultsView(String userId) {
	    String selectedFrom = (String) fromComboBox.getSelectedItem();
	    String selectedTo = (String) toComboBox.getSelectedItem();
	    java.util.Date selectedDate = dateChooser.getDate();
	    String selectedTime = (String) timeComboBox.getSelectedItem();
	    String selectedClass = (String) classComboBox.getSelectedItem();

	    if (selectedDate == null) {
	        // No date selected
	        JOptionPane.showMessageDialog(this, "Please select a date.", "Date Selection Error", JOptionPane.ERROR_MESSAGE);
	        return; 
	    }

	    String formattedDate = formatDate(selectedDate);

	    TrainRecordsView searchResultsView = new TrainRecordsView(userId, selectedFrom, selectedTo, formattedDate, selectedTime, selectedClass);
	    searchResultsView.setVisible(true);
	    dispose();
	}

    private JComboBox<String> createLabelAndComboBox(JPanel panel, String labelText, int labelX, int labelY, int comboBoxX, int comboBoxY, int comboBoxWidth, int comboBoxHeight, String[] options) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setBounds(labelX, labelY, 120, 30);
        panel.add(label);

        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight);
        panel.add(comboBox);

        return comboBox;
    }

    private String[] getCityOptions() {
        
        return ReservationDAO.getCityOptions();
    }

    private String[] getDateOptions() {
   
        return new String[]{""};
    }

    private String[] getTimeOptions() {
        List<String> timeOptions = new ArrayList<>();
        for (int hour = 1; hour <= 24; hour += 3) {
            for (int minute = 0; minute < 60; minute += 60) {
                String time = String.format("%02d:%02d", hour, minute);
                timeOptions.add(time);
            }
        }

        return timeOptions.toArray(new String[0]);
    }

    private String[] getClassOptions() {
        return new String[]{"Economy", "Premium", "Salon"};
    }

    private String formatDate(java.util.Date date) {
        return date.toString();
    }
}
