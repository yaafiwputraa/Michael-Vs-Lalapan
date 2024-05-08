public abstract class Plant {
    protected int cost;
    protected int health;
    protected int attackDamage;
    protected int attackSpeed;  
    protected int range;
    protected int cooldown;  
    protected int x, y;  
    protected long nextActionTime;

    public Plant(int cost, int health, int attackDamage, int attackSpeed, int range, int cooldown, int x, int y) {
        this.cost = cost;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.cooldown = cooldown;
        this.x = x;
        this.y = y;
        this.nextActionTime = System.currentTimeMillis() + (attackSpeed * 1000);
    }

    public abstract void performAction();  
    public abstract boolean isAttackingType();  

    // Getters 
    public int getCost() { return cost; }
    public int getHealth() { return health; }
    public int getAttackDamage() { return attackDamage; }
    public int getAttackSpeed() { return attackSpeed; }
    public int getRange() { return range; }
    public int getCooldown() { return cooldown; }
    public int getX() { return x; }
    public int getY() { return y; }

    public void reduceHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public long getNextActionTime() {
        return nextActionTime;
    }

    public void setNextActionTime(long nextActionTime) {
        this.nextActionTime = nextActionTime;
    }
}
