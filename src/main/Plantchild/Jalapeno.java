package src.main.Plantchild;

import java.util.List;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;
public class Jalapeno extends Plant {

    private GameMap gameMap;

    public Jalapeno(int x, int y, GameMap gameMap) {
        super("jalapeno", 25, 100, 0, 0, 0, 10, x, y); // Parameters: name, cost, health, attackDamage, attackSpeed, range, cooldown, x, y
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // Remove all zombies in the same lane (same y-coordinate)
        List<Zombie> zombiesInLane = gameMap.getZombiesInColumn(y);
        for (Zombie zombie : zombiesInLane) {
            gameMap.removeZombie(zombie);
        }

        // Remove Jalapeno itself after attacking
        gameMap.removePlant(this);
        System.out.println("Jalapeno detonated at (" + x + ", " + y + "), killing all zombies in lane " + y);
    }

    @Override
    public boolean isAttackingType() {
        return true;
    }
}
