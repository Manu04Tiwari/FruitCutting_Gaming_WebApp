import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;

public class LoginPanel extends JPanel implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private JLabel statusLabel;
    private JFrame frame;
    private HashMap<String, String> userDatabase; // Simulate user database with HashMap

    public LoginPanel(JFrame frame, HashMap<String, String> userDatabase) {
        this.frame = frame;
        this.userDatabase = userDatabase; // Pass user database

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add username field
        JLabel usernameLabel = new JLabel("Username:");
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        // Add password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Password:");
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        // Add login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginButton = new JButton("Login");
        add(loginButton, gbc);
        loginButton.addActionListener(this);

        // Add register button
        gbc.gridy = 3;
        registerButton = new JButton("Create an Account");
        add(registerButton, gbc);
        registerButton.addActionListener(e -> openRegistrationPanel()); // Open registration form

        // Add status label
        gbc.gridy = 4;
        statusLabel = new JLabel();
        add(statusLabel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Check if user exists and password matches
        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            loadGamePanel();
        } else {
            statusLabel.setText("Invalid username or password");
            statusLabel.setForeground(Color.RED);
        }
    }

    // Method to load the GamePanel
    private void loadGamePanel() {
        frame.getContentPane().removeAll(); // Remove the login panel
        frame.getContentPane().add(new GamePanel()); // Add the game panel
        frame.revalidate();
        frame.repaint();
    }

    // Method to open the registration panel
    private void openRegistrationPanel() {
        frame.getContentPane().removeAll(); // Remove current panel
        frame.getContentPane().add(new RegistrationPanel(frame, userDatabase)); // Load RegistrationPanel
        frame.revalidate();
        frame.repaint();
    }
}
