package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.Map.GameMap;

public class NormalZombie extends Zombie {
    private GameMap gameMap;

    public NormalZombie() {
        super(name, health, attack_damage, attack_speed, tile, isAquatic, x, y, speed);
        this.gameMap = gameMap;
    }
    @Override
    public boolean isAquatic() {
        return super.isAquatic();
    }

    @Override
    public boolean isSlowed() {
        return super.isSlowed();
    }
    @Override
    public void attackPlant(Plant plant){
        plant.reduceHealth(getAttackDamage());
    }
    
    @Override
    public Zombie createZombie() {
        return new NormalZombie("Normal Zombie", 125, 100, 1, gameMap, false, 0, 0, 1);
    }
    @Override
    public void excecute() {
        
    }
}
