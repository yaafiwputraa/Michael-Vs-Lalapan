package main.java.com.michaelvslalapan.ZombieClass;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;

public class DuckyTubeZombie extends Zombie {
    public DuckyTubeZombie() {
        super("Ducky Tube Zombie", 100, 100, 1, true, false);
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
}
