package game.groups;

import game.GameController;
import javafx.scene.layout.Pane;

public abstract class StartableGroup {

    protected Pane globalGroup;

    abstract public void start(GameController controller);

}
