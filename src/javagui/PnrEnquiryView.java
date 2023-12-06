package javagui;

import javax.swing.*;
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
        setMinimumSize(new Dimension(800, 700)); // Set the minimum size of the frame

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(54, 100, 139)); // Set the background color
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Title label
        JLabel titleLabel = new JLabel("PNR Enquiry");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc_titleLabel = new GridBagConstraints();
        gbc_titleLabel.gridx = 1; // Centered horizontally
        gbc_titleLabel.gridy = 0;
        gbc_titleLabel.insets.top = 20; // Add space at the top
        contentPane.add(titleLabel, gbc_titleLabel);

        // PNR number field
        pnrField = new JTextField(20);
        pnrField.setFont(new Font("Arial", Font.PLAIN, 16));
        GridBagConstraints gbc_pnrField = new GridBagConstraints();
        gbc_pnrField.gridx = 1; // Centered horizontally
        gbc_pnrField.gridy = 1;
        gbc_pnrField.insets.top = 60; // Adjusted to be 20 more
        gbc_pnrField.insets.bottom = 20;
        gbc_pnrField.fill = GridBagConstraints.HORIZONTAL; // Allow the field to expand horizontally
        contentPane.add(pnrField, gbc_pnrField);

        // Enquire button
        JButton btnEnquire = createStyledButton("Enquire");
        btnEnquire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pnr = pnrField.getText();

                if (pnr.isEmpty()) {
                    showMessage("PNR cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Replace this with your PNR Enquiry logic
                    String enquiryInfo = getEnquiryInfo(pnr);
                    enquiryInfoArea.setText(enquiryInfo);
                    enquiryInfoArea.setVisible(true);
                }
            }
        });

        GridBagConstraints gbc_btnEnquire = new GridBagConstraints();
        gbc_btnEnquire.gridx = 1; // Centered horizontally
        gbc_btnEnquire.gridy = 2;
        gbc_btnEnquire.insets.top = 20;
        contentPane.add(btnEnquire, gbc_btnEnquire);

        // Text area to display PNR enquiry information
        enquiryInfoArea = new JTextArea();
        enquiryInfoArea.setEditable(false);
        enquiryInfoArea.setWrapStyleWord(true);
        enquiryInfoArea.setLineWrap(true);
        enquiryInfoArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the text area
        enquiryInfoArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Set the font
        enquiryInfoArea.setForeground(Color.WHITE); // Text color
        enquiryInfoArea.setBackground(new Color(54, 100, 139)); // Background color
        enquiryInfoArea.setVisible(false);

        GridBagConstraints gbc_enquiryInfoArea = new GridBagConstraints();
        gbc_enquiryInfoArea.gridx = 1;
        gbc_enquiryInfoArea.gridy = 3;
        gbc_enquiryInfoArea.insets.top = 20;
        gbc_enquiryInfoArea.fill = GridBagConstraints.BOTH; // Allow the text area to expand both horizontally and vertically
        contentPane.add(enquiryInfoArea, gbc_enquiryInfoArea);

        // Back to Main Menu button
        JButton btnBackToMainMenu = createStyledButton("Back to Main Menu");
//        btnBackToMainMenu.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Menu mainMenu = new Menu();
//                mainMenu.setVisible(true);
//                dispose();
//            }
//        });

        GridBagConstraints gbc_btnBackToMainMenu = new GridBagConstraints();
        gbc_btnBackToMainMenu.gridx = 1; // Centered horizontally
        gbc_btnBackToMainMenu.gridy = 4;
        gbc_btnBackToMainMenu.insets.top = 20;
        contentPane.add(btnBackToMainMenu, gbc_btnBackToMainMenu);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(54, 100, 139)); // Set the background color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    // Simulated method for fetching PNR enquiry information
    private String getEnquiryInfo(String pnr) {
        // Replace this with actual logic to retrieve PNR enquiry information
        // For simulation purposes, it returns a hardcoded message
        return "PNR Enquiry Information for PNR: " + pnr;
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}
