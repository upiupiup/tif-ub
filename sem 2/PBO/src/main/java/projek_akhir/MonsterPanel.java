package projek_akhir;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MonsterPanel extends JPanel {
    private Monster monster;
    private BufferedImage monsterImage;
    private JProgressBar healthBar;

    public MonsterPanel(Monster monster) {
        this.monster = monster;
        try {
            monsterImage = ImageIO.read(new File("C:/Users/Aufii/Documents/Informatika/SEM 2/PBO/projek akhir/monster (2)/monster/" + monster.getName() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            // In case the image is not found, set a default image or handle it gracefully
            monsterImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
            Graphics g = monsterImage.getGraphics();
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, 200, 200);
            g.setColor(Color.BLACK);
            g.drawString(monster.getName(), 50, 100);
        }
        setPreferredSize(new Dimension(200, 200));
        setBackground(new Color(255, 255, 255, 0)); // Transparent background

        // Initialize health bar
        healthBar = new JProgressBar(0, monster.getMaxHealthPoints());
        healthBar.setValue(monster.getHealthPoints());
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.RED);
        healthBar.setBackground(Color.GRAY);
        healthBar.setString(healthBar.getValue() + "/" + healthBar.getMaximum() + " HP");
        
        setLayout(new BorderLayout());
        // Add components to panel
        add(healthBar, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int imageWidth = getWidth() * 3 / 4;
        int imageHeight = getHeight() * 3 / 4;
        int imageX = (getWidth() - imageWidth) / 2;
        int imageY = (getHeight() - imageHeight) / 2 - 20;
        if (monsterImage != null) {
            g.drawImage(monsterImage, imageX, imageY, imageWidth, imageHeight, this);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(monster.getName(), getWidth() / 2 - 50, getHeight() - 30); // Adjust position
        g.setFont(new Font("Tahoma", Font.PLAIN, 16));
        g.drawString("Element: " + monster.getElement(), getWidth() / 2 - 50, getHeight() - 10); // Adjust position
    }

    public void updateHealthBar() {
        // Implementasi untuk memperbarui tampilan health bar
        healthBar.setValue(monster.getHealthPoints());
        healthBar.setString(monster.getHealthPoints() + "/" + monster.getMaxHealthPoints() + " HP");
    }
}
