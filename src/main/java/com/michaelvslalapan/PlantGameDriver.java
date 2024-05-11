package main.java.com.michaelvslalapan;

// Main class to test Deck and Inventory
public class PlantGameDriver {

    public static void main(String[] args) {
        // Create an inventory
        Inventory inventory = new Inventory();
        
        // Add plants to the inventory
        inventory.getInventory().add(new ConcretePlant("Sunflower"));
        inventory.getInventory().add(new ConcretePlant("Peashooter"));
        inventory.getInventory().add(new ConcretePlant("Cherry Bomb"));
        
        // Add plants from inventory to the deck
        inventory.addPlantToDeck(0); // Add Sunflower
        inventory.addPlantToDeck(1); // Add Peashooter
        
        // Show inventory and deck before any operations
        System.out.println("Inventory before operations:");
        inventory.showInventory();
        System.out.println("Deck before operations:");
        inventory.getDeck().showDeckPlants();

        // Swap plants in the deck
        if (inventory.getDeck().getDeckPlants().size() > 1) {
            inventory.swapDeck(0, 1); // Swap Sunflower and Peashooter
        }

        // Show deck after swap
        System.out.println("Deck after swap:");
        inventory.getDeck().showDeckPlants();

        // Remove a plant from the deck
        inventory.deletePlantFromDeck(0); // Remove the first plant

        // Show deck after removal
        System.out.println("Deck after removing a plant:");
        inventory.getDeck().showDeckPlants();
        
        // Check if deck is empty and if the game can start
        System.out.println("Is the deck empty? " + inventory.getDeck().isEmpty());
        System.out.println("Can the game start? " + inventory.getDeck().canStartGame());
    }
}
