package main.java.com.michaelvslalapan.child_plant;

import main.java.com.michaelvslalapan.AbstractClass.*;
import main.java.com.michaelvslalapan.GameMap;
public class Chomper extends Plant {

    private GameMap gameMap;
    public Chomper(int x, int y, GameMap gameMap) {
        super("Chomper",25, 100, 0, 20, 0, 10, x, y, gameMap); 
        this.gameMap = gameMap;
    }

    @Override
    public void performAction() {
        // TangleKelp drowns zombies directly in front of it and removes itself
        Zombie target = gameMap.getZombieAt(x + 1, y); // Get zombie right in front
        if (target != null) {
            gameMap.removeZombie(target); // Remove the zombie
        }
    }

    public boolean isAttackingType() {
        return true; // TangleKelp is an attacking type plant
    }

    @Override
    public void excecute() {
        
    }
}
