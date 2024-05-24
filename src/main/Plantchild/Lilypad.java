package src.main.Plantchild;
import src.main.GameMap;
import src.main.AbstractClass.Plant;

public class Lilypad extends Plant {
    private GameMap gameMap;
    public Lilypad(int x, int y, GameMap gameMap) {
        super("lilypad",25, 100, 0, 0, 0, 10, x, y, PlantType.LILYPAD); // Parameters: cost, health, attack_damage, attack_speed, range, cooldown, x, y
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // Lilypads do not perform any actions
    }

    public boolean isAttackingType() {
        return false;
    }

    public Plant clone(int x, int y, GameMap gameMap) {
        return new Lilypad(x, y, gameMap);
    }
    
}

