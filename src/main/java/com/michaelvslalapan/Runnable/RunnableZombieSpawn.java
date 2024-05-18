package main.java.com.michaelvslalapan.Runnable;
import main.java.com.michaelvslalapan.GameMap;
import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;

import java.util.Random;

public class RunnableZombieSpawn implements Runnable {
    private GameMap gameMap;
    private int spawnInterval = 1000; // Spawn every second
    private Random random = new Random();
    private static final int MAX_ZOMBIES = 10; // Maximum number of zombies allowed on the map

    public RunnableZombieSpawn(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Check the current game time from GameMap's clock
                int currentTime = gameMap.getCurrentTime();

                // Check the number of zombies currently on the map
                if (gameMap.getZombies().size() < MAX_ZOMBIES && currentTime >= 20 && currentTime <= 160) {
                    if (random.nextFloat() < 0.3) { // 30% chance to spawn a zombie at each interval
                        int x = gameMap.getWidth() - 1;
                        int y = random.nextInt(gameMap.getHeight());
                        if (gameMap.isPool(x, y)) {
                            // Spawn aquatic zombies in the pool
                            gameMap.addZombie(new DuckyTubeZombie(x, y));
                            System.out.println("Ducky Tube Zombie spawned at (" + x + ", " + y + ")");
                        } else {
                            // Spawn other types of zombies randomly on non-pool areas
                            double choice = random.nextDouble();
                            if (choice < 0.33) {
                                gameMap.addZombie(new NormalZombie(x, y));
                                System.out.println("Normal Zombie spawned at (" + x + ", " + y + ")");
                            } else if (choice < 0.66) {
                                gameMap.addZombie(new ConeheadZombie(x, y));
                                System.out.println("Conehead Zombie spawned at (" + x + ", " + y + ")");
                            } else {
                                gameMap.addZombie(new BucketheadZombie(x, y));
                                System.out.println("Buckethead Zombie spawned at (" + x + ", " + y + ")");
                            }
                        }
                    }
                }
                Thread.sleep(spawnInterval);
            }
        } catch (InterruptedException e) {
            System.out.println("Zombie spawning thread interrupted");
        }
    }
}
