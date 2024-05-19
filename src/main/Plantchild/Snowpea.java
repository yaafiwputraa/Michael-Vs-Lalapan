package src.main.Plantchild;
import java.util.List;
import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class Snowpea extends Plant {
    private GameMap gameMap;
    public Snowpea() {
        super("Snowpea", 175, 100, 25, 4, -1, 10, 0, 0);  // Initialize with the constructor of the Plant class
    }

    public Snowpea(int x, int y, GameMap gameMap) {
        this();
        this.x = x;
        this.y = y;
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // Identify zombies in the same column
        List<Zombie> targets = gameMap.getZombiesInColumn(y);
        if (!targets.isEmpty()) {
            for (Zombie zombie : targets) {
                zombie.takeDamage(attackDamage);
                zombie.applySlowingEffect(3000);  // Apply slowing effect for 3000 milliseconds (3 seconds)
            }
        }
    }

    public boolean isAttackingType() {
        return true;  // Snowpea is an attacking type plant
    }

    public String getName() {
        return name;
    }
}