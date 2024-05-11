package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Interface.Explode;

public class JackInTheBoxZombie extends Zombie implements Explode {
    private boolean hasExploded;

    public JackInTheBoxZombie() {
        super("JackInTheBox", 125, 1000, 1, false, false,0,0);
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

    @Override
    public void attackPlant(Plant plant){
        plant.reduceHealth(getAttackDamage());
    }

    public void explode() {
        this.hasExploded = true;
    }
}
