package src.main.DesignPattern;
import src.main.Interface.Command;
import src.main.Game;

public class StartGameCommand implements Command {
    private Game game;

    public StartGameCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.startGame();
    }
}
