package src.main.StartExit;

import src.main.Interface.Command;
import src.main.Deck;
import src.main.Game;
import src.main.Inventory;
import java.util.Scanner;

public class StartGameCommand implements Command {
    private Game game;
    private Inventory inventory;
    private Scanner scanner = new Scanner(System.in);

    

    public StartGameCommand(Game game, Inventory inventory) {
        this.game = game;
        this.inventory = inventory;
    }

    @Override
    public void execute() {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        manageDeck();
        if (inventory.getDeck().isFull()) {
            game.startGame();
        } else {
            System.out.println(ANSI_RED + "Deck belum terisi penuh. silahkan isi deck terlebih dahulu" + ANSI_RESET);
        }
    }

    private void manageDeck() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayManageDeckMenu();
            final String ANSI_RED = "\u001B[31m";
            final String ANSI_RESET = "\u001B[0m";
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    selectPlant();
                    break;
                case "2":
                    swapPlants();
                    break;
                case "3":
                    deletePlant();
                    break;
                case "4":
                    showDeck();
                    break;
                case "5":
                    if (inventory.getDeck().isFull()) {
                        return; 
                    } else {
                        System.out.println(ANSI_RED + "Deck belum terisi penuh. silahkan isi deck terlebih dahulu" + ANSI_RESET);
                    }
                    break;
                default:
                    System.out.println("INPUT SALAH!!");
            }
        }
    }

    private void displayManageDeckMenu() {
        final String WHITE_BACKGROUND = "\u001B[47m";
        final String BLACK = "\u001B[30m";
        final String RESET = "\u001B[0m";

        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║" + WHITE_BACKGROUND + BLACK + "                DECK                " + RESET + "║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ 1. Add Plant to deck               ║");
        System.out.println("║ 2. Swap Plants                     ║");
        System.out.println("║ 3. Delete Plant form deck          ║");
        System.out.println("║ 4. Show Deck                       ║");
        System.out.println("║ 5. Start Game                      ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("PILIH OPSI (NOMOR): ");
    }

    private void selectPlant() {
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RESET = "\u001B[0m";
        System.out.println("Available Plants:");
        for (int i = 0; i < inventory.getAllPlantTypes().size(); i++) {
            System.out.println((i+1) + ". " + inventory.getAllPlantTypes().get(i));
        }
        System.out.print(ANSI_CYAN+"Select a plant to add to the deck: "+ANSI_RESET);
        int plantIndex = scanner.nextInt();
        scanner.nextLine(); 
        if (plantIndex > 0 && plantIndex < inventory.getAllPlantTypes().size()+1) {
            inventory.addPlantToDeck(plantIndex);
            
        } else {
            System.out.println("Invalid plant selection.");
        }
    }

    private void swapPlants() {
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RESET = "\u001B[0m";
        System.out.print(ANSI_CYAN+"Masukkan Index Pertama Plant Untuk di swap: "+ANSI_RESET);
        int index1 = scanner.nextInt()-1;
        System.out.print(ANSI_CYAN+"Masukkan Index Kedua Plant untuk di swap: "+ANSI_RESET);
        int index2 = scanner.nextInt()-1;
        scanner.nextLine(); 
        inventory.swapPlantsInDeck(index1, index2);
        System.out.println("Plants berhasil di swap.");
    }

    private void deletePlant() {
        System.out.print("Enter the plant index to delete from the deck: ");
        int index = scanner.nextInt()-1;
        scanner.nextLine(); // Consume newline
        if (inventory.removePlantFromDeck(index)) {
            System.out.println("Plant deleted from the deck.");
        }
    }

    private void showDeck() {
        inventory.getDeck().showDeck();
    }
}
