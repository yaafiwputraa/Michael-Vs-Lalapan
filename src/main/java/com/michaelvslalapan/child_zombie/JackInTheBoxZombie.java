package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.GameMap;
import main.java.com.michaelvslalapan.Interface.Explode;


public class JackInTheBoxZombie extends Zombie implements Explode {
    private boolean exploded;

    public JackInTheBoxZombie(int x, int y, GameMap gameMap) {
        super("Jack-in-the-Box Zombie", 150, 50, 1, false, x, y, 5, gameMap);
        this.exploded = false;
    }

    @Override
    public boolean isExploded() {
        return exploded;
    }

    public void explode() {
        // Implement explosion logic here
        this.exploded = true;
       
    }

    @Override
    public void excecute() {
        if (isZombieBergerak() && !isExploded()) {
            super.bergerak();
        }
        Plant target = gameMap.getPlant(getX(), getY());
        if (target != null && !isExploded()) {
            explode();
        }
    }
}
