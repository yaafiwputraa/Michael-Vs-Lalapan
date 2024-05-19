package src.main.Plantchild;

import src.main.GameMap;
import src.main.AbstractClass.Plant;

public class Sunflower extends Plant {
    private GameMap gameMap;

    public Sunflower(int x, int y, GameMap gameMap) {
        super("sunflower",50, 100, 0, 0, 0, 10, x, y);
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        gameMap.addSuns(25);
    }

    public boolean isAttackingType() {
        return false;
    }
}

