package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Interface.VaultingInterface;
import main.java.com.michaelvslalapan.GameMap;

public class DolphinRiderZombie extends Zombie implements VaultingInterface {
    private boolean vaulted;

    public DolphinRiderZombie(int x, int y, GameMap gameMap) {
        super("Dolphin Rider Zombie", 125, 50, 1, true, x, y, 5, gameMap); // Assuming speed is 2 for Dolphin Rider
        this.vaulted = false;
    }

    @Override
    public void vault() {
        if (!vaulted && gameMap.getPlant(getX(), getY()) != null) {
            setX(getX() + 1); // Move forward by one tile
            this.vaulted = true;
        }
    }

    @Override
    public boolean getVaulted() {
        return vaulted;
    }

    @Override
    public void excecute() {
        if (isZombieBergerak()) {
            if (!vaulted) {
                vault();
            } else {
                super.bergerak();
            }
        }
        Plant target = gameMap.getPlant(getX(), getY());
        if (target != null) {
            attack_plant(target);
        }
    }
}
