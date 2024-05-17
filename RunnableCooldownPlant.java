public class RunnableCooldownPlant implements Runnable {
    private Deck deck;

    public RunnableCooldownPlant(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000); // Sleep for 1 second before updating cooldowns
                for (Plant plant : deck.getDeckPlants()) {
                    if (plant.getRemainingCooldown() > 0) {
                        plant.reduceCooldown(); // Reduce the cooldown by 1 second
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Cooldown management thread was interrupted.");
        }
    }
}
