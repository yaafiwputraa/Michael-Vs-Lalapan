package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Interface.VaultingInterface;

public class PoleVaultingZombie extends Zombie implements VaultingInterface {
    private boolean hasVaulted;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false, false, 0, 0);
        this.hasVaulted = false;
    }

    @Override
    public boolean isAquatic() {
        return this.is_aquatic;
    }

    @Override
    public boolean isSlowed() {
        return this.is_slowed;
    }

    @Override
    public Zombie createZombie() {
        return new PoleVaultingZombie();
    }

    @Override
    public void attackPlant(Plant plant){
        plant.reduceHealth(getAttackDamage());
    }

    @Override
    public void vault() {
        if (getX() > 0) {
            if 
            setX(getX() - 2); // Move zombie one step to the left
        }
        this.hasVaulted = true;
    }


    @Override
    public boolean getVaulted() {
        return this.hasVaulted;
    }
}
