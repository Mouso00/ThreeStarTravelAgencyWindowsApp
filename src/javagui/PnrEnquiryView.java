package javagui;

import javax.swing.*;

import models.Reservation;
import services.ReservationDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnrEnquiryView extends JFrame {

    private JTextField pnrField;
    private JTextArea enquiryInfoArea;

    public PnrEnquiryView(String userId) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("PNR Enquiry View");
        setBounds(100, 100, 800, 700);
        setMinimumSize(new Dimension(800, 700));

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(54, 100, 139));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("PNR Enquiry");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc_titleLabel = new GridBagConstraints();
        gbc_titleLabel.gridx = 1;
        gbc_titleLabel.gridy = 0;
        gbc_titleLabel.insets.top = 20;
        contentPane.add(titleLabel, gbc_titleLabel);

        pnrField = new JTextField(20);
        pnrField.setFont(new Font("Arial", Font.PLAIN, 16));
        GridBagConstraints gbc_pnrField = new GridBagConstraints();
        gbc_pnrField.gridx = 1;
        gbc_pnrField.gridy = 1;
        gbc_pnrField.insets.top = 60;
        gbc_pnrField.insets.bottom = 20;
        gbc_pnrField.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(pnrField, gbc_pnrField);

        JButton btnEnquire = createStyledButton("Enquire");
        btnEnquire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pnr = pnrField.getText();

                if (pnr.isEmpty()) {
                    showMessage("PNR cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String enquiryInfo = getEnquiryInfo(pnr);
                    enquiryInfoArea.setText(enquiryInfo);
                    enquiryInfoArea.setVisible(true);
                }
            }
        });

        GridBagConstraints gbc_btnEnquire = new GridBagConstraints();
        gbc_btnEnquire.gridx = 1;
        gbc_btnEnquire.gridy = 2;
        gbc_btnEnquire.insets.top = 20;
        contentPane.add(btnEnquire, gbc_btnEnquire);

        enquiryInfoArea = new JTextArea();
        enquiryInfoArea.setEditable(false);
        enquiryInfoArea.setWrapStyleWord(true);
        enquiryInfoArea.setLineWrap(true);
        enquiryInfoArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        enquiryInfoArea.setFont(new Font("Arial", Font.PLAIN, 16));
        enquiryInfoArea.setForeground(Color.WHITE);
        enquiryInfoArea.setBackground(new Color(54, 100, 139));
        enquiryInfoArea.setVisible(false);

        GridBagConstraints gbc_enquiryInfoArea = new GridBagConstraints();
        gbc_enquiryInfoArea.gridx = 1;
        gbc_enquiryInfoArea.gridy = 3;
        gbc_enquiryInfoArea.insets.top = 20;
        gbc_enquiryInfoArea.fill = GridBagConstraints.BOTH;
        contentPane.add(enquiryInfoArea, gbc_enquiryInfoArea);

        JButton btnBackToMainMenu = createStyledButton("Back to Main Menu");
        btnBackToMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle back to main menu action
            }
        });

        GridBagConstraints gbc_btnBackToMainMenu = new GridBagConstraints();
        gbc_btnBackToMainMenu.gridx = 1;
        gbc_btnBackToMainMenu.gridy = 4;
        gbc_btnBackToMainMenu.insets.top = 20;
        contentPane.add(btnBackToMainMenu, gbc_btnBackToMainMenu);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(54, 100, 139));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private String getEnquiryInfo(String pnr) {
        try {
            Reservation reservation = ReservationDAO.getReservationByPnr(pnr);

            if (reservation != null) {
                StringBuilder info = new StringBuilder();
                info.append("Train Number: ").append(reservation.getTrainNumber()).append("\n");
                info.append("Class Type: ").append(reservation.getTravelClass()).append("\n");
                info.append("Date of Journey: ").append(reservation.getDate()).append("\n");
                info.append("Source Location: ").append(reservation.getFrom()).append("\n");
                info.append("Destination Location: ").append(reservation.getTo()).append("\n");
                info.append("Status: ").append(reservation.getStatus()).append("\n");
                info.append("Time of Journey: ").append(reservation.getTime()).append("\n");
                info.append("Seat: ").append(reservation.getSeat()).append("\n");
                info.append("Price: ").append(reservation.getPrice()).append("\n");

              

                return info.toString();
            } else {
                return "No reservation found for PNR: " + pnr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving reservation information";
        }
    }



    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}
