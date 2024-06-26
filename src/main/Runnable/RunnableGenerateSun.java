package src.main.Runnable;

import java.util.Random;

import src.main.GameMap;
import src.main.Plantchild.*;

public class RunnableGenerateSun implements Runnable {
    private GameMap gameMap;
    private Random random = new Random();

    public RunnableGenerateSun(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (gameMap.isMorning()) { // Check if it's morning
                    gameMap.addSuns(25);  // Add suns randomly during morning
                    System.out.println("25 suns added, current suns: " + gameMap.getCurrentSuns());
                    Thread.sleep(5000 + random.nextInt(5000)); // Wait for 5-10 seconds
                }
                // Generate sun from Sunflowers every 10 seconds
                gameMap.getAllPlants().stream()
                       .filter(p -> p instanceof Sunflower)
                       .forEach(p -> ((Sunflower)p).performAction());
                Thread.sleep(3000); 
            }
        } catch (InterruptedException e) {
            System.out.printf("");
        }
    }
}
