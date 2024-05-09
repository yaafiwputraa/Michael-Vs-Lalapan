package main.java.com.michaelvslalapan.AbstractClass;
import java.util.concurrent.*;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.Map.GameMap;


public abstract class Zombie extends GameEntity {
    private int speed;
    private boolean isZombieBergerak;
    private boolean isSlowed;
    private ScheduledExecutorService timer;
    private ScheduledFuture<?> scheduledFuture;

    public Zombie(String name, int health, int attack_damage, int attack_speed, GameMap tile, boolean isAquatic, int x, int y, int speed) {        
        super(name, health, attack_damage, attack_speed, tile, isAquatic, x, y);
        this.isSlowed = false; // scr default zombie ga slow kalo ga kena snowpea
        this.isZombieBergerak = true; // scr default zombie bergerak, akan stop sbntr kalo kena snowpea
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
    
   // @Override
    public void setAttackSpeed(int attack_speed) {
        this.attack_speed = attack_speed;
    }

    public int getAttackSpeed() {
        return this.attack_speed;
    }

    public void cekBergerak() {
        if (!isSlowed) { // Cek apkh !isSLowed = true --> yg brrti mmg benar bahwa isSlowed = false (zombie sdg tdk melambat)
            isZombieBergerak = true; 
        }
        else {
            isZombieBergerak = false; 
        }
    }
    public void attack_plant(Plant plant){
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

        // Efek ini hanya bertahan selama 3 detik kalo si zombie sdh tdk lg ditembak oleh snowpea.
            if (scheduledFuture != null && !scheduledFuture.isDone()) {
                scheduledFuture.cancel(false); // Batalin eksekusi yang tertunda jika ada
            }
            scheduledFuture = timer.schedule(this::finishedStruckBySnowPea, 3, TimeUnit.SECONDS);
        }
    }

    public void finishedStruckBySnowPea() {
        if (isSlowed) {
            isSlowed = false;
            setSpeed(getSpeed() * 2); // Mengembalikan speed 
            setAttackSpeed(getAttackSpeed() * 2); // Mengembalikan attack_speed
        }
    }
    
    public void shutdown() { // Mematikan executor ketika tdk lg dibutuhkan
            timer.shutdown();
    }

}