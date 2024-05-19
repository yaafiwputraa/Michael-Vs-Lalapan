package src.main.Plantchild;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class Squash extends Plant {

    private GameMap gameMap;
    public Squash(int x, int y, GameMap gameMap) {
        super("squash",25, 100, 1000, 0, 0, 10, x, y); // Assuming parameters for Plant are cost, health, attackDamage, attackSpeed, range, cooldown, x, y
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        
        Zombie target = gameMap.getZombieAt(x + 1, y); // Get zombie right in front
        if (target != null) {
            gameMap.removeZombie(target); // Remove the zombie
            gameMap.removePlant(this); // Remove TangleKelp itself after attacking
        }
    }

    public boolean isAttackingType() {
        return true; 
    }
}
