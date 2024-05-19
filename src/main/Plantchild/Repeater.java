package src.main.Plantchild;
import java.util.List;
import src.main.AbstractClass.Plant;
import src.main.GameMap;
import src.main.AbstractClass.Zombie;
import java.util.List;

public class Repeater extends Plant {
    private GameMap gameMap;

    public Repeater(int x, int y, GameMap gameMap) {
        super("repeater",150, 100, 50, 3, -1, 20, x, y);  // Initialize with specified attributes
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // This method handles the attack mechanism of the Repeater
        // It attacks all zombies in the same column as the Repeater
        List<Zombie> targets = gameMap.getZombiesInColumn(y);
        if (!targets.isEmpty()) {
            for (Zombie zombie : targets) {
                if (zombie.isAlive()) {
                    zombie.takeDamage(attackDamage);
                }
            }
        }
    }

    @Override
    public boolean isAttackingType() {
        return true; // Repeater is an attacking type plant
    }
}

