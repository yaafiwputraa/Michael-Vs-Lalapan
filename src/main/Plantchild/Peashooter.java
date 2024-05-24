package src.main.Plantchild;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;
import src.main.PlantType;

public class Peashooter extends Plant {
    private GameMap gameMap;

    public Peashooter(int x, int y, GameMap gameMap) {
        super("repeater",100, 100, 25, 4, -1, 10, x, y, PlantType.PEASHOOTER);
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

    public boolean isAttackingType() {
        return true;
    }

    public Plant clone(int x, int y, GameMap gameMap) {
        return new Peashooter(x, y, gameMap);
    }
}


