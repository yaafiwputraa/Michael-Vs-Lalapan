package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Interface.VaultingInterface;
import main.java.com.michaelvslalapan.GameMap;

public class PoleVaultingZombie extends Zombie implements VaultingInterface {
    private boolean vaulted;

    public PoleVaultingZombie(int x, int y, GameMap gameMap) {
        super("Pole Vaulting Zombie", 175, 50, 1, false, x, y, 5, gameMap); // Assuming speed is 2 for Pole Vaulting Zombie
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
