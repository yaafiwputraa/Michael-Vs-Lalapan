package src.main.Zombiechild;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;
import src.main.Interface.ExplodeInterface;

public class JackInTheBoxZombie extends Zombie implements ExplodeInterface{
    private boolean hasExploded;
    private GameMap gameMap;
    public JackInTheBoxZombie(int x, int y) {
        super(300, 100, 1, x, y, false);
        this.hasExploded = false;
    }

    // @Override
    // public void move() {
    //     long currentTime = System.currentTimeMillis();
    //     if (currentTime >= nextMoveTime) {
    //         if (x > 0) {
    //             x--; // Move zombie one step to the left
    //         }
    //         updateNextMoveTime();
    //     }
    // }

    public void attack(Plant plant) {
        if (plant != null) {
            explode(plant);
        }
    }

    @Override
    public void explode(Plant plant) {
        int targetX = x;
        if (targetX >= 0) {
            plant.reduceHealth(plant.getHealth());
            hasExploded = true;
            gameMap.removeZombie(this);
            System.out.println("JackInTheBoxZombie exploded at (" + x + ", " + y + ")");
        }
    }

    public boolean hasExploded() {
        return hasExploded;
    }
}