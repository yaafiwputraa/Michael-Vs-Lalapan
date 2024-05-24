package src.main.Plantchild;
import java.util.List;
import src.main.AbstractClass.Plant;
import src.main.GameMap;
import src.main.PlantType;
import src.main.AbstractClass.Zombie;
import java.util.List;

public class Repeater extends Plant {
    private GameMap gameMap;

    public Repeater(int x, int y, GameMap gameMap) {
        super("repeater",200, 100, 50, 3, -1, 20, x, y, PlantType.REPEATER);  
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        Zombie closestZombie = null;
        int closestDistance = Integer.MAX_VALUE;

        
        for (Zombie zombie : gameMap.getZombiesInColumn(y)) {
            if (zombie.isAlive() && zombie.getX() > x) { 
                int distance = zombie.getX() - x;
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestZombie = zombie;
                }
            }
        }

        // Attack the closest zombie
        if (closestZombie != null) {
            closestZombie.takeDamage(attackDamage);
        }
    }

    @Override
    public boolean isAttackingType() {
        return true; 
    }

    @Override
    public Plant clone(int x, int y, GameMap gameMap) {
        return new Repeater(x, y, gameMap);
    }
}

