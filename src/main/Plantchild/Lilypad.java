package src.main.Plantchild;
import src.main.GameMap;
import src.main.AbstractClass.Plant;

public class Lilypad extends Plant {
    private GameMap gameMap;
    public Lilypad() {
        super("lilypad",25, 100, 0, 0, 0, 10, 0, 0); // Parameters: cost, health, attack_damage, attack_speed, range, cooldown, x, y
    }

    public Lilypad(int x, int y, GameMap gameMap) {
        this();
        this.x = x;
        this.y = y;
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // Lilypads do not perform any actions
    }

    public boolean isAttackingType() {
        return false;
    }

    public String getName() {
        return name;
    }
}

