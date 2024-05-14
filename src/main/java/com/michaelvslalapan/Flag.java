package main.java.com.michaelvslalapan;

import java.util.Random;

import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.child_zombie.BucketheadZombie;
import main.java.com.michaelvslalapan.child_zombie.ConeheadZombie;
import main.java.com.michaelvslalapan.child_zombie.DolphinRiderZombie;
import main.java.com.michaelvslalapan.child_zombie.DuckyTubeZombie;
import main.java.com.michaelvslalapan.child_zombie.GigaZombie;
import main.java.com.michaelvslalapan.child_zombie.JackInTheBoxZombie;
import main.java.com.michaelvslalapan.child_zombie.NewspaperZombie;
import main.java.com.michaelvslalapan.child_zombie.NormalZombie;
import main.java.com.michaelvslalapan.child_zombie.PoleVaultingZombie;
import main.java.com.michaelvslalapan.child_zombie.SaragaZombie;
import main.java.com.michaelvslalapan.GameMap;

public class Flag {
    private GameMap gameMap;
    private boolean flagActive;
    private int flagInterval;
    private int lastFlagTime; // // Variable to keep track of the last time the flag event was activated
    private Random random;

    public Flag(GameMap gameMap, int flagInterval) {
        this.gameMap = gameMap;
        this.flagInterval = flagInterval;
        this.flagActive = false;
        this.lastFlagTime = -flagInterval; //// Initialize to negative flagInterval so the first flag event can occur at time 0

        this.random = new Random();
    }

    public void checkForFlag() {
        int currentTime = gameMap.getCurrentTime();
        // Setiap kali interval waktu tercapai, aktifkan flag
        if (currentTime % flagInterval == 0 && currentTime > lastFlagTime) {
            flagActive = true;
            lastFlagTime = currentTime; // Update lastFlagTime
            System.out.println("Flag event activated!");
            increaseZombieSpawn();
        }
    }

   private void increaseZombieSpawn() {
    if (flagActive) {
        for (int i = 0; i < gameMap.getWidth(); i++) {
            int additionalZombies = 2 + random.nextInt(10); // 2 sampai 10 zombie tambahan
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
    int zombieType = random.nextInt(10); // Angka acak antara 0 dan 9
    switch (zombieType) {
        case 0:
            return new BucketheadZombie(x, y, gameMap);
        case 1:
            return new ConeheadZombie(x, y, gameMap);
        case 2:
            return new DolphinRiderZombie(x, y, gameMap);
        case 3:
            return new DuckyTubeZombie(x, y, gameMap);
        case 4:
            return new GigaZombie(x, y, gameMap);
        case 5:
            return new JackInTheBoxZombie(x, y, gameMap);
        case 6:
            return new NewspaperZombie(x, y, gameMap);
        case 7:
            return new PoleVaultingZombie(x, y, gameMap);
        case 8: 
            return new SaragaZombie(x, y, gameMap);
        default:
            return new NormalZombie(x, y, gameMap); // Default jika ada kesalahan
    }
}

}
