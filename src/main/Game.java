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
    private Deck deck;
    public Game() {
        this.gameMap = new GameMap(11, 6);
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        this.deck = deck;
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
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println(ANSI_BLUE + "***************************************************************************************************************************************");
        System.out.println("*" + ANSI_YELLOW + "                                                   Help Information                                                            " + ANSI_BLUE + "      *");
        System.out.println("***************************************************************************************************************************************");
        System.out.println("*" + ANSI_CYAN + " This is a CLI-based Plants vs. Zombies game.                                                                                       " + ANSI_BLUE + " *");
        System.out.println("*" + ANSI_PURPLE + " Commands pada Main Menu:                                                                                                         " + ANSI_BLUE + "   *");
        System.out.println("*" + ANSI_RESET + "   1.  Start the game                                                                                                              " + ANSI_BLUE + "  *");
        System.out.println("*" + ANSI_RESET + "   2.  Display help information                                                                                                    " + ANSI_BLUE + "  *");
        System.out.println("*" + ANSI_RESET + "   3.  Display list of plants                                                                                                      " + ANSI_BLUE + "  *");
        System.out.println("*" + ANSI_RESET + "   4.  Display list of zombies                                                                                                    " + ANSI_BLUE +  "   *");
        System.out.println("*" + ANSI_RESET + "   5.  Exit the game                                                                                                              " + ANSI_BLUE + "   *");
        System.out.println("*" + ANSI_PURPLE + " Commands pada permainan:                                                                                                        " + ANSI_BLUE + "    *");
        System.out.println("*" + ANSI_RESET + "   Plant [Type] x y  : Tanam plant pada map.                                                                                       " + ANSI_BLUE + "  *");
        System.out.println("*" + ANSI_RESET + "   Dig [type] x y    : Remove plant dari map.                                                                                      " + ANSI_BLUE + "  *");
        System.out.println("*" + ANSI_RESET + "   Start             : Memulai permainan.                                                                                          " + ANSI_BLUE + "  *");
        System.out.println("*" + ANSI_RESET + "   Help              : Memberikan deskripsi dari permainan, arahan cara bermain untuk pemain, dan daftar command yang dapat dipakai." + ANSI_BLUE + " *");
        System.out.println("*" + ANSI_RESET + "   Plants List       : Menampilkan list dari informasi tanaman yang dapat digunakan dalam permainan.                                " + ANSI_BLUE + " *");
        System.out.println("*" + ANSI_RESET + "   Zombies List      : Menampilkan list dari informasi zombie yang dapat muncul dalam permainan.                                    " + ANSI_BLUE + " *");
        System.out.println("*" + ANSI_RESET + "   Exit              : Keluar dari permainan.                                                                                       " + ANSI_BLUE + " *");
        System.out.println("***************************************************************************************************************************************");
        System.out.println("*" + ANSI_GREEN + " Press any key to return to the main menu...                                                                                        " + ANSI_BLUE + " *");
        System.out.println("***************************************************************************************************************************************" + ANSI_RESET);
        scanner.nextLine();
    }
    
    public void displayPlantsList() {
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
    
        System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║" + ANSI_YELLOW + "                              Plants List                                  " + ANSI_BLUE +    " ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + " 1.  Peashooter                                                             " + ANSI_BLUE + "║");
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "100" + ANSI_RESET + ", Health: " + ANSI_GREEN + "100" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "25" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "4" + ANSI_RESET +                "             ║");
        System.out.println("║     Range: " + ANSI_RED + "-1" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "10" + ANSI_RESET +                                                                            "                                                ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + " 2.  Sunflower                                                      " + ANSI_BLUE + "        ║");
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "50" + ANSI_RESET + ", Health: " + ANSI_GREEN + "100" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "0" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "0" + ANSI_RESET +                  "               ║");
        System.out.println("║     Range: " + ANSI_RED + "0" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "10" + ANSI_RESET +            "                                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + " 3.  Wallnut                                                                " + ANSI_BLUE + "║");
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "50" + ANSI_RESET + ", Health: " + ANSI_GREEN + "400" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "0" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "0" + ANSI_RESET +                         "               ║");
        System.out.println("║     Range: " + ANSI_RED + "0" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "20" + ANSI_RESET +                 "                                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + " 4.  Snow Pea                                                       " + ANSI_BLUE + "        ║");
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "175" + ANSI_RESET + ", Health: " + ANSI_GREEN + "100" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "25" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "4" + ANSI_RESET +                        "             ║");
        System.out.println("║     Range: " + ANSI_RED + "-1" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "10" + ANSI_RESET + "                                                ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + " 5.  Squash                                                         " + ANSI_BLUE + "        ║");
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "50" + ANSI_RESET + ", Health: " + ANSI_GREEN + "100" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "1000" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "0" + ANSI_RESET +      "            ║");
        System.out.println("║     Range: " + ANSI_RED + "1" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "10" + ANSI_RESET + "                                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + " 6.  Repeater                                                       " + ANSI_BLUE + "        ║");
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "150" + ANSI_RESET + ", Health: " + ANSI_GREEN + "100" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "50" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "4" + ANSI_RESET +      "             ║");
        System.out.println("║     Range: " + ANSI_RED + "-1" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "10" + ANSI_RESET + "                                                ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + " 7.  Lilypad                                                        " + ANSI_BLUE + "        ║");  
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "25" + ANSI_RESET + ", Health: " + ANSI_GREEN + "50" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "0" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "0" + ANSI_RESET + "                ║");
        System.out.println("║     Range: " + ANSI_RED + "0" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "10" + ANSI_RESET + "                                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + " 8.  Chomper                                                        " + ANSI_BLUE + "        ║");
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "150" + ANSI_RESET + ", Health: " + ANSI_GREEN + "100" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "1000" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "20" + ANSI_RESET + "          ║");
        System.out.println("║     Range: " + ANSI_RED + "1" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "10" + ANSI_RESET + "                                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + " 9.  Tanglekelp                                                     " + ANSI_BLUE + "        ║");
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "25" + ANSI_RESET + ", Health: " + ANSI_GREEN + "100" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "1000" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "0" + ANSI_RESET + "            ║");
        System.out.println("║     Range: " + ANSI_RED + "1" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "10" + ANSI_RESET + "                                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + ANSI_GREEN + "10.  Jalapeno                                                       " + ANSI_BLUE + "        ║");
        System.out.println("║" + ANSI_RESET + "     Cost: " + ANSI_YELLOW + "125" + ANSI_RESET + ", Health: " + ANSI_GREEN + "100" + ANSI_RESET + ", Attack Damage: " + ANSI_RED + "1000" + ANSI_RESET + ", Attack Speed: " + ANSI_RED + "1" + ANSI_RESET + "           ║");
        System.out.println("║     Range: " + ANSI_RED + "-1" + ANSI_RESET + ", Cooldown: " + ANSI_YELLOW + "10" + ANSI_RESET + "                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println(ANSI_RESET + "Press any key to return to the main menu...");
        scanner.nextLine(); // Assuming scanner is defined elsewhere in your class
    }
    
    
    

    public void displayZombiesList() {
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_RESET = "\u001B[0m";

    System.out.println(ANSI_BLUE + "╔═════════════════════════════════════════════════════════════════════════════════════╗");
    System.out.println("║" + ANSI_YELLOW + "                                       Zombies List                                 " + ANSI_BLUE + " ║");
    System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════╣");
    System.out.println("║" + ANSI_GREEN + " Normal Zombie             " + ANSI_RESET + " Health: " + ANSI_RED + "125" + ANSI_RESET + ", Damage: " + ANSI_RED + "100" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "false" + ANSI_BLUE + "    ║");
    System.out.println("║" + ANSI_GREEN + " Conehead Zombie           " + ANSI_RESET + " Health: " + ANSI_RED + "250" + ANSI_RESET + ", Damage: " + ANSI_RED + "100" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "false" + ANSI_BLUE + "    ║");
    System.out.println("║" + ANSI_GREEN + " Buckethead Zombie         " + ANSI_RESET + " Health: " + ANSI_RED + "300" + ANSI_RESET + ", Damage: " + ANSI_RED + "100" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "false" + ANSI_BLUE + "    ║");
    System.out.println("║" + ANSI_GREEN + " Ducky Tube Zombie         " + ANSI_RESET + " Health: " + ANSI_RED + "100" + ANSI_RESET + ", Damage: " + ANSI_RED + "100" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "true" + ANSI_BLUE + "     ║");
    System.out.println("║" + ANSI_GREEN + " Pole Vaulting Zombie      " + ANSI_RESET + " Health: " + ANSI_RED + "175" + ANSI_RESET + ", Damage: " + ANSI_RED + "100" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "false" + ANSI_BLUE + "    ║");
    System.out.println("║" + ANSI_GREEN + " Dolphin Rider Zombie      " + ANSI_RESET + " Health: " + ANSI_RED + "175" + ANSI_RESET + ", Damage: " + ANSI_RED + "100" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "true" + ANSI_BLUE + "     ║");
    System.out.println("║" + ANSI_GREEN + " Giga Zombie               " + ANSI_RESET + " Health: " + ANSI_RED + "400" + ANSI_RESET + ", Damage: " + ANSI_RED + "1000" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "false" + ANSI_BLUE + "   ║");
    System.out.println("║" + ANSI_GREEN + " Saraga Zombie             " + ANSI_RESET + " Health: " + ANSI_RED + "100" + ANSI_RESET + ", Damage: " + ANSI_RED + "100" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "true" + ANSI_BLUE + "     ║");
    System.out.println("║" + ANSI_GREEN + " Newspaper Zombie          " + ANSI_RESET + " Health: " + ANSI_RED + "125" + ANSI_RESET + ", Damage: " + ANSI_RED + "100" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "false" + ANSI_BLUE + "    ║");
    System.out.println("║" + ANSI_GREEN + " Jack In The Box Zombie    " + ANSI_RESET + " Health: " + ANSI_RED + "100" + ANSI_RESET + ", Damage: " + ANSI_RED + "1000" + ANSI_RESET + ", Speed: " + ANSI_RED + "1" + ANSI_RESET + ", is_aquatic: " + ANSI_RED + "false" + ANSI_BLUE + "   ║");
    System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════╣");
    System.out.println("║" + ANSI_PURPLE + " Press any key to return to the main menu...                                         " + ANSI_BLUE + "║");
    System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
    scanner.nextLine(); // Pause for user to read zombie list
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
                Plant plant = deck.getPlant(type);
                if (plant != null) {
                    plant.setPosition(x, y);
                    if (gameMap.addPlant(plant, x, y)) {
                        System.out.println("Planted " + type + " at (" + x + ", " + y + ").");
                    } else {
                        System.out.println("Failed to plant. Check suns, position, or type.");
                    }
                } else {
                    System.out.println("Invalid plant type or not in deck.");
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
                System.out.println("Invalid input.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numerical values provided.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Command does not include enough information.");
        }
    
        gameMap.printMap();  // Optionally print the map to show updates after any command
    }
    

    private Plant createPlant(String type) {
        switch (type.toLowerCase()) {
            case "repeater":
                return new Repeater();
            case "sunflower":
                return new Sunflower();
            case "peashooter":
                return new Peashooter();
            case "tanglekelp":
                return new TangleKelp();
            case "lilypad":
                return new Lilypad();
            case "squash":
                return new Squash();
            case "wallnut":
                return new Wallnut();
            case "chomper":
                return new Chomper();
            case "snowpea":
                return new Snowpea();
            default:
                return null;  // Return null if the plant type is unknown
        }
    }
}
