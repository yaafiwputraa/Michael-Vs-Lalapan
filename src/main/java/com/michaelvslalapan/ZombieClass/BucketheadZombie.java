package main.java.com.michaelvslalapan.ZombieClass;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;

public class BucketheadZombie extends Zombie {
    public BucketheadZombie() {
        super("Buckethead Zombie", 300, 100, 1, false, false);
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
        return new BucketheadZombie();
    }
}
