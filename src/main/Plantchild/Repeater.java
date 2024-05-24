package src.main.Plantchild;
import java.util.List;
import src.main.AbstractClass.Plant;
import src.main.GameMap;
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
        return true; 
    }

    @Override
    public Plant clone(int x, int y, GameMap gameMap) {
        return new Repeater(x, y, gameMap);
    }
}

