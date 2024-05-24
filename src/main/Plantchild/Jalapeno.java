package src.main.Plantchild;

import java.util.List;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;
import src.main.PlantType;

public class Jalapeno extends Plant {

    private GameMap gameMap;

    public Jalapeno(int x, int y, GameMap gameMap) {
        super("jalapeno", 125, 100, 0, 0, 0, 10, x, y, PlantType.JALAPENO); 
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        
        List<Zombie> zombiesInLane = gameMap.getZombiesInColumn(y);
        for (Zombie zombie : zombiesInLane) {
            gameMap.removeZombie(zombie);
        }

        
        gameMap.removePlant(this);
        System.out.println("DDUUUUARRRR!, Jalapeno explodes (" + x + ", " + y + "), killing all zombies in lane " + y);
    }

    @Override
    public boolean isAttackingType() {
        return true;
    }

    public Plant clone(int x, int y, GameMap gameMap) {
        return new Jalapeno(x, y, gameMap);
    }
}

