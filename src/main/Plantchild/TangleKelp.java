package src.main.Plantchild;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class TangleKelp extends Plant {

    private GameMap gameMap;
    public TangleKelp(int x, int y, GameMap gameMap) {
        super("tanglekelp",25, 100, 0, 0, 0, 10, x, y, PlantType.TANGLEKELP); // Assuming parameters for Plant are cost, health, attackDamage, attackSpeed, range, cooldown, x, y
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        
        Zombie target = gameMap.getZombieAt(x + 1, y); 
        if (target != null) {
            gameMap.removeZombie(target); 
            gameMap.removePlant(this); 
            System.out.println("TangleKelp at (" + x + ", " + y + ") drowned a zombie at (" + (x + 1) + ", " + y + ")");
        }
    }

    public boolean isAttackingType() {
        return true; 
    }

    public Plant clone(int x, int y, GameMap gameMap) {
        return new TangleKelp(x, y, gameMap);
    }
}

