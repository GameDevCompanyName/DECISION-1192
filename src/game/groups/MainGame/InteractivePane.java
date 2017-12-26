package game.groups.MainGame;

import TextGameOld.igorlo.InteractiveConsole;
import game.utils.Constants;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.InputStream;

public class InteractivePane {

    VBox box;
    CustomConsole console;
    ChoiceMaker choiceMaker;

    public InteractivePane(GameInterface gameInterface) {

        box = new VBox();
        box.prefWidthProperty().bind(gameInterface.getBox().widthProperty().multiply(Constants.CONSOLE_WIDTH_SCALE));
        console = new CustomConsole(gameInterface);
        choiceMaker = new ChoiceMaker(gameInterface);


        box.getChildren().add(console.getBox());
        box.getChildren().add(choiceMaker.getBox());

    }

    public Node getBox() {
        return box;
    }

    public void appendText(String wordOfGod) {
        console.textAppend(wordOfGod);
    }

    public void setChoiceGetter(InteractiveConsole.ChoiceGetter choiceGetter) {
        choiceMaker.setChoiceGetter(choiceGetter);
    }

    public InputStream getStream() {
        return choiceMaker.getStream();
    }
}
