package src.main.Plantchild;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class TangleKelp extends Plant {

    private GameMap gameMap;
    public TangleKelp(int x, int y, GameMap gameMap) {
        super("tanglekelp",25, 100, 0, 0, 0, 10, x, y); // Assuming parameters for Plant are cost, health, attackDamage, attackSpeed, range, cooldown, x, y
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // TangleKelp drowns zombies directly in front of it and removes itself
        Zombie target = gameMap.getZombieAt(x + 1, y); // Get zombie right in front
        if (target != null) {
            gameMap.removeZombie(target); // Remove the zombie
            gameMap.removePlant(this); // Remove TangleKelp itself after attacking
            System.out.println("TangleKelp at (" + x + ", " + y + ") drowned a zombie at (" + (x + 1) + ", " + y + ")");
        }
    }

    public boolean isAttackingType() {
        return true; // TangleKelp is an attacking type plant
    }
}
