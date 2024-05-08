public class RunnableCooldownPlant implements Runnable {
    private GameMap gameMap;
    private int checkInterval = 1000; // Check every second

    public RunnableCooldownPlant(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Implement logic to handle plant cooldowns
                Thread.sleep(checkInterval);
            }
        } catch (InterruptedException e) {
            System.out.println("Plant cooldown management thread interrupted");
        }
    }
}
