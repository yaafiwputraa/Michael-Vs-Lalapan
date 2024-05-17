
package main.java.com.michaelvslalapan.child_plant;

import main.java.com.michaelvslalapan.AbstractClass.*;
import main.java.com.michaelvslalapan.GameMap;

public class Peashooter extends Plant {
    private GameMap gameMap;

    public Peashooter(int x, int y, GameMap gameMap) {
        super("repeater",100, 100, 25, 4, -1, 10, x, y);
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // Attack all zombies in the same column
        for (Zombie zombie : gameMap.getZombiesInColumn(y)) {
            if (zombie.isAlive()) {
                zombie.takeDamage(attackDamage);
            }
        }
    }

    public boolean isAttackingType() {
        return true;
    }
}
