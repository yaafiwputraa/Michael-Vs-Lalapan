package src.main.Runnable;

import java.util.Random;

import src.main.GameMap;
import src.main.Zombiechild.*;

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
                            double choice = random.nextDouble();
                            if (choice < 0.5) {
                                gameMap.addZombie(new DuckyTubeZombie(x, y));
                                System.out.println("Ducky Tube Zombie spawned at (" + x + ", " + y + ")");
                            } else {
                                gameMap.addZombie(new DolphinRiderZombie(x, y));
                                System.out.println("Dolphin Rider Zombie spawned at (" + x + ", " + y + ")");
                            }
                            
    
                        } else {
                            // Spawn other types of zombies randomly on non-pool areas
                            double choice = random.nextDouble();
                            if (choice < 0.4) {
                                gameMap.addZombie(new NormalZombie(x, y));
                                System.out.println("Normal Zombie spawned at (" + x + ", " + y + ")");
                            } else if (choice < 0.5) {
                                gameMap.addZombie(new PoleVaultingZombie(x, y));
                                System.out.println("Pole Vaulting Zombie spawned at (" + x + ", " + y + ")");
                            } else if (choice < 0.6) {
                                gameMap.addZombie(new BucketheadZombie(x, y));
                                System.out.println("Buckethead Zombie spawned at (" + x + ", " + y + ")");
                            } else if (choice < 0.9){
                                gameMap.addZombie(new ConeheadZombie(x, y));
                                System.out.println("Conehead Zombie spawned at (" + x + ", " + y + ")");     
                            } else{
                                gameMap.addZombie(new GigaZombie(x, y));
                                System.out.println("Giga Zombie spawned at (" + x + ", " + y + ")");
                            }
                        }
                    }
                }
                Thread.sleep(spawnInterval);
            }
        } catch (InterruptedException e) {
            System.out.printf("");
        }
    }
}
