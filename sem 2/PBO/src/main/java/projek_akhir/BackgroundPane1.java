package projek_akhir;

import javax.swing.*;
import java.awt.*;

public class BackgroundPane1 extends JPanel {
    private Image backgroundImage;

    // Constructor
    public BackgroundPane1(String fileName) {
        // Load the background image
        loadImage(fileName);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Method to update background image
    public void setBackgroundImage(String fileName) {
        loadImage(fileName);
        repaint();
    }

    private void loadImage(String fileName) {
        ImageIcon icon = new ImageIcon(fileName);
        if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
            backgroundImage = icon.getImage();
        } else {
            backgroundImage = null;
            setBackground(Color.WHITE); // Default background color if image not found
        }
    }
}
