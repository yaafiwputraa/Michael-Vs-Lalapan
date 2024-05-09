package main.java.com.michaelvslalapan.ZombieClass;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Map.GameMap;

public class NormalZombie extends Zombie {
    private GameMap gameMap;

    public NormalZombie(int x, int y, GameMap gameMap) {
        super("Normal Zombie", 125, 100, 1, false, false,x, y);
        this.gameMap = gameMap;
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
        return new NormalZombie();
    }
}
