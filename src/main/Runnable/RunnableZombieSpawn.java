package src.main.Runnable;

import java.util.Random;

import src.main.GameMap;
import src.main.ZombieFactory;
import src.main.AbstractClass.Zombie;
import src.main.Zombiechild.*;

import java.util.Random;

public class RunnableZombieSpawn implements Runnable {
    private GameMap gameMap;
    private ZombieFactory zombieFactory;
    private int spawnInterval = 1000; 
    private Random random = new Random();
    private static final int MAX_ZOMBIES = 10; 

    public RunnableZombieSpawn(GameMap gameMap) {
        this.gameMap = gameMap;
        this.zombieFactory = new ZombieFactory(gameMap); // Initialize the factory
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int currentTime = gameMap.getCurrentTime();

                if (gameMap.getZombies().size() < MAX_ZOMBIES && currentTime >= 20 && currentTime <= 160) {
                    if (random.nextFloat() < 0.3) {
                        int x = gameMap.getWidth() - 1;
                        int y = random.nextInt(gameMap.getHeight());
                        Zombie zombie = zombieFactory.createZombie(x, y); // Use the factory to create a zombie
                        gameMap.addZombie(zombie);
                        System.out.println(zombie.getClass().getSimpleName() + " spawned at (" + x + ", " + y + ")");
                    }
                }
                Thread.sleep(spawnInterval);
            }
        } catch (InterruptedException e) {
            System.out.printf("");
        }
    }
}

