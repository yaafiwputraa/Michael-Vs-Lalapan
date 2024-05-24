package src.main;
import java.util.Random;

public class ZombieFactory {
    private GameMap gameMap;
    private Random random;

    public ZombieFactory(GameMap gameMap) {
        this.gameMap = gameMap;
        this.random = new Random();
    }

    public Zombie createZombie(int x, int y) {
        if (gameMap.isPool(x, y)) {
            // Spawn aquatic zombies in the pool
            double choice = random.nextDouble();
            if (choice < 0.5) {
                return new DuckyTubeZombie(x, y);
            } else {
                return new DolphinRiderZombie(x, y);
            }
        } else {
            double choice = random.nextDouble();
            if (choice < 0.4) {
                return new NormalZombie(x, y);
            } else if (choice < 0.5) {
                return new PoleVaultingZombie(x, y);
            } else if (choice < 0.6) {
                return new BucketheadZombie(x, y);
            } else if (choice < 0.9) {
                return new ConeheadZombie(x, y);
            } else {
                return new GigaZombie(x, y);
            }
        }
    }
}

