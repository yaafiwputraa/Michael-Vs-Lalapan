package src.main.Runnable;

import java.util.ArrayList;
import java.util.List;

import src.main.GameMap;
import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

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
                Thread.sleep(checkInterval);  
                
                synchronized (gameMap) {  // Synchronize on gameMap to handle shared data safely
                    List<Zombie> zombies = new ArrayList<>(gameMap.getZombies());  // Work with a snapshot of zombies
                    for (Zombie zombie : zombies) {
                        zombie.update();  // Update zombie for any time-based changes like slow effects wearing off

                        if (zombie.isAlive()) {
                            Plant plant;
                            if(zombie.getClass().getName().equals("PoleVaultingZombie") && zombie.getClass().getName().equals("DolphinRiderZombie")){
                                plant = gameMap.getPlant(zombie.getX()-1, zombie.getY());
                            }
                            else {
                                plant = gameMap.getPlant(zombie.getX(), zombie.getY());
                            }
                            
                            if (plant != null) {
                                zombie.attack(plant);
                                if (!plant.isAlive()) {
                                    gameMap.removePlant(plant);  
                                    System.out.println("Zombie at (" + zombie.getX() + ", " + zombie.getY() + ") destroyed the plant.");
                                }
                            }

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
            System.out.printf("");
        }
    }
}
