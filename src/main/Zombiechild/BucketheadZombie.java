package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class BucketheadZombie extends Zombie {
    public BucketheadZombie(int x, int y) {
        super(300, 100, 1, x, y, false);
    }

    // @Override
    // public void move() {
    //     long currentTime = System.currentTimeMillis();
    //     if (currentTime >= nextMoveTime) {
    //         if (x > 0) {
    //             x--; // Move zombie one step to the left
    //         }
    //         updateNextMoveTime();
    //     }
    // }

    public void attack(Plant plant) {
        if (plant != null) {
            plant.reduceHealth(attackDamage);
        }
    }
}