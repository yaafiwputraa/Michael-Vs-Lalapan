package main.java.com.michaelvslalapan.ZombieClass;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Interface.VaultingInterface;

public class PoleVaultingZombie extends Zombie implements VaultingInterface {
    private boolean hasVaulted;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false, false);
        this.hasVaulted = false;
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
        return new PoleVaultingZombie();
    }

    @Override
    public void attackPlant() {
        
    }

    @Override
    public void vault() {
        // INI NUNGGU TILE AMA GAMEMAP YAA
        // NANTI DISESUAIIN
        this.hasVaulted = true;
    }


    @Override
    public boolean getVaulted() {
        return this.hasVaulted;
    }
}
