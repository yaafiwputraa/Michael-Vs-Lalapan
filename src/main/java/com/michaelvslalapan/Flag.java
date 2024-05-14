package main.java.com.michaelvslalapan;

import java.util.Random;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import src.main.java.com.michaelvslalapan.child_zombie.BucketheadZombie;
import src.main.java.com.michaelvslalapan.child_zombie.PoleVaultingZombie;
import main.java.com.michaelvslalapan.GameMap;

public class Flag {
    private GameMap gameMap;
    private boolean flagActive;
    private int flagInterval;
    private Random random;

    public Flag(GameMap gameMap, int flagInterval) {
        this.gameMap = gameMap;
        this.flagInterval = flagInterval;
        this.flagActive = false;
        this.random = new Random();
    }

    public void checkForFlag() {
        // Setiap kali interval waktu tercapai, aktifkan flag
        if (gameMap.getCurrentTime() % flagInterval == 0) {
            flagActive = true;
            System.out.println("Flag event activated!");
            increaseZombieSpawn();
        }
    }

   private void increaseZombieSpawn() {
    if (flagActive) {
        // Logika untuk meningkatkan jumlah zombie
        for (int i = 0; i < gameMap.getWidth(); i++) {
            int additionalZombies = 2 + random.nextInt(4); // 2 sampai 5 zombie tambahan
            for (int j = 0; j < additionalZombies; j++) {
                Zombie newZombie = createRandomZombie(i, gameMap.getHeight() - 1);
                gameMap.addZombie(newZombie); // Tambahkan zombie acak ke game
            }
        }
        flagActive = false; // Reset flag setelah penambahan zombie
    }
}

// Method untuk membuat zombie secara acak
private Zombie createRandomZombie(int x, int y) {
    int zombieType = random.nextInt(4); // Angka acak antara 0 dan 3
    switch (zombieType) {
        case 0:
            return new RegularZombie(x, y);
        case 1:
            return new BucketheadZombie(x, y);
        case 2:
            return new PoolZombie(x, y);
        case 3:
            return new PoleVaultingZombie(x, y);
        default:
            return new RegularZombie(x, y); // Default jika ada kesalahan
    }
}

}
