import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.Map.GameMap;

public class Sunflower extends Plant {
    private GameMap gameMap;

    public Sunflower(int x, int y, GameMap gameMap) {
        super(50, 100, 0, 0, 0, 10, x, y);
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
