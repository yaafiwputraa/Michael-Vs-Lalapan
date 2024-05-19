package src.main.Display;
import src.main.Interface.Command;
import src.main.Game;

public class DisplayPlantsListCommand implements Command {
    private Game game;

    public DisplayPlantsListCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.displayPlantsList();
    }
}
