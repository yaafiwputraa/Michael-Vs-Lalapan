package src.main;
import java.util.Random;

import src.main.AbstractClass.Zombie;
import src.main.Zombiechild.*;

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
            } else if(choice < 0.7) {
                return new DolphinRiderZombie(x, y);
            } else {
                return new SaragaZombie(x, y);
            }
        } else {
            double choice = random.nextDouble();
            if (choice < 0.4) {
                return new NormalZombie(x, y);
            } else if (choice < 0.5) {
                return new PoleVaultingZombie(x, y);
            } else if (choice < 0.6) {
                return new BucketheadZombie(x, y);
            } else if (choice < 0.7) {
                return new ConeheadZombie(x, y);
            } else if (choice < 0.8) {
                return new NewspaperZombie(x, y);
            } else if (choice < 0.95) {
                return new JackInTheBoxZombie(x, y);
            }
            else {
                return new GigaZombie(x, y);
            }
        }
    }
}

