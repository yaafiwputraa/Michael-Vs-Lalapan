package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class NewspaperZombie extends Zombie {
    public NewspaperZombie(int x, int y) {
        super( 150, 100, 1, x, y, false);
    }
    
    
    
    public void rage(){
        if (health < 100){
            movementSpeedFactor = 1.2;
        }
    }

    @Override
    public void attack(Plant plant) {
        if (plant != null) {
            plant.reduceHealth(attackDamage);

        }
    }
    
}
