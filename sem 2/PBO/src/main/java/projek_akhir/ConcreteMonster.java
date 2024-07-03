package projek_akhir;

public class ConcreteMonster extends Monster {

    public ConcreteMonster(String name, int level, int healthPoints, Element element, ElementalFeature elementalFeature) {
        super(name, level, healthPoints, element, elementalFeature); // Menambahkan healthPoints
        this.healthPoints = healthPoints;
        this.experiencePoints = 0; // Nilai default
        this.isDefending = false; // Status bertahan default
    }

    @Override
    public String attack(Monster opponent) {

        StringBuilder log = new StringBuilder();
        log.append(this.name).append(" menggunakan serangan dasar!\n");
        int damage = (int) (0.1 * this.level) + 10; // Kerusakan sedang, minimal 10
        if (opponent.isDefending()) {
            damage /= 2; // Mengurangi kerusakan jika lawan sedang bertahan
            opponent.setDefending(false); // Reset status bertahan setelah serangan
            log.append(opponent.getName()).append(" sedang bertahan! Kerusakan berkurang menjadi ").append(damage).append("\n");
        }
        opponent.setHealthPoints(Math.max(0, opponent.getHealthPoints() - damage));
        log.append("Serangan menghasilkan ").append(damage).append(" kerusakan pada ").append(opponent.getName()).append("!\n");
        log.append(opponent.getName()).append(" sekarang memiliki ").append(opponent.getHealthPoints()).append(" HP.\n");
        return log.toString();
    }

    @Override
    public String specialAttack(Monster opponent) {


        StringBuilder log = new StringBuilder();
        log.append(this.name).append(" menggunakan serangan spesial!\n");
        int damage = (int) (0.2 * this.level) + 20; // Kerusakan lebih besar, minimal 20
        double missChance = 0.1; // 10% kemungkinan meleset

        if (Math.random() < missChance) {
            log.append("Serangan spesial meleset!\n");
        } else {
            if (opponent.isDefending()) {
                damage /= 2; // Mengurangi kerusakan jika lawan sedang bertahan
                opponent.setDefending(false); // Reset status bertahan setelah serangan
                log.append(opponent.getName()).append(" sedang bertahan! Kerusakan berkurang menjadi ").append(damage).append("\n");
            }
            opponent.setHealthPoints(Math.max(0, opponent.getHealthPoints() - damage));
            log.append("Serangan spesial menghasilkan ").append(damage).append(" kerusakan pada ").append(opponent.getName()).append("!\n");
            log.append(opponent.getName()).append(" sekarang memiliki ").append(opponent.getHealthPoints()).append(" HP.\n");
        }
        return log.toString();
    }

    @Override
    public String elementalAttack(Monster opponent) {


        StringBuilder log = new StringBuilder();

        // Perform the elemental attack
        log.append(this.elementalFeature.elementalAttack(this, opponent));

        return log.toString();
    }

    @Override
    public String defend() {
        this.isDefending = true;
        return this.name + " sedang bertahan! Serangan berikutnya akan mengurangi kerusakan.";
    }

    @Override
    public void addEP(int amount) {
        this.experiencePoints += amount;
        System.out.println(this.name + " mendapatkan " + amount + " EP!");
    }

    @Override
    public void levelUp() throws ValueOutOfRangeException {
        if (this.level >= 99) {
            throw new ValueOutOfRangeException("Level monster tidak bisa lebih dari 99.");
        }
        if (this.experiencePoints >= 10) { // Misalkan diperlukan 100 EP untuk naik level
            this.level++;
            this.experiencePoints -= 10;
            System.out.println(this.name + " naik ke level " + this.level + "!");
        } else {
            System.out.println(this.name + " tidak memiliki cukup poin pengalaman untuk naik level.");
        }
    }

    @Override
    public void changeElement(Element newElement, ElementalFeature newElementalFeature) throws InvalidElementChangeException {
        this.element = newElement;
        this.elementalFeature = newElementalFeature;
        System.out.println(this.name + " berubah elemen menjadi " + newElement + " dengan fitur " + newElementalFeature);
    }

    @Override
    public boolean isDefending() {
        return isDefending;
    }

    @Override
    public void setDefending(boolean isDefending) {
        this.isDefending = isDefending;
    }
}
