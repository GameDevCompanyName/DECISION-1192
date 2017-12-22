package game.groups.MainGame;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class InteractivePane {

    VBox box;
    CustomConsole console;
    ChoiceMaker choiceMaker;

    public InteractivePane(GameInterface gameInterface) {

        box = new VBox();
        box.prefWidthProperty().bind(gameInterface.getBox().widthProperty().divide(2.3));

        console = new CustomConsole(gameInterface);
        choiceMaker = new ChoiceMaker(gameInterface);

        box.getChildren().add(console.getBox());
        box.getChildren().add(choiceMaker.getBox());

    }

    public Node getBox() {
        return box;
    }

}
