package main.java.com.michaelvslalapan.ZombieClass;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;

public class NormalZombie extends Zombie {
    public NormalZombie() {
        super("Normal Zombie", 125, 100, 1, false, false);
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
    public void attackPlant() {
        
    }
    
    @Override
    public Zombie createZombie() {
        return new NormalZombie();
    }
}
