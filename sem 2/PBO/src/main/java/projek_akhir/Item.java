package projek_akhir;

public class Item {
    private String type;
    private String effect;
    private int cost;

    public Item(String type, String effect, int cost) {
        this.type = type;
        this.effect = effect;
        this.cost = cost;
    }

    public void applyEffect(Monster monster) {
        switch (type.toLowerCase()) {
            case "health potion":
                applyHealthEffect(monster);
                break;
            case "fire potion":
                applyElementalEffect(monster, new FireFeature());
                break;
            case "water potion":
                applyElementalEffect(monster, new WaterFeature());
                break;
            case "wind potion":
                applyElementalEffect(monster, new WindFeature());
                break;
            case "earth potion":
                applyElementalEffect(monster, new EarthFeature());
                break;
            case "ice potion":
                applyElementalEffect(monster, new IceFeature());
                break;
            default:
                System.out.println("Unknown item type.");
        }
    }

//    private void applyHealthEffect(Monster monster) {
//        int healAmount = 20; // Contoh jumlah penyembuhan
//        monster.setHealthPoints(monster.getHealthPoints() + healAmount);
//        System.out.println("Applied health potion. " + monster.getName() + " recovers " + healAmount + " HP.");
//    }
    
    private void applyHealthEffect(Monster monster) {
        monster.restoreHealth(); // Mengisi penuh HP
        System.out.println("Applied health potion. " + monster.getName() + " recovers to full HP.");
    }

    private void applyElementalEffect(Monster monster, ElementalFeature feature) {
        System.out.println("Applied " + type + ". " + monster.getName() + " can perform a one-time " + effect + " attack.");
        monster.setElementalFeature(feature);
    }

    public String getType() {
        return type;
    }

    public String getEffect() {
        return effect;
    }

    public int getCost() {
        return cost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
