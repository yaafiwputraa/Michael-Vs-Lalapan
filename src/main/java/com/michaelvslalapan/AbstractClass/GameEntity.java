package main.java.com.michaelvslalapan.AbstractClass;


import main.java.com.michaelvslalapan.Interface.Excecute;
import main.java.com.michaelvslalapan.Map.GameMap;



public abstract class GameEntity implements Excecute {
    protected String name;
    protected int health;
    protected int attack_damage;
    protected int attack_speed;
    protected GameMap tile;
    protected boolean isAquatic;
    private int x;
    private int y;
    
    public GameEntity(String name, int health, int attack_damage, int attack_speed, GameMap tile, boolean isAquatic, int x, int y) {
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.tile = tile;
        this.isAquatic = isAquatic;
        this.x = x;
        this.y = y;
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

    public void setTile(GameMap tile) {
        this.tile = tile;
    }

    public GameMap getTile() {
        return tile;
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

    public abstract void excecute();
}
