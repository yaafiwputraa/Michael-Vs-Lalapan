package src.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import src.main.DesignPattern.*;
import src.main.Display.*;

public class Main {
    private Map<String, Command> commands = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public Main() {
        Game game = new Game();
        commands.put("1", new StartGameCommand(game));
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
            System.out.println("**************************************");
            System.out.println("*                                    *");
            System.out.println("*    Welcome to Michael Vs Lalapan   *");
            System.out.println("*                                    *");
            System.out.println("**************************************");
            System.out.println("*                                    *");
            System.out.println("*             MAIN MENU              *");
            System.out.println("*                                    *");
            System.out.println("*        1. Start                    *");
            System.out.println("*        2. Help                     *");
            System.out.println("*        3. Plants List              *");
            System.out.println("*        4. Zombies List             *");
            System.out.println("*        5. Exit                     *");
            System.out.println("*                                    *");
            System.out.println("**************************************");
            System.out.print("Choose an option: ");
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
