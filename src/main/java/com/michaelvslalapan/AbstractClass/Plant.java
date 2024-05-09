package main.java.com.michaelvslalapan.AbstractClass;

public abstract class Plant extends GameEntity {
    private int cost;
    protected int range;
    protected int cooldown;  
    protected long nextActionTime;

    public Plant(String name, int cost, int health, int attack_damage, int attack_speed, boolean is_aquatic, int x, int y, int range, int cooldown) {
        super(name, health, attack_damage, attack_speed, is_aquatic, x, y);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.nextActionTime = System.currentTimeMillis() + (attack_speed * 1000);
    }

    public abstract void performAction();  
    public abstract boolean isAttackingType();  

    // Getters 
    public int getRange() { 
        return range; 
    }

    public int getCooldown() { 
        return cooldown; 
    }

    public long getNextActionTime() {
        return nextActionTime;
    }
    public int getCost(){
        return cost;
    }
    public void reduceHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setNextActionTime(long nextActionTime) {
        this.nextActionTime = nextActionTime;
    }
}