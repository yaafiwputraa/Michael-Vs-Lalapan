package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.GameMap;

public class SaragaZombie extends Zombie {
    private boolean isDived;

    public SaragaZombie(GameMap gameMap) {
        super("Snorkel Zombie", 125, 50, 1, true, 0, 0, 1, gameMap);
        this.isDived = false;
    }

    public void dive() {
        if (!isDived) {
            System.out.println(getName() + " is diving!");
            isDived = true;
            // Adjust behavior for diving, e.g., avoid attacks
        }
    }

    public void emerge() {
        if (isDived) {
            System.out.println(getName() + " is emerging!");
            isDived = false;
            // Adjust behavior for emerging, e.g., become attackable
        }
    }

    @Override
    public void excecute() {
        if (isZombieBergerak()) {
            if (!isDived) {
                dive();
            } else if (isDived) {
                emerge();
            } else {
                super.bergerak();
            }
        }
        if (!isDived) {
            Plant target = gameMap.getPlant(getX(), getY());
            if (target != null) {
                attack_plant(target);
            }
        }
    }

    
    
}
