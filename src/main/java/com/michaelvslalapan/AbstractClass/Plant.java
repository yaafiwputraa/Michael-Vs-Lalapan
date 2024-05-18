package main.java.com.michaelvslalapan.AbstractClass;

import main.java.com.michaelvslalapan.GameMap;

public abstract class Plant extends GameEntity {
    private int cost;
    private int range;
    private int cooldown;  
    private long prev_time; 
    private long nextActionTime;
    protected int remainingCooldown;

    public Plant(String name, int health, int attack_damage, int attack_speed, boolean isAquatic, int x, int y, int cost, int range, int cooldown, GameMap gameMap) {
        super(name, health, attack_damage, attack_speed, isAquatic, x, y, gameMap);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.prev_time = System.currentTimeMillis();
        this.nextActionTime = (long) (System.currentTimeMillis() + (attack_speed * 1000)); // delay tanaman nyerang
    }

    public abstract void performAction();  
    public abstract boolean isAttackingType();  

    public int getCost() {
        return cost;
    }
    
    public int getRange() { 
        return range; 
    }

    public int getCooldown() { 
        return cooldown; 
    }

    @Override
    public boolean isAlive(){
        return health > 0;
    }

    public void reduceHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
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
    public long getNextActionTime() {
        return nextActionTime;
    }

    public void setNextActionTime(long nextActionTime) {
        this.nextActionTime = nextActionTime;
    }

    public void reduceCooldown() {
        if (remainingCooldown > 0) {
            remainingCooldown--;
        }
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void resetCooldown() {
        remainingCooldown = cooldown;
    }

    public boolean isAvailable() {
        return remainingCooldown == 0;
    }

    public void use(){
        this.remainingCooldown = this.cooldown;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //public abstract void excecute();
    public void displayPlant() {
        System.out.println("Name: " + getName());
        System.out.println("Cost: " + getCost());
        System.out.println("Health: " + getHealth());
        System.out.println("Attack damage: " + getAttackDamage());
        System.out.println("attack speed: " + getAttackSpeed());
        System.out.println("range: " + getRange());
        System.out.println("cooldown: " + getCooldown());
        System.out.println("isAquatic: " + isAquatic());
    }
    
}

