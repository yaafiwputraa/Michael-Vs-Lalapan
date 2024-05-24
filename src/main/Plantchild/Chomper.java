package src.main.Plantchild;

import src.main.GameMap;
import src.main.PlantType;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class Chomper extends Plant {

    private GameMap gameMap;
    public Chomper(int x, int y, GameMap gameMap) {
        super("chomper",25, 100, 0, 20, 0, 10, x, y, PlantType.CHOMPER); 
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        
        Zombie target = gameMap.getZombieAt(x + 1, y); 
        if (target != null) {
            gameMap.removeZombie(target); 
        }
    }

    public boolean isAttackingType() {
        return true; 
    }

    public Plant clone(int x, int y, GameMap gameMap) {
        return new Chomper(x, y, gameMap);
    }
}

