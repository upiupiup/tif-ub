package projek_akhir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player implements Battle {
    private List<Monster> allMonsters; // Daftar semua monster
    private List<Monster> dungeonMonsters; // Daftar monster yang digunakan di dungeon
    private Monster currentMonster;
    private HomeBase homeBase;
    private List<Item> inventory;
    private Scanner scanner;
    private Random random;

    public Player(List<Monster> monsters, HomeBase homeBase) {
        this.allMonsters = new ArrayList<>(monsters); // Inisialisasi daftar semua monster
        this.dungeonMonsters = new ArrayList<>(); // Inisialisasi daftar monster di dungeon
        this.currentMonster = allMonsters.get(0); // Default current monster
        this.homeBase = homeBase;
        this.inventory = new ArrayList<>(); // Initialize inventory
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void selectMonster(Monster monster) {
        this.currentMonster = monster;
    }

    @Override
    public void attack() {
        if (currentMonster != null) {
            currentMonster.attack(null); // Null, as no specific opponent is passed
        }
    }

    public void specialAttack() {
        if (currentMonster != null) {
            currentMonster.specialAttack(null); // Null, as no specific opponent is passed
        }
    }

    public void elementalAttack() {
        if (currentMonster != null) {
            currentMonster.elementalAttack(null); // Null, as no specific opponent is passed
        }
    }

    @Override
    public void defend() {
        if (currentMonster != null) {
            currentMonster.defend();
        }
    }

    @Override
    public void useItem(Item item) {
        if (currentMonster != null) {
            item.applyEffect(currentMonster);
            System.out.println("Used item: " + item.getType());
            inventory.remove(item); // Remove used item from inventory
        }
    }

    public boolean buyItem(Monster monster, String itemName) {
        Item item = homeBase.buyItem(this, monster, itemName); // Gunakan `this` untuk referensi ke Player
        if (item != null) {
            inventory.add(item); // Tambahkan item yang dibeli ke inventory
            return true;
        }
        return false;
    }

    public boolean levelUpMonster(Monster monster) {
        return homeBase.levelUpMonster(monster);
    }

    public void healAllMonsters() {
        homeBase.healAllMonsters(allMonsters); // Heal all monsters in allMonsters list
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public List<Monster> getMonsters() {
        return allMonsters;
    }

    public void setDungeonMonsters(List<Monster> monsters) {
        this.dungeonMonsters = monsters;
        if (!monsters.isEmpty()) {
            this.currentMonster = monsters.get(0);
        }
    }

    public void restoreMonsters() {
        this.dungeonMonsters.clear();
        this.currentMonster = allMonsters.get(0);
    }

    public void removeCurrentMonster() {
        dungeonMonsters.remove(currentMonster);
        if (!dungeonMonsters.isEmpty()) {
            currentMonster = dungeonMonsters.get(0);
        } else {
            currentMonster = null;
        }
    }

    public String getCurrentMonsterName() {
        return currentMonster.getName();
    }

    public void battle(Monster wildMonster) {
        boolean battleOver = false;
        int currentMonsterIndex = 0;
        currentMonster = dungeonMonsters.get(currentMonsterIndex);

        while (!battleOver) {
            System.out.println("Choose action: 1) Attack 2) Special Attack 3) Elemental Attack 4) Defend 5) Use Item 6) Run");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentMonster.attack(wildMonster);
                    break;
                case 2:
                    currentMonster.specialAttack(wildMonster);
                    break;
                case 3:
                    currentMonster.elementalAttack(wildMonster);
                    break;
                case 4:
                    currentMonster.defend();
                    break;
                case 5:
                    if (!inventory.isEmpty()) {
                        System.out.println("Choose an item to use:");
                        for (int i = 0; i < inventory.size(); i++) {
                            System.out.println((i + 1) + ". " + inventory.get(i).getType());
                        }
                        int itemChoice = scanner.nextInt();
                        if (itemChoice > 0 && itemChoice <= inventory.size()) {
                            useItem(inventory.get(itemChoice - 1));
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    } else {
                        System.out.println("No items available!");
                    }
                    break;
                case 6:
                    if (random.nextDouble() > 0.5) {
                        System.out.println(currentMonster.getName() + " successfully ran away!");
                        return;
                    } else {
                        System.out.println("Failed to run away!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

            if (wildMonster.getHealthPoints() <= 0) {
                wildMonster.setHealthPoints(0);
                System.out.println(wildMonster.getName() + " is defeated!");
                currentMonster.addEP(50); // Add EP to the current monster
                System.out.println(currentMonster.getName() + " berhasil mendapatkan 50 EP!");
                battleOver = true;
                break;
            }

            // Wild monster's turn
            int wildMonsterAction = random.nextInt(4) + 1;
            switch (wildMonsterAction) {
                case 1:
                    wildMonster.attack(currentMonster);
                    break;
                case 2:
                    wildMonster.specialAttack(currentMonster);
                    break;
                case 3:
                    wildMonster.elementalAttack(currentMonster);
                    break;
                case 4:
                    wildMonster.defend();
                    break;
                default:
                    System.out.println("Wild monster did nothing.");
                    break;
            }

            if (currentMonster.getHealthPoints() <= 0) {
                System.out.println("Monster Anda (" + currentMonster.getName() + ") telah dikalahkan!");
                currentMonsterIndex++;
                if (currentMonsterIndex >= dungeonMonsters.size()) {
                    System.out.println("Semua monster Anda telah dikalahkan!");
                    battleOver = true;
                    return;
                } else {
                    currentMonster = dungeonMonsters.get(currentMonsterIndex);
                    System.out.println("Monster berikutnya: " + currentMonster.getName());
                }
            }
        }
    }
}
