package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.GameMap;
import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.GameMap;

public class BucketheadZombie extends Zombie {
    public BucketheadZombie(int x, int y, GameMap gameMap) {
        super("Buckethead Zombie", 300, 100, 1, false, x, y, 5, gameMap);
    }

    @Override
    
    public boolean isSlowed() {
        return super.isSlowed();
    }

    public static void main(String[] args) {
        GameMap gameMap = new GameMap(11, 6);
        BucketheadZombie mybucketheadzombie = new BucketheadZombie(3, 4, gameMap);
        mybucketheadzombie.displayZombie();
    }

}

