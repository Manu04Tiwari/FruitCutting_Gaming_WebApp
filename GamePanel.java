import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, MouseMotionListener {
    private ArrayList<Fruit> fruits;
    private ArrayList<Bomb> bombs; // List to store bombs
    private Timer timer;
    private int score = 0;
    private int lives = 3;
    private boolean gameOver = false; // Game over flag
    private Font scoreFont;
    private int mouseX, mouseY; // To track mouse position
    private Image knifeImage; // Image of the knife
    private Image scaledKnifeImage; // Scaled version of the knife image

    public GamePanel() {
        this.fruits = new ArrayList<>();
        this.bombs = new ArrayList<>(); // Initialize bomb list
        this.timer = new Timer(30, this); // Timer for refreshing the game
        this.timer.start();
        this.addMouseMotionListener(this);

        scoreFont = new Font("Arial", Font.BOLD, 24); // Font for score and lives

        // Load knife image and scale it to a suitable size
        knifeImage = new ImageIcon("C:\\Users\\Praab\\OneDrive\\Desktop\\code playground\\FruitCuttingGame\\sword.jpg").getImage();
        scaledKnifeImage = knifeImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        // Add initial fruits with random types
        for (int i = 0; i < 5; i++) {
            int randomFruitType = new Random().nextInt(4) + 1; // Random fruit type between 1 and 4
            fruits.add(new Fruit(randomFruitType)); // Create a new Fruit object with the random type
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Update fruits' positions and check for out-of-screen fruits
            for (int i = 0; i < fruits.size(); i++) {
                Fruit fruit = fruits.get(i);
                fruit.update();

                if (fruit.isOutOfScreen()) {
                    lives--; // Decrease life if fruit goes out of the screen
                    fruits.remove(i);
                    int randomFruitType = new Random().nextInt(4) + 1; // Random fruit type between 1 and 4
                    fruits.add(new Fruit(randomFruitType)); // Add a new fruit after one goes off screen
                }
            }

            // Update bombs' positions
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = bombs.get(i);
                bomb.update();

                if (bomb.isOutOfScreen()) {
                    bombs.remove(i);
                    if (new Random().nextInt(100) < 20) { // 20% chance of adding a new bomb
                        bombs.add(new Bomb());
                    }
                }
            }

            // Randomly add bombs
            if (new Random().nextInt(100) < 3) { // 3% chance of adding a bomb per frame
                bombs.add(new Bomb());
            }

            repaint(); // Repaint the screen after updating
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background
        Image background = new ImageIcon("C:\\Users\\Praab\\OneDrive\\Desktop\\code playground\\FruitCuttingGame\\back.png").getImage(); // Replace with correct path
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

        // Draw the fruits
        for (Fruit fruit : fruits) {
            fruit.draw(g);
        }

        // Draw the bombs
        for (Bomb bomb : bombs) {
            bomb.draw(g);
        }

        // Draw the score and lives
        g.setFont(scoreFont);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 20, 30);
        g.drawString("Lives: " + lives, 20, 60);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.setColor(Color.RED);
            g.drawString("Game Over", this.getWidth() / 2 - 150, this.getHeight() / 2);
        }

        // Draw the knife at the mouse position
        g.drawImage(scaledKnifeImage, mouseX - scaledKnifeImage.getWidth(null) / 2, mouseY - scaledKnifeImage.getHeight(null) / 2, null);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!gameOver) {
            mouseX = e.getX();
            mouseY = e.getY();

            // Check if the knife hits any fruits
            for (int i = 0; i < fruits.size(); i++) {
                Fruit fruit = fruits.get(i);
                if (fruit.isHit(mouseX, mouseY)) {
                    fruits.remove(i);
                    score += 10;
                    int randomFruitType = new Random().nextInt(4) + 1;
                    fruits.add(new Fruit(randomFruitType));
                }
            }

            // Check if the knife hits any bombs
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = bombs.get(i);
                if (bomb.isHit(mouseX, mouseY)) {
                    gameOver = true; // End the game if a bomb is hit
                    timer.stop();
                }
            }

            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Handle mouse drag events if needed
    }
}
