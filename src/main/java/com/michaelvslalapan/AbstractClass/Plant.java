package main.java.com.michaelvslalapan.AbstractClass;

import main.java.com.michaelvslalapan.Map.GameMap;

public abstract class Plant extends GameEntity {
    private int cost;
    private int range;
    private int cooldown;  
    private long prev_time; 
    private long nextActionTime;

    public Plant(String name, Float health, Float attack_damage, Float attack_speed, GameMap tile, boolean isAquatic, int x, int y, int cost, int range, int cooldown) {
        super(name, health, attack_damage, attack_speed, tile, isAquatic, x, y);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.prev_time = System.currentTimeMillis();
        this.nextActionTime = (long) (System.currentTimeMillis() + (attack_speed * 1000)); // delay tanaman nyerang
    }

    //public abstract void performAction();  
    //public abstract boolean isAttackingType();  

    public int getCost() {
        return cost;
    }
    
    public int getRange() { 
        return range; 
    }

    public int getCooldown() { 
        return cooldown; 
    }

    public long getNextActionTime() {
        return nextActionTime;
    }

    public void reduceHealth(Float damage) {
        health -= damage;
        if (health <= 0) {
            health = 0f;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setNextActionTime(long nextActionTime) {
        this.nextActionTime = nextActionTime;
    }

    public boolean isAbleToPlant() {
        long curr_time;
        long duration;
        boolean check = true;

        curr_time = System.currentTimeMillis();
        duration = curr_time - prev_time;
        
        if (duration < cooldown) {
            check = false; // blm bs ditanam kembali krn timer cooldown blm berakhir (masi dlm proses cooldown)
        }
        return check;
    }

    public void mapPlantCoordinate(int x, int y) throws Exception {
        if (!isAbleToPlant()) {
            throw new Exception(name + " belum dapat ditanam kembali sebelum timer cooldown berakhir.");
        }
       // else if (isAbleToPlant()) {
        prev_time = System.currentTimeMillis();
        System.out.printf("%s telah ditanam pada (%d, %d)\n", name, x, y);
    }
    
}

