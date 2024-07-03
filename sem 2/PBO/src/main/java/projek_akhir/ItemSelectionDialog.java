package projek_akhir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ItemSelectionDialog extends JDialog {
    private JComboBox<String> itemComboBox;
    private JButton useItemButton;
    private List<Item> inventory;
    private Player player;
    private Monster currentMonster;

    public ItemSelectionDialog(JFrame parent, Player player, Monster currentMonster) {
        super(parent, "Use Item", true);
        this.player = player;
        this.currentMonster = currentMonster;
        this.inventory = player.getInventory();

        setLayout(new BorderLayout());

        itemComboBox = new JComboBox<>();
        for (Item item : inventory) {
            itemComboBox.addItem(item.getType());
        }

        useItemButton = new JButton("Use Item");
        useItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenItem = (String) itemComboBox.getSelectedItem();
                for (Item item : inventory) {
                    if (item.getType().equals(chosenItem)) {
                        player.useItem(item);
                        String log = "Item " + chosenItem + " digunakan!";
                        JOptionPane.showMessageDialog(parent, log);
                        break;
                    }
                }
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(itemComboBox);
        panel.add(useItemButton);

        add(panel, BorderLayout.CENTER);
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }
}
