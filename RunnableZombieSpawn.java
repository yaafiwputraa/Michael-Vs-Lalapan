import java.util.Random;

public class RunnableZombieSpawn implements Runnable {
    private GameMap gameMap;
    private int spawnInterval = 10000; // 10 seconds in milliseconds
    private Random random = new Random();

    public RunnableZombieSpawn(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Check the current game time from GameMap's clock
                int currentTime = gameMap.getCurrentTime();
                System.out.println("Checking zombie spawn at time: " + currentTime);
            
                // Spawn zombies only between the 20th and 160th seconds
                if (currentTime >= 20 && currentTime <= 160) {
                    if (random.nextFloat() < 0.3) { // 30% chance to spawn a zombie at each interval
                        int y = random.nextInt(gameMap.getHeight());
                        gameMap.addZombie(new Zombie(125, 100, 1, gameMap.getWidth() - 1, y)); // Assuming Zombie constructor (health, attackDamage, attackSpeed, x, y)
                        System.out.println("Zombie spawned at (" + (gameMap.getWidth() - 1) + ", " + y + ")");
                    }
                }
                Thread.sleep(spawnInterval);
            }
        } catch (InterruptedException e) {
            System.out.println("Zombie spawning thread interrupted");
        }
    }
}
