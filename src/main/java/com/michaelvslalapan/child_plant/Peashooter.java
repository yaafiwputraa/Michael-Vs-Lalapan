package main.java.com.michaelvslalapan.child_plant;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.GameMap;

public class Peashooter extends Plant {

    public Peashooter(int x, int y, GameMap gameMap) {
        super("Peashooter", 100, 25, 4, false, x, y, 100, -1, 10, gameMap);
    }

    @Override
    public void performAction() {
        // Attack all zombies in the same column
        for (Zombie zombie : gameMap.getZombiesInColumn(y)) {
            if (zombie.isAlive()) {
                zombie.takeDamage(getAttackDamage());
            }
        }
    }
    @Override
    public boolean isAttackingType() {
        return true;
    }

    @Override
    public void reduceHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(11, 6);
        Peashooter myPeashooter= new Peashooter(3, 4, gameMap);
        myPeashooter.displayPlant();
    }
}
