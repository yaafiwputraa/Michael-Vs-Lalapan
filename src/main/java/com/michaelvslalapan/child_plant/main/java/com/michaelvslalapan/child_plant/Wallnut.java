package main.java.com.michaelvslalapan.child_plant;

import main.java.com.michaelvslalapan.AbstractClass.*;
import main.java.com.michaelvslalapan.GameMap;

public class Wallnut extends Plant {
    private GameMap gameMap;
    public Wallnut(int x, int y, GameMap gameMap) {
        super("wallnut",50, 1000, 0, 0, 0, 10, x, y); // Parameters: cost, health, attack_damage, attack_speed, range, cooldown, x, y
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // Lilypads do not perform any actions
    }

    public boolean isAttackingType() {
        return false;
    }
}
