package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;

public class BucketheadZombie extends Zombie {
    public BucketheadZombie(int x, int y) {
        super("Buckethead Zombie", 300, 100, 1, false, x, y, 5);
    }

    public static void main(String[] args) {
        BucketheadZombie mybucketheadzombie = new BucketheadZombie(1, 1);
        mybucketheadzombie.displayZombie();
    }
}
