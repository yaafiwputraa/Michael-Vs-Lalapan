package src.main;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import src.main.DesignPattern.*;
import src.main.Display.*;
import src.main.Interface.Command;

public class Main {
    private Map<String, Command> commands = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    private Inventory inventory = new Inventory();
    private Deck deck = new Deck();

    public Main() {
        Game game = new Game();
        commands.put("1", new StartGameCommand(game, inventory, deck));
        commands.put("2", new DisplayHelpCommand(game));
        commands.put("3", new DisplayPlantsListCommand(game));
        commands.put("4", new DisplayZombiesListCommand(game));
        commands.put("5", new ExitCommand());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.displayMainMenu();
    }

    private void displayMainMenu() {
        
        while (true) {
            final String ANSI_GREEN = "\u001B[32m";
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_RED = "\u001B[31m";
            final String ANSI_PURPLE = "\u001B[35m";
            final String ANSI_BRONZE = "\u001B[33m";
            final String ANSI_BLUE = "\u001B[34m";

            System.out.println(ANSI_GREEN + " ███▄ ▄███▓ ██▓ ▄████▄   ██░ ██  ▄▄▄      ▓█████  ██▓        ██▒   █▓ ██████     ██▓    ▄▄▄       ██▓    ▄▄▄       ██▓███   ▄▄▄      ███▄    █ " + ANSI_RESET);
            System.out.println(ANSI_RED +   "▓██▒▀█▀ ██▒▓██▒▒██▀ ▀█  ▓██░ ██▒▒████▄    ▓█   ▀ ▓██▒       ▓██░   █▒██    ▒    ▓██▒   ▒████▄    ▓██▒   ▒████▄    ▓██░  ██▒▒████▄    ██ ▀█   █ " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "▓██    ▓██░▒██▒▒▓█    ▄ ▒██▀▀██░▒██  ▀█▄  ▒███   ▒██░        ▓██  █▒░ ▓██▄      ▒██░   ▒██  ▀█▄  ▒██░   ▒██  ▀█▄  ▓██░ ██▓▒▒██  ▀█▄ ▓██  ▀█ ██▒" + ANSI_RESET);
            System.out.println(ANSI_RED +   "▒██    ▒██ ░██░▒▓▓▄ ▄██▒░▓█ ░██ ░██▄▄▄▄██ ▒▓█  ▄ ▒██░        ▒██ █░░ ▒   ██▒    ▒██░   ░██▄▄▄▄██ ▒██░   ░██▄▄▄▄██ ▒██▄█▓▒ ▒░██▄▄▄▄██▓██▒  ▐▌██▒" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "▒██▒   ░██▒░██░▒ ▓███▀ ░░▓█▒░██▓ ▓█   ▓██▒░▒████▒░██████▒     ▒▀█░ ▒██████▒▒    ░██████▒▓█   ▓██▒░██████▒▓█   ▓██▒▒██▒ ░  ░ ▓█   ▓██▒██░   ▓██░" + ANSI_RESET);
            System.out.println(ANSI_RED +   "░ ▒░   ░  ░░▓ ░ ░▒ ▒  ░ ▒ ░░▒░▒ ▒▒   ▓▒█░░░ ▒░ ░░ ▒░▓  ░      ░ ▐░ ▒ ▒▓▒ ▒ ░   ░ ▒░▓  ░▒▒   ▓▒█░░ ▒░▓  ░▒▒   ▓▒█░▒▓▒░ ░  ░ ▒▒   ▓▒█░ ▒░   ▒ ▒ " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "░  ░      ░ ▒ ░  ░  ▒    ▒ ░▒░ ░  ▒   ▒▒ ░ ░ ░  ░░ ░ ▒  ░      ░ ░░ ░ ░▒  ░ ░   ░ ░ ▒  ░ ▒   ▒▒ ░░ ░ ▒  ░ ▒   ▒▒ ░░▒ ░       ▒   ▒▒ ░ ░░   ░ ▒░" + ANSI_RESET);
            System.out.println(ANSI_RED +   "░      ░    ▒ ░░         ░  ░░ ░  ░   ▒      ░     ░ ░           ░░ ░  ░  ░       ░ ░    ░   ▒     ░ ░    ░   ▒   ░░         ░   ▒     ░   ░ ░ " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "       ░    ░  ░ ░       ░  ░  ░      ░  ░   ░  ░    ░  ░         ░       ░         ░  ░     ░  ░    ░  ░     ░  ░               ░  ░        ░ " + ANSI_RESET);
            System.out.println(ANSI_RED + "                 ░                                                 ░                                                                               " + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "┌──────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│" + ANSI_BLUE + "                                                                          " + ANSI_PURPLE + "│");
            System.out.println("│" + ANSI_BRONZE+ "                      Welcome to Michael Vs Lalapan               " + ANSI_PURPLE + "        │");
            System.out.println("│" + ANSI_BLUE + "                                                                          " + ANSI_PURPLE + "│");
            System.out.println("├──────────────────────────────────────────────────────────────────────────┤");
            System.out.println("│" + ANSI_GREEN + "                                MAIN MENU                                " + ANSI_PURPLE + " │");
            System.out.println("│" + ANSI_BLUE + "                                                                          " + ANSI_PURPLE + "│");
            System.out.println("│" + ANSI_RESET + "        1. Start                                                          " + ANSI_PURPLE + "│");
            System.out.println("│" + ANSI_RESET + "        2. Help                                                           " + ANSI_PURPLE + "│");
            System.out.println("│" + ANSI_RESET + "        3. Plants List                                                    " + ANSI_PURPLE + "│");
            System.out.println("│" + ANSI_RESET + "        4. Zombies List                                                   " + ANSI_PURPLE + "│");
            System.out.println("│" + ANSI_RESET + "        5. Exit                                                           " + ANSI_PURPLE + "│");
            System.out.println("│" + ANSI_BLUE + "                                                                          " + ANSI_PURPLE + "│");
            System.out.println("└──────────────────────────────────────────────────────────────────────────┘");
            System.out.print(ANSI_RESET + "Choose an option: ");
            String choice = scanner.nextLine();

            Command command = commands.get(choice);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
