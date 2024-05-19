package src.main.Plantchild;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class Chomper extends Plant {

    private GameMap gameMap;
    public Chomper(int x, int y, GameMap gameMap) {
        super("chomper",25, 100, 0, 20, 0, 10, x, y); // Assuming parameters for Plant are cost, health, attackDamage, attackSpeed, range, cooldown, x, y
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // TangleKelp drowns zombies directly in front of it and removes itself
        Zombie target = gameMap.getZombieAt(x + 1, y); // Get zombie right in front
        if (target != null) {
            gameMap.removeZombie(target); // Remove the zombie
        }
    }

    public boolean isAttackingType() {
        return true; // TangleKelp is an attacking type plant
    }
}