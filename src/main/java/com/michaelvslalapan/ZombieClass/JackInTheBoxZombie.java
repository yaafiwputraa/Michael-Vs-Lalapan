package main.java.com.michaelvslalapan.ZombieClass;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Interface.Explode;

public class JackInTheBoxZombie extends Zombie implements Explode {
    private boolean hasExploded;

    public JackInTheBoxZombie() {
        super("JackInTheBox", 125, 1000, 1, false, false);
        this.hasExploded = false;
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
        return new JackInTheBoxZombie();
    }

    @Override
    public boolean isExploded() {
        return this.hasExploded;
    }

    public void explode() {
        this.hasExploded = true;
    }
}
