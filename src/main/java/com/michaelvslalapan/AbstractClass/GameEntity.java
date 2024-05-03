package main.java.com.michaelvslalapan.AbstractClass;

import java.util.Objects;

public abstract class GameEntity {
    protected String name;
    private int cost;
    protected int health;
    private int attack_damage;
    private int attack_speed;
    private int range;
    private int cooldown;

    public GameEntity(String name, Integer health, Integer attack_damage, Integer attack_speed) {
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
    }

    public int getCost() {
        return cost;
    }

    public int getAttackDamage() {
        return attack_damage;
    }

    public int getAttackSpeed(){
        return attack_speed;
    }

    public int getRange(){
        return range;
    }

    public int getCooldown(){
        return cooldown;
    }
}
