package game.groups.MainGame;

import game.utils.Constants;
import javafx.scene.layout.Pane;

public class ChoiceMaker {

    Pane box;

    public ChoiceMaker(GameInterface gameInterface){

        box = new Pane();
        box.prefWidthProperty().bind(gameInterface.getBox().widthProperty().divide(2.6));
        box.prefHeightProperty().bind(gameInterface.getBox().heightProperty().multiply(1 - Constants.CONSOLE_HEIGHT_SCALE));

    }

    public Pane getBox() {
        return box;
    }
}
