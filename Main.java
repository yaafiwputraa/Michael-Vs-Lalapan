import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.java.com.michaelvslalapan.AbstractClass.Plant;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(11, 6);  // Initialize the game map with dimensions 11x6
        ExecutorService executor = Executors.newFixedThreadPool(5);  // Thread pool for managing game mechanics

        // Submit runnable tasks for managing zombies, sun generation, time tracking, plant actions, and attacking plants
        executor.submit(new RunnableZombieSpawn(gameMap));
        executor.submit(new RunnableGenerateSun(gameMap));
        executor.submit(new RunnableTimeTracker(gameMap));
        executor.submit(new RunnableManageZombies(gameMap));
        executor.submit(new RunnableManageAttackingPlants(gameMap));

        // Handling user input for planting and removing plants
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter command ('Plant [type] x y', 'Dig x y', 'Exit'):");
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting game...");
                break;
            }
            handleCommand(command, gameMap);
        }

        // Shut down executor and close resources
        executor.shutdownNow();
        scanner.close();
        System.out.println("Game terminated.");
    }

    private static void handleCommand(String command, GameMap gameMap) {
        String[] parts = command.split(" ");
        if (parts.length < 4) {
            if (parts[0].equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            System.out.println("Invalid command format.");
            return;
        }
        int x = Integer.parseInt(parts[2]);
        int y = Integer.parseInt(parts[3]);

        switch (parts[0].toLowerCase()) {
            case "plant":
                Plant plant = null;
                if (parts[1].equalsIgnoreCase("repeater")) {
                    plant = new Repeater(x, y, gameMap);
                } else if (parts[1].equalsIgnoreCase("sunflower")) {
                    plant = new Sunflower(x, y, gameMap);
                } else if (parts[1].equalsIgnoreCase("peashooter")) {
                    plant = new Peashooter(x, y, gameMap);
                }
                if (plant != null && gameMap.addPlant(plant)) {
                    System.out.println("Planted " + parts[1] + " at (" + x + ", " + y + ").");
                    gameMap.printMap();  // Optionally print the map to show updates
                } else {
                    System.out.println("Failed to plant. Check suns, position, or type.");
                }
                break;
            case "dig":
                gameMap.removePlant(x, y);
                gameMap.printMap();  // Print the map after digging
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }
}
