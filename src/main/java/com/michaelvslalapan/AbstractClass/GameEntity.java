package main.java.com.michaelvslalapan.AbstractClass;


public abstract class GameEntity {
    protected String name;
    protected int health;
    private int attack_damage;
    private int attack_speed;
    private int cooldown;
    private int x, y;
    private boolean is_aquatic;

    public GameEntity(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic, int x, int y ) {
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.is_aquatic = is_aquatic;
        this.x = x;
        this.y = y;
    }

    // Getters

    public int getAttackDamage() {
        return attack_damage;
    }
    
    public int getHealth() { 
        return health; 
    }

    public int getAttackSpeed(){
        return attack_speed;
    }
    public int getCooldown(){
        return cooldown;
    }

    public int getX() { 
        return x; 
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY() { 
        return y; 
    }

    public boolean isAquatic(){
        return is_aquatic;
    }
}
