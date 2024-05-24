package src.main;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import src.main.DesignPattern.*;
import src.main.Display.*;
import src.main.Interface.Command;


public class Main {
    private Map<String, Command> commands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private Inventory inventory;
    private Game game;

    public Main() {
        this.game = new Game();
        this.inventory = new Inventory();
        this.game.setInventory(this.inventory);

        commands.put("1", new StartGameCommand(game, inventory));
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

            System.out.println(ANSI_GREEN + " ███▄ ▄███▓ ██▓ ▄████▄   ██░ ██  ▄▄▄      ▓█████  ██▓        ██▒   █▓ ██████     ██▓    ▄▄▄       ██▓    ▄▄▄       ██▓███   ▄▄▄      ███▄    █ " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "▓██▒▀█▀ ██▒▓██▒▒██▀ ▀█  ▓██░ ██▒▒████▄    ▓█   ▀ ▓██▒       ▓██░   █▒██    ▒    ▓██▒   ▒████▄    ▓██▒   ▒████▄    ▓██░  ██▒▒████▄    ██ ▀█   █ " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "▓██    ▓██░▒██▒▒▓█    ▄ ▒██▀▀██░▒██  ▀█▄  ▒███   ▒██░        ▓██  █▒░ ▓██▄      ▒██░   ▒██  ▀█▄  ▒██░   ▒██  ▀█▄  ▓██░ ██▓▒▒██  ▀█▄ ▓██  ▀█ ██▒" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "▒██    ▒██ ░██░▒▓▓▄ ▄██▒░▓█ ░██ ░██▄▄▄▄██ ▒▓█  ▄ ▒██░         ▒██ █░░ ▒   ██▒   ▒██░   ░██▄▄▄▄██ ▒██░   ░██▄▄▄▄██ ▒██▄█▓▒ ▒░██▄▄▄▄██▓██▒  ▐▌██▒" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "▒██▒   ░██▒░██░▒ ▓███▀ ░░▓█▒░██▓ ▓█   ▓██▒░▒████▒░██████▒      ▒▀█░ ▒██████▒▒   ░██████▒▓█   ▓██▒░██████▒▓█   ▓██▒▒██▒ ░  ░ ▓█   ▓██▒██░   ▓██░" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "░ ▒░   ░  ░░▓  ░ ░▒ ▒  ░ ▒ ░░▒░▒ ▒▒   ▓▒█░░░ ▒░ ░░ ▒░▓  ░      ░ ▐░ ▒ ▒▓▒ ▒ ░   ░ ▒░▓  ░▒▒   ▓▒█░░ ▒░▓  ░▒▒   ▓▒█░▒▓▒░ ░  ░ ▒▒   ▓▒█░ ▒░   ▒ ▒ " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "░  ░      ░ ▒ ░  ░  ▒    ▒ ░▒░ ░  ▒   ▒▒ ░ ░ ░  ░░ ░ ▒  ░      ░ ░░ ░ ░▒  ░ ░   ░ ░ ▒  ░ ▒   ▒▒ ░░ ░ ▒  ░ ▒   ▒▒ ░░▒ ░       ▒   ▒▒ ░ ░░   ░ ▒░" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "░      ░    ▒ ░░         ░  ░░ ░  ░   ▒      ░     ░ ░           ░░ ░  ░  ░       ░ ░    ░   ▒     ░ ░    ░   ▒   ░░         ░   ▒     ░   ░ ░ " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "       ░    ░  ░ ░       ░  ░  ░      ░  ░   ░  ░    ░  ░         ░       ░         ░  ░     ░  ░    ░  ░     ░  ░               ░  ░        ░ " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "               ░                                                 ░                                                                             " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "╔════════════════════════════════════╗"+ANSI_RESET);
            System.out.println("║                                    ║");
            System.out.println("║             MAIN MENU              ║");
            System.out.println("║                                    ║");
            System.out.println("╠════════════════════════════════════╣");   
            System.out.println("║        1. START                    ║");
            System.out.println("║        2. HELP                     ║");
            System.out.println("║        3. PLANT LIST               ║");
            System.out.println("║        4. ZOMBIE LIST              ║");
            System.out.println("║        5. EXIT                     ║");
            System.out.println(ANSI_GREEN+"╚════════════════════════════════════╝"+ANSI_RESET);
            System.out.print("PILIH OPSI (NOMOR): ");
            String choice = scanner.nextLine();

            Command command = commands.get(choice);
            if (command != null) {
                command.execute();
            } else {
                System.out.println(ANSI_RED+"INPUT Invalid, PILIH YANG NOMOR YANG BENAR!!"+ANSI_RESET);
            }
        }
    }
}

