public class RunnableManageAttackingPlants implements Runnable {
    private GameMap gameMap;

    public RunnableManageAttackingPlants(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        try {
            // Use a consistent check interval for simplicity, can be optimized
            long checkInterval = 1000; // 1 second
            while (!Thread.currentThread().isInterrupted()) {
                long currentTime = System.currentTimeMillis();
                for (Plant plant : gameMap.getAllPlants()) {
                    if (plant.isAttackingType() && plant.isAlive() && currentTime >= plant.getNextActionTime()) {
                        plant.performAction();
                        plant.setNextActionTime(currentTime + plant.getAttackSpeed() * 1000); // Schedule next action
                    }
                }
                gameMap.removeDeadZombie(null);
                Thread.sleep(checkInterval);
            }
        } catch (InterruptedException e) {
            System.out.println("Attacking plants management thread interrupted");
        }
    }
}
