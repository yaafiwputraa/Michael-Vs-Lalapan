package src.main.DesignPattern;

import src.main.Interface.Command;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Exiting game. Goodbye!");
        System.exit(0);
    }
}

