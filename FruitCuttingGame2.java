import javax.swing.*;


public class FruitCuttingGame2 extends JFrame {
    public FruitCuttingGame2() {
        setTitle("Fruit Cutting Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        add(new GamePanel()); // Add the main game panel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FruitCuttingGame2 game = new FruitCuttingGame2();
            game.setVisible(true);
        });
    }
}
