package src.main.AbstractClass;

public abstract class Zombie {
    protected int health;
    protected int attackDamage;
    protected int attackSpeed;
    protected double movementSpeedFactor = 1.0;
    protected int x, y;
    protected boolean alive = true;
    protected boolean isAquatic;
    protected long nextMoveTime;
    protected long slowEffectEndTime;

    public Zombie(int health, int attackDamage, int attackSpeed, int x, int y, boolean isAquatic) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.x = x;
        this.y = y;
        this.isAquatic = isAquatic;
        this.nextMoveTime = System.currentTimeMillis();
    }

    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime >= nextMoveTime) {
            if (x > 0) {
                x--; // Move zombie one step to the left
            }
            updateNextMoveTime();
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            alive = false;
        }
    }

    public abstract void attack(Plant plant);

    public void applySlowingEffect(int duration) {
        if (System.currentTimeMillis() > slowEffectEndTime) {
            movementSpeedFactor = 0.5;
            slowEffectEndTime = System.currentTimeMillis() + duration;
            updateNextMoveTime();
        }
    }

    protected void updateNextMoveTime() {
        nextMoveTime = System.currentTimeMillis() + (long) (5000 / movementSpeedFactor);
    }

    public long getNextMoveTime() {
        return nextMoveTime;
    }


    public boolean isAlive() {
        return alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void update() {
        if (System.currentTimeMillis() > slowEffectEndTime && movementSpeedFactor < 1.0) {
            movementSpeedFactor = 1.0;
            updateNextMoveTime();
        }
    }
}
