package main.java.com.michaelvslalapan.child_plant;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Map.GameMap;

public class Peashooter extends Plant {
    private GameMap tile;

    public Peashooter(int x, int y, GameMap tile) {
        super("Peashooter", 100, 100, 25, 4, false, x, y);
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // Attack all zombies in the same column
        for (Zombie zombie : gameMap.getZombiesInColumn(y)) {
            if (zombie.isAlive()) {
                zombie.takeDamage(getAttackDamage());
            }
        }
    }

    public boolean isAttackingType() {
        return true;
    }
}
