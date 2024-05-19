package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;
import src.main.Interface.VaultingInterface;

public class DolphinRiderZombie extends Zombie implements VaultingInterface {
    private boolean hasVaulted;

    public DolphinRiderZombie(int x, int y) {
        super(175, 100, 1, x, y, true);
        this.hasVaulted = false;
    }

    
    public void attack(Plant plant) {
        if (plant != null) {
            System.out.println("tests1");
            if (!hasVaulted()) {
                int targetX =this.x - 1;
                if (targetX >= 0) {
                    vault(plant);                
                }
            } else {     
                plant.reduceHealth(attackDamage);
                // if (!plant.isAlive()) {
                //     gameMap.removePlant(plant);
                // }
            }
        }
    }

    @Override
    public void vault(Plant plant) {
        int targetX = x - 1;
        if (targetX >= 0) {
            plant.reduceHealth(plant.getHealth());
            this.x = targetX-1;
            hasVaulted = true;
            System.out.println("Dolphin Rider Zombie vaulted over plant at (" + targetX + ", " + y + ")");
        }
    }   

    @Override
    public boolean hasVaulted() {
        return hasVaulted;
    }
}
