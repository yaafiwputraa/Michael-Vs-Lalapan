package main.java.com.michaelvslalapan.AbstractClass;

import java.util.concurrent.*;
import main.java.com.michaelvslalapan.GameMap;
import main.java.com.michaelvslalapan.TileType;

public abstract class Zombie extends GameEntity {
    private int speed;
    private boolean isZombieBergerak;
    private boolean isSlowed;
    private ScheduledExecutorService timer;
    private ScheduledFuture<?> scheduledFuture;

    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean isAquatic, int x, int y, int speed, GameMap gameMap) {
        super(name, health, attack_damage, attack_speed, isAquatic, x, y, gameMap);
        this.isSlowed = false; // Default: zombie is not slowed
        this.isZombieBergerak = true; // Default: zombie is moving
        this.speed = speed;
        timer = Executors.newSingleThreadScheduledExecutor();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isZombieBergerak() {
        return isZombieBergerak;
    }

    public void setIsZombieBergerak() {
        isZombieBergerak = !isZombieBergerak;
    }

    public boolean isSlowed() {
        return isSlowed;
    }

    public void setIsSlowed() {
        isSlowed = !isSlowed;
    }

    @Override
    public void setAttackSpeed(int attack_speed) {
        this.attack_speed = attack_speed;
    }

    @Override
    public int getAttackSpeed() {
        return this.attack_speed;
    }

    public void cekBergerak() {
        if (!isSlowed) {
            isZombieBergerak = true;
        } else {
            isZombieBergerak = false;
        }
    }

    public void attackPlant(Plant plant) {
        plant.decreaseHealth(getAttackDamage());
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public void bergerak() {
        if (getX() > 0) {
            setX(getX() - 1); // Move zombie one step to the left
        }
    }

    public void struckBySnowPea() {
        if (!isSlowed) {
            isSlowed = true;
            setSpeed(getSpeed() / 2);
            setAttackSpeed(getAttackSpeed() / 2);

            // Effect lasts for 3 seconds unless hit again
            if (scheduledFuture != null && !scheduledFuture.isDone()) {
                scheduledFuture.cancel(false); // Cancel any pending executions
            }
            scheduledFuture = timer.schedule(this::finishedStruckBySnowPea, 3, TimeUnit.SECONDS);
        }
    }

    public void finishedStruckBySnowPea() {
        if (isSlowed) {
            isSlowed = false;
            setSpeed(getSpeed() * 2); // Restore speed
            setAttackSpeed(getAttackSpeed() * 2); // Restore attack speed
        }
    }

    public void shutdown() { // Shutdown executor when no longer needed
        timer.shutdown();
    }

    public void displayZombie() {
        System.out.println("Name: " + getName());
        System.out.println("Health: " + getHealth());
        System.out.println("Attack damage: " + getAttackDamage());
        System.out.println("Attack speed: " + getAttackSpeed());
        System.out.println("Is Aquatic: " + isAquatic());
        System.out.println("Is Slowed: " + isSlowed());
    }

    @Override
    public boolean isAlive() {
        return getHealth() > 0;
    }

    @Override
    public void execute() {
        if (isZombieBergerak()) {
            bergerak();
        }
        Plant target = gameMap.getPlant(getX(), getY());
        if (target != null) {
            attackPlant(target);
        }
    }
}
