package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.GameMap;

public class ConeheadZombie extends Zombie {

    public ConeheadZombie(GameMap gameMap) {
        super("Conehead Zombie", 150, 50, 1, false, 0, 0, 1, gameMap);
    }

    @Override
    public void excecute() {
        if (isZombieBergerak()) {
            super.bergerak();
        }
        Plant target = gameMap.getPlant(getX(), getY());
        if (target != null) {
            attack_plant(target);
        }
    }
}
