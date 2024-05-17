package main.java.com.michaelvslalapan.child_plant;

import main.java.com.michaelvslalapan.AbstractClass.*;
import main.java.com.michaelvslalapan.GameMap;

public class Squash extends Plant {

    private GameMap gameMap;
    public Squash(int x, int y, GameMap gameMap) {
        super("squash",25, 100, 0, 0, 0, 10, x, y); // Assuming parameters for Plant are cost, health, attackDamage, attackSpeed, range, cooldown, x, y
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // TangleKelp drowns zombies directly in front of it and removes itself
        Zombie target = gameMap.getZombieAt(x + 1, y); // Get zombie right in front
        if (target != null) {
            gameMap.removeZombie(target); // Remove the zombie
            gameMap.removePlant(this); // Remove TangleKelp itself after attacking
        }
    }

    public boolean isAttackingType() {
        return true; // TangleKelp is an attacking type plant
    }
}
