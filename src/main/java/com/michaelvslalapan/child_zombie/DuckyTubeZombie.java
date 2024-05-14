

package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.GameMap;

public class DuckyTubeZombie extends Zombie {

    public DuckyTubeZombie(int x, int y, GameMap gameMap) {
        super("DuckyTubeZombie", 100, 100, 1, true, x, y, 5, gameMap);
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
