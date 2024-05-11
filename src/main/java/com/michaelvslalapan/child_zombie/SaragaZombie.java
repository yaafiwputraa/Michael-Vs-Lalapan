package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Map.GameMap;

public class SaragaZombie extends Zombie {
    private GameMap gameMap;

    public SaragaZombie() {
        super("Normal Zombie", 125, 100, 1, false, false, x, y);
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
    public void attackPlant(Plant plant){
        plant.reduceHealth(getAttackDamage());
    }
    
    @Override
    public Zombie createZombie() {
        return new SaragaZombie();
    }
}
