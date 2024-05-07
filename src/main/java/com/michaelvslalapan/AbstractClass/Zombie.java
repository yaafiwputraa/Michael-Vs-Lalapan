package main.java.com.michaelvslalapan.AbstractClass;

public abstract class Zombie extends GameEntity {
    protected boolean is_aquatic;
    protected boolean is_slowed;

    public Zombie(String name, Integer health, Integer attack_damage, Integer attack_speed, boolean is_aquatic, boolean is_slowed) {
        super(name, health, attack_damage, attack_speed);
        this.is_aquatic = is_aquatic;
        this.is_slowed = is_slowed;
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
       for (int i = 0; i <= 5; i++){
        // this.posisi += petak
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
       }

    }
}
    

