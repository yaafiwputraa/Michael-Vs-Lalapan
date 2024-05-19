package src.main.Display;

import src.main.Interface.Command;
import src.main.Game;

public class DisplayZombiesListCommand implements Command {
    private Game game;

    public DisplayZombiesListCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.displayZombiesList();
    }
}
