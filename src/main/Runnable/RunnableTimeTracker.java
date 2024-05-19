package src.main.Runnable;

import src.main.GameMap;

public class RunnableTimeTracker implements Runnable {
    private GameMap gameMap;

    public RunnableTimeTracker(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000); // Ensure this sleep is happening
                gameMap.updateTime();
            }
        } catch (InterruptedException e) {
            System.out.printf("");
        }
    }
}
