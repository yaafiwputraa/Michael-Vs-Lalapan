package main.java.com.michaelvslalapan.AbstractClass;

import main.java.com.michaelvslalapan.GameMap;

public abstract class GameEntity {
    protected String name;
    protected int health;
    protected int attack_damage;
    protected int attack_speed;
    protected boolean isAquatic;
    private int x;
    private int y;
    private GameMap gameMap;
    
    public GameEntity(String name, int health, int attack_damage, int attack_speed, boolean isAquatic, int x, int y, GameMap gameMap) {
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.isAquatic = isAquatic;
        this.x = x;
        this.y = y;
        this.gameMap = gameMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int damage) {
        this.health = this.health - damage;
    }

    public void setAttackDamage(int attack_damage) {
        this.attack_damage = attack_damage;
    }

    public int getAttackDamage() {
        return attack_damage;
    }

    public void setAttackSpeed(int attack_speed) {
        this.attack_speed = attack_speed;
    }

    public int getAttackSpeed(){
        return attack_speed;
    }

    public void setIsAquatic(boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    public boolean isAquatic() {
        return isAquatic;

    }
    
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

   
}
