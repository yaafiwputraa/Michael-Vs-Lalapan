package src.main.AbstractClass;

import src.main.PlantType;

public abstract class Plant {
    protected String name;
    protected int cost;
    protected int health;
    protected int attackDamage;
    protected int attackSpeed;  
    protected int range;
    protected int cooldown;  
    protected int remainingCooldown;
    protected int x, y;  
    protected long nextActionTime;
    private PlantType type;

    public Plant(String name, int cost, int health, int attackDamage, int attackSpeed, int range, int cooldown, int x, int y, PlantType type) {
        this.name = name;
        this.cost = cost;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.cooldown = cooldown;
        this.remainingCooldown = 0;
        this.x = x;
        this.y = y;
        this.nextActionTime = System.currentTimeMillis() + (attackSpeed * 1000);
        this.type = type;
    }

    public PlantType getType() {
        return type;
    }

    public abstract Plant clone(int x, int y, GameMap gameMap);

    public abstract void performAction();  
    public abstract boolean isAttackingType();  

    // Getters 
    public int getCost() { return cost; }
    public int getHealth() { return health; }
    public int getAttackDamage() { return attackDamage; }
    public int getAttackSpeed() { return attackSpeed; }
    public int getRange() { return range; }
    public int getCooldown() { return cooldown; }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getName() { return name; }

    public void reduceHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
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
}

