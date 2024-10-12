import java.awt.*;
import javax.swing.*;

public class Bomb {
    private int x, y, speed;
    private Image bombImage;
    private int width, height;

    public Bomb() {
        this.x = (int) (Math.random() * 800); // Random x-position
        this.y = -100; // Start above the screen
        this.speed = (int) (Math.random() * 3 + 2); // Random speed between 2 and 5

        // Load bomb image and scale it
        bombImage = new ImageIcon("C:\\Users\\Praab\\OneDrive\\Desktop\\code playground\\FruitCuttingGame\\bomb.jpg").getImage();
        width = 50; // Set width and height
        height = 50;

        // Scale the bomb image to the proper size
        bombImage = bombImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void update() {
        y += speed; // Move the bomb downwards
    }

    public void draw(Graphics g) {
        // Draw the bomb image
        g.drawImage(bombImage, x, y, null);
    }

    public boolean isOutOfScreen() {
        return y > 600; // If the bomb moves below the screen height (600)
    }

    public boolean isHit(int mouseX, int mouseY) {
        // Check if the mouse coordinates overlap with the bomb's bounding box
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}

