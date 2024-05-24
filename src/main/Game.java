package src.main;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import src.main.Runnable.*;

public class Game {
    private GameMap gameMap;
    private boolean isRunning;
    private Scanner scanner;
    private Inventory inventory;

    public Game() {
        this.gameMap = new GameMap(11, 6);
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        if (!inventory.getDeck().isFull()) {
            System.out.println("Deck is not full. Please fill the deck before starting the game.");
            return;
        }

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
                System.out.println("  ▄▀  ██   █▀▄▀█ ▄███▄       ████▄     ▄   ▄███▄   █▄▄▄▄ ");
                System.out.println("▄▀    █ █  █ █ █ █▀   ▀      █   █      █  █▀   ▀  █  ▄▀ ");
                System.out.println("█ ▀▄  █▄▄█ █ ▄ █ ██▄▄        █   █ █     █ ██▄▄    █▀▀▌  ");
                System.out.println("█   █ █  █ █   █ █▄   ▄▀     ▀████  █    █ █▄   ▄▀ █  █  ");
                System.out.println(" ███     █    █  ▀███▀               █  █  ▀███▀     █   ");
                System.out.println("        █    ▀                        █▐            ▀    ");
                System.out.println("       ▀                              ▐                 ");
                System.out.println("Game Over! Zombies have reached the base.");
                isRunning = false; // End the game loop
                break;
            }

            if (gameMap.getCurrentTime() > 160 && gameMap.getCurrentTime() <= 200 && gameMap.getZombies().isEmpty()) {
                System.out.println("Congratulations! You have defeated all zombies. You win!");
                isRunning = false; // End the game loop
                break;
            }

            System.out.println("Enter command ('Plant [deckIndex] x y', 'Dig x y', 'Exit'):");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting game...");
                isRunning = false; // End the game loop
                break;
            }
            handleCommand(command);
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
        System.out.println("*" + ANSI_GREEN + " Ketik apapun untuk kembali ke Main Menu...                                                                                         " + ANSI_BLUE + " *");
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
        System.out.println(ANSI_RESET + "Ketik apapun untuk kembali ke Main Menu...");
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
        System.out.println("║" + ANSI_PURPLE + " Ketik apapun untuk kembali ke Main Menu...                                          " + ANSI_BLUE + "║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        scanner.nextLine(); // Pause for user to read zombie list
    }

    private void handleCommand(String command) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        String[] parts = command.trim().split(" ");
        if (parts.length == 0) {
            System.out.println(ANSI_RED+"Invalid command format."+ ANSI_RESET);
            return;
        }

        String action = parts[0].toLowerCase();
        try {
            switch (action) {
                case "plant":
                    PlantCommand(parts);
                    break;
                case "dig":
                    DigCommand(parts);
                    break;
                default:
                    System.out.println(ANSI_RED+"Invalid command."+ANSI_RESET);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED+"Error: Invalid numerical values provided."+ANSI_RESET);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ANSI_RED+"Error: Command does not include enough information."+ANSI_RESET);
        }

        gameMap.printMap();
        showStatus();
    }

    private void PlantCommand(String[] parts) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        if (parts.length < 4) {
            System.out.println(ANSI_RED+"Format penanaman salah."+ANSI_RESET);
            return;
        }
        int deckIndex = Integer.parseInt(parts[1])-1;
        int x = Integer.parseInt(parts[2]);
        int y = Integer.parseInt(parts[3]);

        try {
            PlantType plantType = inventory.getDeck().getPlantTypes().get(deckIndex);
            if (plantType != null && gameMap.addPlant(createPlant(plantType, x, y), x, y)) {
                System.out.println("Planted " + plantType + " at (" + x + ", " + y + ").");
            } else {
                System.out.println(ANSI_RED+"Failed to plant. Check suns, position, or type."+ANSI_RESET);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Deck Invalid, pilih plant yang benar.");
        }
    }

    private void DigCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Format untuk menggali salah.");
            return;
        }
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        if (gameMap.isPlantPresent(x, y)) {
            gameMap.removePlant(x, y);
            System.out.println("Removed plant at (" + x + ", " + y + ").");
        } else {
            System.out.println("No plant to remove at (" + x + ", " + y + ").");
        }
    }
    private Plant createPlant(PlantType plantType, int x, int y) {
        switch (plantType) {
            case PEASHOOTER:
                return new Peashooter(x, y, gameMap);
            case SUNFLOWER:
                return new Sunflower(x, y, gameMap);
            case WALLNUT:
                return new Wallnut(x, y, gameMap);
            case SQUASH:
                return new Squash(x, y, gameMap);
            case REPEATER:
                return new Repeater(x, y, gameMap);
            case SNOWPEA:
                return new Snowpea(x, y, gameMap);
            case CHOMPER:
                return new Chomper(x, y, gameMap);
            case LILYPAD:
                return new Lilypad(x, y, gameMap);
            case TANGLEKELP:
                return new TangleKelp(x, y, gameMap);
            case JALAPENO:
                return new Jalapeno(x, y, gameMap);
            default:
                return null;
        }
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    private void showStatus() {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
    
        System.out.println(ANSI_YELLOW + "╔═════════════════════════╗");
        System.out.println("║      Current Status     ║");
        System.out.println("╠═════════════════════════╣");
        System.out.println("║ Deck                    ║");
        System.out.println("╠════╦════════════════════╣");
    
        for (int i = 0; i < inventory.getDeck().getPlantTypes().size(); i++) {
            PlantType plantType = inventory.getDeck().getPlantTypes().get(i);
            String plantName = (plantType != null) ? plantType.toString() : "[Empty]";
            System.out.printf("║ %-2d ║ %-18s ║\n", (i + 1), plantName);
        }
    
        System.out.println("╠════╩════════════════════╣");
        System.out.printf("║ Suns: %-17d ║\n", gameMap.getCurrentSuns());
        System.out.printf("║ Time: %-17d ║\n", gameMap.getCurrentTime());
        System.out.println("╚═════════════════════════╝" + ANSI_RESET);
    }
    
}
