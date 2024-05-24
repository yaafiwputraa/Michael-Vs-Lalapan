package src.main.Plantchild;
import java.util.List;
import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class Snowpea extends Plant {
    private GameMap gameMap;
    public Snowpea(int x, int y, GameMap gameMap) {
        super("Snowpea", 175, 100, 25, 4, -1, 10, x, y, PlantType.SNOWPEA);  
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // Identify zombies in the same column
        List<Zombie> targets = gameMap.getZombiesInColumn(y);
        if (!targets.isEmpty()) {
            for (Zombie zombie : targets) {
                zombie.takeDamage(attackDamage);
                zombie.applySlowingEffect(3000);  
            }
        }
    }

    public boolean isAttackingType() {
        return true;  // Snowpea is an attacking type plant
    }

    @Override
    public Plant clone(int x, int y, GameMap gameMap) {
        return new Snowpea(x, y, gameMap);
    }
}