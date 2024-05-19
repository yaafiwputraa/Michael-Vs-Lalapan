package src.main;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import src.main.AbstractClass.Plant;
import src.main.Plantchild.*;
import src.main.Runnable.*;

public class Game {
    private GameMap gameMap;
    private boolean isRunning;
    private Scanner scanner;

    public Game() {
        this.gameMap = new GameMap(11, 6);
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        gameMap = new GameMap(11, 6); // Reinitialize the game map for a new game
        isRunning = true; // Reset the running state

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit(new RunnableZombieSpawn(gameMap));
        executor.submit(new RunnableGenerateSun(gameMap));
        executor.submit(new RunnableTimeTracker(gameMap));
        executor.submit(new RunnableManageZombies(gameMap));
        executor.submit(new RunnableManageAttackingPlants(gameMap));

        while (isRunning) {
            if (gameMap.zombiesReachedFirstColumn()) {
                System.out.println("Game Over! Zombies have reached the base.");
                isRunning = false; // End the game loop
                break;
            }

            System.out.println("Enter command ('Plant [type] x y', 'Dig [type] x y', 'Exit'):");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting game...");
                isRunning = false; // End the game loop
                break;
            }
            handleCommand(command, gameMap);
        }

        // Shut down executor and close resources
        executor.shutdownNow();
        System.out.println("Game terminated.");
    }

    public void displayHelp() {
        System.out.println("=== Help ===");
        System.out.println("This is a CLI-based Plants vs. Zombies game.");
        System.out.println("Commands pada Main Menu :");
        System.out.println("1 - Start the game");
        System.out.println("2 - Display help information");
        System.out.println("3 - Display list of plants");
        System.out.println("4 - Display list of zombies");
        System.out.println("5 - Exit the game");
        System.out.println("Commands pada permainan :");
        System.out.println("Plant [Type] x y - Tanam plant pada map.");
        System.out.println("Dig [type] x y - Remove plant dari map.");
        System.out.println("Exit - Keluar dari permainan");
        System.out.println("Press any key to return to the main menu...");
        scanner.nextLine();
    }

    public void displayPlantsList() {
        System.out.println("=== Plants List ===");
        System.out.println("1.  Peashooter - Cost: 100, Health: 100, Attack Damage: 25, Attack Speed: 4, Range: -1, Cooldown: 10");
        System.out.println("2.  Sunflower - Cost: 50, Health: 100, Attack Damage: 0, Attack Speed: 0, Range: 0, Cooldown: 10");
        System.out.println("3.  Wallnut - Cost: 50, Health: 400, Attack Damage: 0, Attack Speed: 0, Range: 0, Cooldown: 20");
        System.out.println("4.  Snow Pea - Cost: 175, Health: 100, Attack Damage: 25, Attack Speed: 4, Range: -1, Cooldown: 10");
        System.out.println("5.  Squash - Cost: 50, Health: 100, Attack Damage: 1000, Attack Speed: 0, Range: 1, Cooldown: 10");
        System.out.println("6.  Repeater - Cost: 150, Health: 100, Attack Damage: 50, Attack Speed: 4, Range: -1, Cooldown: 10");
        System.out.println("7.  Lilypad - Cost: 25, Health: 50, Attack Damage: 0, Attack Speed: 0, Range: 0, Cooldown: 10");
        System.out.println("8.  Chomper - Cost: 150, Health: 100, Attack Damage: 1000, Attack Speed: 20, Range: 1, Cooldown: 10");
        System.out.println("9.  Tanglekelp - Cost: 25, Health: 100, Attack Damage: 1000, Attack Speed: 0, Range: 1, Cooldown: 10");
        System.out.println("10. Jalapeno - Cost: 125, Health: 100, Attack Damage: 1000, Attack Speed: 1, Range: -1, Cooldown: 10");
        // Add more plant details as needed
        System.out.println("Press any key to return to the main menu...");
        scanner.nextLine();
    }

    public void displayZombiesList() {
        System.out.println("=== Zombies List ===");
        System.out.println("Normal Zombie - Health: 125, Damage: 100, Speed: 1, is_aqua: false");
        System.out.println("Conehead Zombie - Health: 250, Damage: 100, Speed: 1, is_aqua: false");
        System.out.println("Buckethead Zombie - Health: 300, Damage: 100, Speed: 1, is_aqua: false");
        System.out.println("Ducky Tube Zombie - Health: 100, Damage: 100, Speed: 1, is_aqua: true");
        System.out.println("Pole Vaulting Zombie - Health: 175, Damage: 100, Speed: 1, is_aqua: false");
        System.out.println("Dolphine Rider Zombie - Health: 175, Damage: 100, Speed: 1, is_aqua: true");
        System.out.println("Giga Zombie - Health: 400, Damage: 1000, Speed: 1, is_aqua: false");
        System.out.println("Saraga Zombie - Health: 100, Damage: 100, Speed: 1, is_aqua: true");
        System.out.println("Newspaper Zombie - Health: 125, Damage: 100, Speed: 1, is_aqua: false");
        System.out.println("Jack In The Box Zombie - Health: 100, Damage: 1000, Speed: 1, is_aqua: false");
        // Add more zombie details as needed
        System.out.println("Press any key to return to the main menu...");
        new Scanner(System.in).nextLine(); // Pause for user to read zombie list
    }

    private void handleCommand(String command, GameMap gameMap) {
        String[] parts = command.trim().split(" ");
        if (parts.length == 0) {
            System.out.println("Invalid command format.");
            return;
        }

        String action = parts[0].toLowerCase();
        if ("exit".equals(action)) {
            isRunning = false;
            return;
        }


        try {
            if (action.equals("plant")) {
                if (parts.length < 4) {
                    System.out.println("Invalid command format for planting.");
                    return;
                }
                int x = Integer.parseInt(parts[2]);
                int y = Integer.parseInt(parts[3]);
                String type = parts[1].toLowerCase();
                Plant plant = createPlant(type, x, y, gameMap);
                if (plant != null && gameMap.addPlant(plant, x, y)) {
                    System.out.println("Planted " + type + " at (" + x + ", " + y + ").");
                } else if (plant != null) {
                    System.out.println("Failed to plant. Check suns, position, or type.");
                } else {
                    System.out.println("Invalid plant type.");
                }
            } else if (action.equals("dig")) {
                if (parts.length < 3) {
                    System.out.println("Invalid command format for digging.");
                    return;
                }
                int x = Integer.parseInt(parts[2]);
                int y = Integer.parseInt(parts[3]);
                if (gameMap.isPlantPresent(x, y)) {
                    gameMap.removePlant(x, y);
                    System.out.println("Removed plant at (" + x + ", " + y + ").");
                } else {
                    System.out.println("No plant to remove at (" + x + ", " + y + ").");
                }
            } else {
                System.out.println("Input salah");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numerical values provided.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Command does not include enough information.");
        }

        gameMap.printMap();
    }

    private Plant createPlant(String type, int x, int y, GameMap gameMap) {
        switch (type) {
            case "repeater":
                return new Repeater(x, y, gameMap);
            case "sunflower":
                return new Sunflower(x, y, gameMap);
            case "peashooter":
                return new Peashooter(x, y, gameMap);
            case "tanglekelp":
                return new TangleKelp(x, y, gameMap);
            case "lilypad":
                return new Lilypad(x, y, gameMap);
            case "squash":
                return new Squash(x, y, gameMap);
            case "wallnut":
                return new Wallnut(x, y, gameMap);
            case "chomper":
                return new Chomper(x, y, gameMap);
            case "snowpea":
                return new Snowpea(x, y, gameMap);
            case "jalapeno":
                return new Jalapeno(x, y, gameMap);
            default:
                return null;
        }
    }
}

