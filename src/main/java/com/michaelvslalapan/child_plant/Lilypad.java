package main.java.com.michaelvslalapan.child_plant;

import main.java.com.michaelvslalapan.GameMap;
import main.java.com.michaelvslalapan.AbstractClass.Plant;

public class Lilypad extends Plant{


    public Lilypad(int x, int y) {
        super("Lilypad", 100, 0, 0, true, x, y, 25, 0, 10);
    }

    
    //public void displayLilypad() {
        //public static void main(String[] args) {
        // Peashooter myPeashooter = new Peashooter("Peashooter", 100, 100, 25, 4, -1, 10);
        /*System.out.println("Name: " + getName());
        System.out.println("Cost: " + getCost());
        System.out.println("Health: " + getHealth());
        System.out.println("Attack damage: " + getAttackDamage());
        System.out.println("attack speed: " + getAttackSpeed());
        System.out.println("range: " + getRange());
        System.out.println("cooldown: " + getCooldown());
        System.out.println("isAquatic: " + isAquatic()); */
        

    

    // testing
    public static void main(String[] args) {
    
        Lilypad myLilypad = new Lilypad(3, 4);
        myLilypad.displayPlant();
    }


}


