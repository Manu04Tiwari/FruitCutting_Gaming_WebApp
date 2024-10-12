import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class RegistrationPanel extends JPanel implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton, backButton;
    private JLabel statusLabel;
    private JFrame frame;
    private HashMap<String, String> userDatabase; // Simulate user database with HashMap

    public RegistrationPanel(JFrame frame, HashMap<String, String> userDatabase) {
        this.frame = frame;
        this.userDatabase = userDatabase;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add username field
        JLabel usernameLabel = new JLabel("Create Username:");
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        // Add password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Create Password:");
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        // Add confirm password field
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(15);
        add(confirmPasswordField, gbc);

        // Add register button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        registerButton = new JButton("Register");
        add(registerButton, gbc);
        registerButton.addActionListener(this);

        // Add back button to go back to login
        gbc.gridy = 4;
        backButton = new JButton("Back to Login");
        add(backButton, gbc);
        backButton.addActionListener(e -> openLoginPanel());

        // Add status label
        gbc.gridy = 5;
        statusLabel = new JLabel();
        add(statusLabel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (userDatabase.containsKey(username)) {
            statusLabel.setText("Username already exists");
            statusLabel.setForeground(Color.RED);
        } else if (!password.equals(confirmPassword)) {
            statusLabel.setText("Passwords do not match");
            statusLabel.setForeground(Color.RED);
        } else if (password.length() < 6) {
            statusLabel.setText("Password must be at least 6 characters");
            statusLabel.setForeground(Color.RED);
        } else {
            // Add the new user to the user database
            userDatabase.put(username, password);
            statusLabel.setText("Registration successful!");
            statusLabel.setForeground(Color.GREEN);
        }
    }

    // Method to open the login panel
    private void openLoginPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new LoginPanel(frame, userDatabase)); // Back to LoginPanel
        frame.revalidate();
        frame.repaint();
    }
}
