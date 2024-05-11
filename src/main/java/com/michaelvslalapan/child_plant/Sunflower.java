package main.java.com.michaelvslalapan.child_plant;
import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.Map.GameMap;

public class Sunflower extends Plant {
    private GameMap tile;

    public Sunflower(int x, int y, GameMap tile) {
        super("Sunflower", 100, 0, 0, tile, false, x, y, 50, 0, 10);
        //public Plant(String name, int health, int attack_damage, int attack_speed, GameMap tile, boolean isAquatic, int x, int y, int cost, int range, int cooldown) {
       
    }

    @Override
    public void performAction() {
        tile.addSuns(25);
    }

    public boolean isAttackingType() {
        return false;
    }
    
}
