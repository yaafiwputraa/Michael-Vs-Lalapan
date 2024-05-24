package src.main.Plantchild;

import src.main.GameMap;
import src.main.PlantType;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class Squash extends Plant {

    private GameMap gameMap;
    public Squash(int x, int y, GameMap gameMap) {
        super("squash",25, 100, 1000, 0, 0, 10, x, y, PlantType.SQUASH);
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        
        Zombie target = gameMap.getZombieAt(x + 1, y); 
        if (target != null) {
            gameMap.removeZombie(target); 
            gameMap.removePlant(this); 
        }
    }

    public boolean isAttackingType() {
        return true; 
    }

    @Override
    public Plant clone(int x, int y, GameMap gameMap) {
        return new Squash(x, y, gameMap);
    }
}
