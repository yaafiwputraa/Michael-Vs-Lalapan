package main.java.com.michaelvslalapan.child_plant;

import main.java.com.michaelvslalapan.AbstractClass.*;
import main.java.com.michaelvslalapan.GameMap;
import java.util.List;

public class Snowpea extends Plant {
    private GameMap gameMap;
    public Snowpea(int x, int y, GameMap gameMap) {
        super("Snowpea", 175, 100, 25, 4, -1, 10, x, y);  // Initialize with the constructor of the Plant class
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // Identify zombies in the same column
        List<Zombie> targets = gameMap.getZombiesInColumn(y);
        if (!targets.isEmpty()) {
            for (Zombie zombie : targets) {
                zombie.takeDamage(attackDamage);
                zombie.applySlowingEffect(3000);  // Apply slowing effect for 3000 milliseconds (3 seconds)
            }
        }
    }

    public boolean isAttackingType() {
        return true;  // Snowpea is an attacking type plant
    }
}
