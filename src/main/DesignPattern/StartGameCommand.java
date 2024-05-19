package src.main.DesignPattern;
import src.main.Interface.Command;
import src.main.Deck;
import src.main.Game;
import src.main.Inventory;
import java.util.Scanner;

public class StartGameCommand implements Command {
    private Game game;
    private Inventory inventory;
    private Deck deck;
    private Scanner scanner;

    public StartGameCommand(Game game, Inventory inventory, Deck deck) {
        this.game = game;
        this.inventory = inventory;
        this.deck = deck;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println("=== Deck Management ===");
            System.out.println("1. Add to Deck");
            System.out.println("2. Swap in Deck");
            System.out.println("3. Start Game");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addToDeck();
                    break;
                case "2":
                    swapInDeck();
                    break;
                case "3":
                    if (deck.isFull()) {
                        game.startGame();
                        return;
                    } else {
                        System.out.println("Deck is not full. Please add 6 plants to the deck.");
                    }
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addToDeck() {
        System.out.println("Inventory: " + inventory.getPlantNames());
        System.out.println("Current Deck: " + deck.getPlantNames());
        System.out.print("Enter the plant name to add to deck: ");
        String plantName = scanner.nextLine();
        if (inventory.hasPlant(plantName)) {
            if (deck.addPlant(inventory.getPlant(plantName))) {
                System.out.println("Plant added to deck.");
            } else {
                System.out.println("Deck is full or plant is already in the deck.");
            }
        } else {
            System.out.println("Invalid plant name.");
        }
    }

    private void swapInDeck() {
        System.out.println("Current Deck: " + deck.getPlantNames());
        System.out.println("Inventory: " + inventory.getPlantNames());
        System.out.print("Enter the plant name in the deck to swap out: ");
        String plantOut = scanner.nextLine();
        System.out.print("Enter the plant name from inventory to swap in: ");
        String plantIn = scanner.nextLine();
        if (deck.hasPlant(plantOut) && inventory.hasPlant(plantIn)) {
            deck.swapPlant(inventory.getPlant(plantIn), plantOut);
            System.out.println("Plants swapped.");
        } else {
            System.out.println("Invalid plant names.");
        }
    }
}
