package main.java.com.michaelvslalapan.ZombieClass;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;

public class NewspaperZombie extends Zombie {
    public NewspaperZombie() {
        super("GigaZombie", 150, 100, 1, false, false);
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
