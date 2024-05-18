package main.java.com.michaelvslalapan.Runnable;
import main.java.com.michaelvslalapan.GameMap;
import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;

import java.util.ArrayList;
import java.util.List;

public class RunnableManageZombies implements Runnable {
    private GameMap gameMap;
    private final long checkInterval = 5000;  // Check every second for finer control over effects

    public RunnableManageZombies(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(checkInterval);  // Shorter pause interval for more frequent updates
                
                synchronized (gameMap) {  // Synchronize on gameMap to handle shared data safely
                    List<Zombie> zombies = new ArrayList<>(gameMap.getZombies());  // Work with a snapshot of zombies
                    for (Zombie zombie : zombies) {
                        zombie.update();  // Update zombie for any time-based changes like slow effects wearing off

                        if (zombie.isAlive()) {
                            Plant plant = gameMap.getPlant(zombie.getX() - 1, zombie.getY());  // Check one cell ahead for a plant
                            
                            if (plant != null) {
                                zombie.attack(plant);
                                if (plant.getHealth() <= 0) {
                                    gameMap.removePlant(plant);  // Remove dead plant
                                    System.out.println("Zombie at (" + zombie.getX() + ", " + zombie.getY() + ") destroyed the plant.");
                                }
                            }

                            // Move zombie forward if no plant is in the way or the plant is dead
                            long currentTime = System.currentTimeMillis();
                            if ((plant == null || plant.getHealth() <= 0) && currentTime >= zombie.getNextMoveTime()) {
                                zombie.move();
                                System.out.println("Zombie moves to (" + zombie.getX() + ", " + zombie.getY() + ")");
                            }
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Zombie management thread interrupted");
        }
    }
}
