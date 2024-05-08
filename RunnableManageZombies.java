import main.java.com.michaelvslalapan.AbstractClass.Plant;

public class RunnableManageZombies implements Runnable {
    private GameMap gameMap;
    private final int moveInterval = 5000; // Zombie moves every 5 seconds

    public RunnableManageZombies(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (Zombie zombie : gameMap.getZombies()) {
                    if (zombie.isAlive()) {
                        // Check for a plant at the zombie's current location
                        Plant plant = gameMap.getPlant(zombie.getX(), zombie.getY());
                        if (plant != null) {
                            zombie.attack(plant);
                            if (plant.getHealth() <= 0) {
                                gameMap.removePlant(plant.getX(), plant.getY()); // Remove the dead plant
                                System.out.println("Zombie at (" + zombie.getX() + ", " + zombie.getY() + ") destroyed the plant and moves on.");
                            }
                        }
                        // Move zombie forward if no plant is in the way or the plant is dead
                        if (plant == null || plant.getHealth() <= 0) {
                            zombie.move();
                            System.out.println("Zombie moves to (" + zombie.getX() + ", " + zombie.getY() + ")");
                        }
                    }
                }
                Thread.sleep(moveInterval);
            }
        } catch (InterruptedException e) {
            System.out.println("Zombie management thread interrupted");
        }
    }
}
