package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;
import src.main.Interface.VaultingInterface;

public class PoleVaultingZombie extends Zombie implements VaultingInterface {
    private boolean hasVaulted;
    
    public PoleVaultingZombie(int x, int y) {
        super(175, 100, 1, x, y, false);
        this.hasVaulted = false;
    }

    
    public void attack(Plant plant) {
        if (plant != null) {
            if (!hasVaulted()) {
                int targetX =this.x - 1;
                if (targetX >= 0) {
                    vault(plant);                
                }
            } else {     
                plant.reduceHealth(attackDamage);
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
            System.out.println("PoleVaultingZombie vaulted over plant at (" + targetX + ", " + y + ")");
        }
    }   

    @Override
    public boolean hasVaulted() {
        return hasVaulted;
    }
}
