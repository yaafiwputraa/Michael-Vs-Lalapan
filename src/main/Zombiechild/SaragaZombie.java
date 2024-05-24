package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class SaragaZombie extends Zombie {
    private boolean isDived;
    

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
        if (isDived) {
            isDived = false;
            takeDamage(attackDamage);

        }
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
            super.takeDamage(damage); 
        }
    }



   


    
    
}
