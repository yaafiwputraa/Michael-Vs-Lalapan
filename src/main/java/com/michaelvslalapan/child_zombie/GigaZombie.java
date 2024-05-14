package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.GameMap;

public class GigaZombie extends Zombie {

    public GigaZombie(int x, int y, GameMap gameMap) {
        super("GigaZombie", 400, 500, 4, false, x, y, 5, gameMap);
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

