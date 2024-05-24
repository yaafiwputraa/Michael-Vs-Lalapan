package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class GigaZombie extends Zombie {
    public GigaZombie(int x, int y) {
        super(400,400, 4, x, y, false);
    }

   

    public void attack(Plant plant) {
        if (plant != null) {
            plant.reduceHealth(attackDamage);
        }
    }
}