package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class BucketheadZombie extends Zombie {
    public BucketheadZombie(int x, int y) {
        super(300, 100, 1, x, y, false);
    }

    

    public void attack(Plant plant) {
        if (plant != null) {
            plant.reduceHealth(attackDamage);
        }
    }
}