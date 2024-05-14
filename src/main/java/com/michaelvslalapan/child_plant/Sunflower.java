package main.java.com.michaelvslalapan.child_plant;
import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.GameMap;

import java.util.Timer;
import java.util.TimerTask;

public class Sunflower extends Plant {


    public Sunflower(int x, int y, GameMap gameMap) {
        super("Sunflower", 100, 0, 0, false, x, y, 50, 0, 10, gameMap);
        //public Plant(String name, int health, int attack_damage, int attack_speed, GameMap tile, boolean isAquatic, int x, int y, int cost, int range, int cooldown) {
    }

    @Override
    public void performAction() {
        gameMap.addSuns(25);
    }
    @Override
    public boolean isAttackingType() {
        return false;
    }
    
    @Override
    public void decreaseHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public void generateSunflowerProduction() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                performAction();
            }
        }, 0, 3000);
    }
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(11, 6);
        Sunflower mySunflower = new Sunflower(2, 2, gameMap);
        mySunflower.displayPlant();
    }
    
   
}
