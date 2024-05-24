package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class DuckyTubeZombie extends Zombie {
    public DuckyTubeZombie(int x, int y) {
        super(100, 100, 1, x, y, true);
    }

    

    public void attack(Plant plant) {
        if (plant != null) {
            plant.reduceHealth(attackDamage);
            System.out.println("ducky tube menyerang");
        }
    }
}