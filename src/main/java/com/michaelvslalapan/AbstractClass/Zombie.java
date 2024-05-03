package main.java.com.michaelvslalapan.AbstractClass;

public abstract class Zombie extends GameEntity {
    protected boolean is_aquatic;

    public Zombie(String name, Integer health, Integer attack_damage, Integer attack_speed) {
        super(name, health, attack_damage, attack_speed);
    }

    public abstract boolean isAquatic();

    public String getName() {
            return name;
    }

    public abstract boolean isSlowed();
    
    public abstract Zombie createZombie();

    public void attackPlant() {
        int foundPlant = 0;
       // while (i.hasNext()) {
       // if (plant != null) {

        

    }
    

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        speed = 5;
    }

    public void bergerak() {
       // int i = 5;
       // for (i=0; i<=5; i++) {

    }
}
    

