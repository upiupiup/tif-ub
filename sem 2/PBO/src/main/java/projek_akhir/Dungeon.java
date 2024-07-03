/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package projek_akhir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dungeon {
    private List<Monster> wildMonsters;

    public Dungeon() {
        this.wildMonsters = new ArrayList<>();
        generateWildMonsters();
    }

    private void generateWildMonsters() {
        wildMonsters.add(new ConcreteMonster("Ditto", 5, 48, Element.WIND, new WindFeature()));
        wildMonsters.add(new ConcreteMonster("Bulbasaur", 5, 58, Element.EARTH, new EarthFeature()));
        wildMonsters.add(new ConcreteMonster("Charmander", 5, 78, Element.FIRE, new FireFeature()));
        wildMonsters.add(new ConcreteMonster("Squirtle", 5, 79, Element.WATER, new WaterFeature()));
        wildMonsters.add(new ConcreteMonster("Pikachu", 5, 60, Element.FIRE, new FireFeature()));
        wildMonsters.add(new ConcreteMonster("Sandslash", 5, 70, Element.ICE, new IceFeature()));
        wildMonsters.add(new ConcreteMonster("Golem", 5, 80, Element.EARTH, new EarthFeature()));
        wildMonsters.add(new ConcreteMonster("Spheal", 5, 90, Element.WATER, new WaterFeature()));
    }
    

    public Monster encounterMonster() {
        Random random = new Random();
        return wildMonsters.get(random.nextInt(wildMonsters.size()));
    }
    
    public void enterDungeon(Player player) {
    System.out.println("Anda berada di dungeon.");
    System.out.println("Monster anda saat ini adalah " + player.getCurrentMonsterName());
    }
}
