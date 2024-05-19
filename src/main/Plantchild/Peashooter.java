package src.main.Plantchild;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class Peashooter extends Plant {
    private GameMap gameMap;

    public Peashooter() {
        super("Peashooter", 100, 100, 25, 4, -1, 10, 0, 0);
    }

    public Peashooter(int x, int y, GameMap gameMap) {
        this();
        this.x = x;
        this.y = y;
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

    public String getName() {
        return this.name;
    }
}

