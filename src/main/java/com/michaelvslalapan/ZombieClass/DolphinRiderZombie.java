package main.java.com.michaelvslalapan.ZombieClass;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Interface.VaultingInterface;

public class DolphinRiderZombie extends Zombie implements VaultingInterface {
    private boolean hasVaulted;

    public DolphinRiderZombie() {
        super("Dolphin Rider Zombie", 175, 100, 1, true, false);
        this.hasVaulted = false;
    }

    @Override
    public boolean isAquatic() {
        // DolphinRiderZombie is aquatic, so this method always returns true
        return this.is_aquatic;
    }

    @Override
    public boolean isSlowed() {
        return this.is_slowed;
    }

    @Override
    public Zombie createZombie() {
        return new DolphinRiderZombie();
    }

    @Override
    public void vault() {
        // SEMENTARA
        this.hasVaulted = true;
    }

    @Override
    public boolean getVaulted() {
        return this.hasVaulted;
    }
}
