/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmgui;

/**
 *
 * @author HP
 */
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ATMGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JLabel title, nameLabel, userLabel, passLabel, accLabel;
    private JTextField nameText, userText, accText;
    private JPasswordField passText;
    private JButton registerButton, loginButton;
    private JTextArea historyArea;
    private JScrollPane scrollPane;
    private JComboBox<String> choiceBox;
    private JTextField amountField;
    private JButton submitButton;
    
    private static String name;
    private static int balance = 0;
    private static String accnumber;
    private static ArrayList<String> history = new ArrayList<String>();

    public ATMGUI() {
        super("ATM Interface");

        // Create and configure components
        panel = new JPanel();
        panel.setLayout(null);

        title = new JLabel("ATM Interface");
        title.setBounds(200, 10, 200, 30);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 80, 25);
        nameText = new JTextField(20);
        nameText.setBounds(140, 60, 165, 25);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 100, 80, 25);
        userText = new JTextField(20);
        userText.setBounds(140, 100, 165, 25);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 140, 80, 25);
        passText = new JPasswordField(20);
        passText.setBounds(140, 140, 165, 25);

        accLabel = new JLabel("Account Number:");
        accLabel.setBounds(50, 180, 100, 25);
        accText = new JTextField(20);
        accText.setBounds(140, 180, 165, 25);

        registerButton = new JButton("Register");
        registerButton.setBounds(50, 220, 100, 25);
        registerButton.addActionListener(this);

        loginButton = new JButton("Login");
        loginButton.setBounds(200, 220, 100, 25);
        loginButton.addActionListener(this);

        choiceBox = new JComboBox<String>(new String[]{"Withdraw", "Deposit", "Transfer", "Check Balance"});
        choiceBox.setBounds(50, 280, 100, 25);

        amountField = new JTextField(20);
        amountField.setBounds(200, 280, 100, 25);

        submitButton = new JButton("Submit");
        submitButton.setBounds(350, 280, 100, 25);
        submitButton.addActionListener(this);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        scrollPane = new JScrollPane(historyArea);
        scrollPane.setBounds(50, 320, 400, 150);

        // Add components to panel
        panel.add(title);
        panel.add(nameLabel);
        panel.add(nameText);
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passLabel);
        panel.add(passText);
        panel.add(accLabel);
        panel.add(accText);
        panel.add(registerButton);
        panel.add(loginButton);
        panel.add(choiceBox);
        panel.add(amountField);
        panel.add(submitButton);
        panel.add(scrollPane);

        // Set JFrame properties
        setContentPane(panel);
        setSize(500, 550);
        // Add window listener to handle closing event
addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
    System.exit(0);
    }
    });
        // Display JFrame
        setVisible(true);
    }
    
    // Action listener method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
    
        if (action.equals("Register")) {
            name = nameText.getText();
            accnumber = accText.getText();
    
            // Validate input fields
            if (name.equals("") || accnumber.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Account registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    
        if (action.equals("Login")) {
            String username = userText.getText();
            String password = passText.getText();
    
            // Validate input fields
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    
        if (action.equals("Submit")) {
            String choice = (String) choiceBox.getSelectedItem();
            String amountStr = amountField.getText();
    
            // Validate input fields
            if (amountStr.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please enter amount.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int amount = Integer.parseInt(amountStr);
    
                // Perform selected action
                switch (choice) {
                    case "Withdraw":
                        if (amount <= 0) {
                            JOptionPane.showMessageDialog(panel, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (amount > balance) {
                            JOptionPane.showMessageDialog(panel, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            balance = balance - amount;
                            history.add("Withdraw : Rs. " + amount);
                            JOptionPane.showMessageDialog(panel, "Withdraw successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
    
                    case "Deposit":
                        if (amount <= 0) {
                            JOptionPane.showMessageDialog(panel, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            balance = balance + amount;
                            history.add("Deposit : Rs. " + amount);
                            JOptionPane.showMessageDialog(panel, "Deposit successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
    
                    case "Transfer":
                        if (amount <= 0) {
                            JOptionPane.showMessageDialog(panel, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (amount > balance) {
                            JOptionPane.showMessageDialog(panel, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String accnum = JOptionPane.showInputDialog(panel, "Enter account number to transfer to:");
    
                            // Validate input
                            if (accnum == null || accnum.equals("")) {
                                JOptionPane.showMessageDialog(panel, "Invalid account number.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                history.add("Transfer of Rs. : " + amount + " to account " + accnum);
                                JOptionPane.showMessageDialog(panel, "Transfer successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                balance = balance - amount;
                            }
                        }
                        break;
                        
                    case "Check Balance":
                        if (balance == 0) {
                                JOptionPane.showMessageDialog(panel, "Your account is empty", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                history.add("Balance Check : " + balance);
                                JOptionPane.showMessageDialog(panel, "Your balance is : " + balance, "Balance check", JOptionPane.INFORMATION_MESSAGE);
                            }
                        break;
    
                    default:
                        JOptionPane.showMessageDialog(panel, "Invalid choice.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
    
                // Update history
                historyArea.setText("");
                for (String h : history) {
                    historyArea.append(h + "\n");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new ATMGUI();
    }
}    
