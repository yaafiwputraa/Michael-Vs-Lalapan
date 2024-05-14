package main.java.com.michaelvslalapan.child_zombie;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.GameMap;

public class NewspaperZombie extends Zombie {

    private boolean enraged;

    public NewspaperZombie(GameMap gameMap) {
        super("Newspaper Zombie", 100, 50, 1, false, 0, 0, 1, gameMap); // Original attributes retained
        this.enraged = false;
    }

    public void becomeEnraged() {
        if (!enraged) {
            setSpeed(getSpeed() * 2);
            enraged = true;
            System.out.println(getName() + " has become enraged!");
        }
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (getHealth() <= 50 && !enraged) {
            becomeEnraged();
        }
    }

    @Override
    public void excecute() {
        if (isZombieBergerak()) {
            super.bergerak();
        }
        Plant target = gameMap.getPlant(getX(), getY());
        if (target != null) {
            attack_plant(target);
        }
    }
}
