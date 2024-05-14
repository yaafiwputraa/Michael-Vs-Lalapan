package main.java.com.michaelvslalapan.child_plant;
import java.util.List;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.GameMap;

public class Repeater extends Plant {

    public Repeater(int x, int y, GameMap gameMap) {
        super("Repeater", 200, 50, 8, false, x, y, 200, -1, 20, gameMap);  // Initialize with specified attributes
    }

    @Override
    public void performAction() {
        // This method handles the attack mechanism of the Repeater
        // It attacks all zombies in the same column as the Repeater
        List<Zombie> targets = gameMap.getZombiesInColumn(y);
        if (!targets.isEmpty()) {
            for (Zombie zombie : targets) {
                if (zombie.isAlive()) {
                    zombie.takeDamage(getAttackDamage());
                }
            }
        }
    }
    @Override
    public boolean isAttackingType() {
        return true; // Repeater is an attacking type plant
    }

    @Override
    public void decreaseHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(11, 6);
        Repeater myRepeater = new Repeater(3, 4, gameMap);
        myRepeater.displayPlant();
    }
}
