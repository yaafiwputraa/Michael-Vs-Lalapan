package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class JackInTheBoxZombie extends Zombie {
    public JackInTheBoxZombie(int x, int y) {
        super(100,50, 1, x, y, false);
    }


    public void attack(Plant plant) {
        if (plant != null) {
            plant.reduceHealth(attackDamage);
        }
    }
}