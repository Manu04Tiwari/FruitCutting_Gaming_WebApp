import javax.swing.*;
import java.util.HashMap;

public class Game {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Fruit Cutting Game with Login and Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        // Simulated user database (username -> password)
        HashMap<String, String> userDatabase = new HashMap<>();
        // Preload with some default users (optional)
        userDatabase.put("player", "password123");

        // Show the login panel when the game starts
        LoginPanel loginPanel = new LoginPanel(frame, userDatabase);
        frame.getContentPane().add(loginPanel);

        frame.setVisible(true);
    }
}
