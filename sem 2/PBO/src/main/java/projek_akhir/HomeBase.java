package projek_akhir;

import java.util.ArrayList;
import java.util.List;

public class HomeBase {
    private List<Item> availableItems;
    private List<Item> purchasedItems;

    public HomeBase() {
        this.availableItems = new ArrayList<>();
        this.purchasedItems = new ArrayList<>();
        initializeItems();
    }

    private void initializeItems() {
        availableItems.add(new Item("Health Potion", "Restores HP", 10));
        availableItems.add(new Item("Fire Potion", "Fire Attack", 15));
        availableItems.add(new Item("Water Potion", "Water Attack", 15));
        availableItems.add(new Item("Wind Potion", "Wind Attack", 15));
        availableItems.add(new Item("Earth Potion", "Earth Attack", 15));
        availableItems.add(new Item("Ice Potion", "Ice Attack", 15));
    }

    public Item buyItem(Player player, Monster monster, String itemName) {
        for (Item item : availableItems) {
            if (item.getType().equalsIgnoreCase(itemName)) {
                if (monster.getExperiencePoints() >= item.getCost()) {
                    monster.setExperiencePoints(monster.getExperiencePoints() - item.getCost());
                    purchasedItems.add(item);
                    System.out.println("Purchased " + item.getType() + " for " + monster.getName());
                    return item;
                } else {
                    System.out.println("Not enough EP to buy " + item.getType());
                    return null;
                }
            }
        }
        System.out.println("Item not found: " + itemName);
        return null;
    }

public boolean levelUpMonster(Monster monster) {
    if (monster.getExperiencePoints() >= 15) {
        monster.setExperiencePoints(monster.getExperiencePoints() - 15);
        try {
            monster.levelUp();
            return true;
        } catch (ValueOutOfRangeException e) {
            e.printStackTrace();
        }
    }
    return false;
}

    public void healAllMonsters(List<Monster> monsters) {
        for (Monster monster : monsters) {
            monster.restoreHealth();
        }
    }

    public boolean evolveMonster(Monster monster, Element newElement) {
        if (monster.getExperiencePoints() >= 15) {
            monster.setExperiencePoints(monster.getExperiencePoints() - 15);
            ElementalFeature newFeature = getElementalFeature(newElement);
            if (newFeature != null) {
                try {
                    monster.changeElement(newElement, newFeature);
                    return true;
                } catch (InvalidElementChangeException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private ElementalFeature getElementalFeature(Element element) {
        switch (element) {
            case FIRE:
                return new FireFeature();
            case WIND:
                return new WindFeature();
            case WATER:
                return new WaterFeature();
            case ICE:
                return new IceFeature();
            case EARTH:
                return new EarthFeature();
            default:
                return null;
        }
    }

    public List<Element> getPossibleEvolutions(Element currentElement) {
        List<Element> evolutions = new ArrayList<>();
        switch (currentElement) {
            case FIRE:
                evolutions.add(Element.ICE);
                break;
            case WIND:
                evolutions.add(Element.EARTH);
                break;
            case WATER:
                evolutions.add(Element.FIRE);
                break;
            case ICE:
                evolutions.add(Element.WIND);
                break;
            case EARTH:
                evolutions.add(Element.WATER);
                break;
            default:
                break;
        }
        return evolutions;
    }
}
