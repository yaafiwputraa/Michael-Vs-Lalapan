package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class SaragaZombie extends Zombie {
    private boolean isDived;
    private long diveEndTime;

    public SaragaZombie(int x, int y) {
        super(125, 50, 1, x, y, true);
        this.isDived = false;
    }

    public void dive() {
        
        if (!isDived) {
            isDived = true;
            
        }
    }

    public void emerge() {
        // The zombie emerges when the dive time ends
        if (isDived) {
            isDived = false;
            takeDamage(attackDamage);

    }

    @Override
    public void attack(Plant plant) {
        if (plant != null && !isDived) {
            plant.reduceHealth(attackDamage);
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (!isDived) {
            super.takeDamage(damage);  // Only take damage if not dived
        }
    }



    // @Override
    // public void excecute() {
    //     if (isZombieBergerak()) {
    //         if (!isDived) {
    //             dive();
    //         } else if (isDived) {
    //             emerge();
    //         } else {
    //             super.bergerak();
    //         }
    //     }
    //     if (!isDived) {
    //         Plant target = gameMap.getPlant(getX(), getY());
    //         if (target != null) {
    //             attackPlant(target);
    //         }
    //     }
    // }



    
    
}
