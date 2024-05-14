package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.GameMap;
import main.java.com.michaelvslalapan.AbstractClass.*;

public class ConeheadZombie extends Zombie {
    public ConeheadZombie(int x, int y, GameMap gameMap) {
        super("Conehead Zombie", 250, 100, 1,false, x, y, 5, gameMap);

    }

    @Override
    public boolean isSlowed() {
        return super.isSlowed();
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
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(11, 6);
        ConeheadZombie myconeheadzombie = new ConeheadZombie(3, 4, gameMap);
        myconeheadzombie.displayZombie();
    }
}
