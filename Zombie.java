import main.java.com.michaelvslalapan.AbstractClass.Plant;

public class Zombie {
    private int health;
    private int attackDamage;
    private int attackSpeed;
    private int x;
    private int y;

    public Zombie(int health,int attackDamage, int attackSpeed , int x, int y) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (x > 0) {
            x--; // Move zombie one step to the left
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void attack(Plant plant){
        plant.reduceHealth(attackDamage);
    }

}
