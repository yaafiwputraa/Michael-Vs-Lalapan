import java.util.List;
public class Repeater extends Plant {
    private GameMap gameMap;

    public Repeater(int x, int y, GameMap gameMap) {
        super(150, 100, 50, 3, -1, 20, x, y);  // Initialize with specified attributes
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // This method handles the attack mechanism of the Repeater
        // It attacks all zombies in the same column as the Repeater
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
        return true; // Repeater is an attacking type plant
    }
}
