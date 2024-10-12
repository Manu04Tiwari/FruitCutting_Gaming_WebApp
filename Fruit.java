import java.awt.*;
import javax.swing.*;

public class Fruit {
    private int x, y, speed;
    private int type; // 1 for Mango, 2 for Apple, 3 for Strawberry, 4 for Pineapple
    private Image fruitImage; // The image for the specific fruit
    private int width, height;

    public Fruit(int type) {
        this.type = type;
        this.x = (int) (Math.random() * 800); // Random starting x-position
        this.y = -100; // Start above the screen
        this.speed = (int) (Math.random() * 3 + 2); // Random speed between 2 and 5

        // Load the image based on fruit type
        switch (type) {
            case 1: // Mango
                fruitImage = new ImageIcon("C:\\Users\\Praab\\OneDrive\\Desktop\\code playground\\FruitCuttingGame\\mango.png").getImage();
                width = 60; // Set width and height for scaling the image
                height = 60;
                break;
            case 2: // Apple
                fruitImage = new ImageIcon("C:\\Users\\Praab\\OneDrive\\Desktop\\code playground\\FruitCuttingGame\\appkle.png").getImage();
                width = 50;
                height = 50;
                break;
            case 3: // Strawberry
                fruitImage = new ImageIcon("C:\\Users\\Praab\\OneDrive\\Desktop\\code playground\\FruitCuttingGame\\straw.png").getImage();
                width = 45;
                height = 45;
                break;
            case 4: // Pineapple
                fruitImage = new ImageIcon("C:\\Users\\Praab\\OneDrive\\Desktop\\code playground\\FruitCuttingGame\\orange.png").getImage();
                width = 70;
                height = 80;
                break;
        }

        // Scale the fruit image to the proper size
        fruitImage = fruitImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void update() {
        y += speed; // Move the fruit downwards
    }

    public void draw(Graphics g) {
        // Draw the fruit image
        g.drawImage(fruitImage, x, y, null);
    }

    public boolean isOutOfScreen() {
        return y > 600; // If the fruit moves below the screen height (600)
    }

    public boolean isHit(int mouseX, int mouseY) {
        // Check if the mouse coordinates overlap with the fruit's bounding box
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
