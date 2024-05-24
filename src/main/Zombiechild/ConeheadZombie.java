package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class ConeheadZombie extends Zombie {
    public ConeheadZombie(int x, int y) {
        super(250, 100, 1, x, y, false);
    }

    public void attack(Plant plant) {
        if (plant != null) {
            plant.reduceHealth(attackDamage);
        }
    }
}