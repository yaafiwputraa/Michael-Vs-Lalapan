package main.java.com.michaelvslalapan.ZombieClass;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;

public class GigaZombie extends Zombie {
    public GigaZombie() {
        super("GigaZombie", 400, 500, 4, false, false);
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
        return new GigaZombie();
    }

    @Override
    public void attackPlant() {
        
    }
}
