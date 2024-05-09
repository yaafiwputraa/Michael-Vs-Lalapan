package main.java.com.michaelvslalapan.AbstractClass;

public abstract class Zombie extends GameEntity {
    protected boolean is_aquatic;
    protected boolean is_slowed;
    private int speed;

    public Zombie(String name, Integer health, Integer attack_damage, Integer attack_speed, boolean is_aquatic, boolean is_slowed, int x, int y) {
        super(name, health, attack_damage, attack_speed, is_aquatic, x, y);
        this.is_aquatic = is_aquatic;
        this.is_slowed = is_slowed;
    }

    public abstract boolean isAquatic();

    public String getName() {
        return name;
    }

    public abstract boolean isSlowed();
    
    public abstract Zombie createZombie();

    public abstract void attackPlant();
    

    public void attack(Plant plant){
        plant.reduceHealth(getAttackDamage());
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void bergerak() {
        if (getX() > 0) {
            setX(getX() - 1); // Move zombie one step to the left
        }
    }
}
