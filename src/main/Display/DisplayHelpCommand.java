package src.main.Display;
import src.main.Interface.Command;
import src.main.Game;

public class DisplayHelpCommand implements Command {
    private Game game;

    public DisplayHelpCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.displayHelp();
    }
}
