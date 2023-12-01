package javagui.Reservation;

import com.toedter.calendar.JDateChooser;

import services.ReservationDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchTrainView extends JFrame {

    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JDateChooser dateChooser;
    private JComboBox<String> timeComboBox;
    private JComboBox<String> classComboBox;

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

        createLabelAndComboBox(panel, "From:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getCityOptions());
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;
//        loginButton.addActionListener(e -> authenticateUser());

        createLabelAndComboBox(panel, "To:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getCityOptions());
        labelY += labelComboBoxGap;
        comboBoxY += labelComboBoxGap;

        dateChooser = new JDateChooser();
        dateChooser.setBounds(comboBoxX, comboBoxY, 150, 30);
        panel.add(dateChooser);

        createLabelAndComboBox(panel, "Date of Journey:", labelX, labelY, comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight, getDateOptions());
        labelY += labelComboBoxGap;
        comboBoxX += 20;
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
        reservationButton.addActionListener(e -> openRegisterView());
        panel.add(reservationButton);

        pack();
        setLocationRelativeTo(null);
    }
    private void openRegisterView() {
    	SearchResultsView searchResultsView = new SearchResultsView();
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
        // Replace this with your own data or logic to get city options
        return ReservationDAO.getCityOptions();
    }

    private String[] getDateOptions() {
        // Replace this with your own data or logic to get date options
        return new String[]{""};
    }

    private String[] getTimeOptions() {
        // Replace this with your own data or logic to get time options
        return new String[]{""};
    }

	 private String[] getClassOptions() {
	        // Replace this with your own data or logic to get class options
	        return new String[]{"Economy", "Premium", "Salon"};
	        }
}
