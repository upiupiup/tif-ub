package projek_akhir;

public interface ElementalFeature {
    String elementalAttack(Monster attacker, Monster opponent);
    String applyElementalEffect();
}

class WaterFeature implements ElementalFeature {
    @Override
    public String elementalAttack(Monster attacker, Monster opponent) {
        StringBuilder log = new StringBuilder();
        log.append(attacker.getName()).append(" menggunakan serangan elemen air!\n");

        int damage;
        if (opponent.getElement() == Element.FIRE) {
            damage = (int) (0.3 * attacker.getLevel()) + 30; // More damage for effective attack
            log.append("It's super effective! The water attack deals ").append(damage).append(" damage!\n");
        } else {
            damage = (int) (0.15 * attacker.getLevel()) + 10; // Less damage for non-effective attack
            log.append("The water attack deals ").append(damage).append(" damage.\n");
        }

        opponent.setHealthPoints(opponent.getHealthPoints() - damage);
        log.append(opponent.getName()).append(" now has ").append(opponent.getHealthPoints()).append(" HP.\n");
        return log.toString();
    }

    @Override
    public String applyElementalEffect() {
        return "Applying water elemental effect! The target is soaked and has reduced accuracy.";
    }
}


class FireFeature implements ElementalFeature {
    @Override
    public String elementalAttack(Monster attacker, Monster opponent) {
        StringBuilder log = new StringBuilder();
        log.append(attacker.getName()).append(" menggunakan serangan elemen api!\n");

        int damage;
        if (opponent.getElement() == Element.WIND) {
            damage = (int) (0.3 * attacker.getLevel()) + 30; // More damage for effective attack
            log.append("It's super effective! The fire attack deals ").append(damage).append(" damage!\n");
        } else {
            damage = (int) (0.15 * attacker.getLevel()) + 10; // Less damage for non-effective attack
            log.append("The fire attack deals ").append(damage).append(" damage.\n");
        }

        opponent.setHealthPoints(opponent.getHealthPoints() - damage);
        log.append(opponent.getName()).append(" now has ").append(opponent.getHealthPoints()).append(" HP.\n");
        return log.toString();
    }

    @Override
    public String applyElementalEffect() {
        return "Applying fire elemental effect! The target is burned and will take extra damage over time.";
    }
}

class WindFeature implements ElementalFeature {
    @Override
    public String elementalAttack(Monster attacker, Monster opponent) {
        StringBuilder log = new StringBuilder();
        log.append(attacker.getName()).append(" menggunakan serangan elemen angin!\n");

        int damage;
        if (opponent.getElement() == Element.EARTH) {
            damage = (int) (0.3 * attacker.getLevel()) + 30; // More damage for effective attack
            log.append("It's super effective! The wind attack deals ").append(damage).append(" damage!\n");
        } else {
            damage = (int) (0.15 * attacker.getLevel()) + 10; // Less damage for non-effective attack
            log.append("The wind attack deals ").append(damage).append(" damage.\n");
        }

        opponent.setHealthPoints(opponent.getHealthPoints() - damage);
        log.append(opponent.getName()).append(" now has ").append(opponent.getHealthPoints()).append(" HP.\n");
        return log.toString();
    }

    @Override
    public String applyElementalEffect() {
        return "Applying wind elemental effect! The user is surrounded by a storm and has increased speed.";
    }
}

class EarthFeature implements ElementalFeature {
    @Override
    public String elementalAttack(Monster attacker, Monster opponent) {
        StringBuilder log = new StringBuilder();
        log.append(attacker.getName()).append(" menggunakan serangan elemen tanah!\n");

        int damage;
        if (opponent.getElement() == Element.WIND) {
            damage = (int) (0.3 * attacker.getLevel()) + 30; // More damage for effective attack
            log.append("It's super effective! The earth attack deals ").append(damage).append(" damage!\n");
        } else {
            damage = (int) (0.15 * attacker.getLevel()) + 10; // Less damage for non-effective attack
            log.append("The earth attack deals ").append(damage).append(" damage.\n");
        }

        opponent.setHealthPoints(opponent.getHealthPoints() - damage);
        log.append(opponent.getName()).append(" now has ").append(opponent.getHealthPoints()).append(" HP.\n");
        return log.toString();
    }

    @Override
    public String applyElementalEffect() {
        return "Applying earth elemental effect! The user is reinforced by earth and has increased defense.";
    }
}


class IceFeature implements ElementalFeature {
    @Override
    public String elementalAttack(Monster attacker, Monster opponent) {
        StringBuilder log = new StringBuilder();
        log.append(attacker.getName()).append(" menggunakan serangan elemen es!\n");

        int damage;
        if (opponent.getElement() == Element.WATER) {
            damage = (int) (0.3 * attacker.getLevel()) + 30; // More damage for effective attack
            log.append("It's super effective! The ice attack deals ").append(damage).append(" damage!\n");
        } else {
            damage = (int) (0.15 * attacker.getLevel()) + 10; // Less damage for non-effective attack
            log.append("The ice attack deals ").append(damage).append(" damage.\n");
        }

        opponent.setHealthPoints(opponent.getHealthPoints() - damage);
        log.append(opponent.getName()).append(" now has ").append(opponent.getHealthPoints()).append(" HP.\n");
        return log.toString();
    }

    @Override
    public String applyElementalEffect() {
        return "Applying ice elemental effect! The target is frozen and has reduced speed.";
    }
}





