package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;

public class DuckyTubeZombie extends Zombie {
    public DuckyTubeZombie() {
        super("Ducky Tube Zombie", 100, 100, 1, true, false,0,0);
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
        return new DuckyTubeZombie();
    }

    @Override
    public void attackPlant(Plant plant){
        plant.reduceHealth(getAttackDamage());
    }
}
