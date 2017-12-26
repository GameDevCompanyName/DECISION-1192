package game.groups.MainGame;

import TextGameOld.igorlo.InteractiveConsole;
import game.utils.Constants;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.io.StringBufferInputStream;

public class ChoiceMaker {

    VBox box;
    TextField input;
    Button confirm;
    InteractiveConsole.ChoiceGetter choiceGetter;
    Thread getterThread;
    InputStream stream = new StringBufferInputStream("ХЕЙ");

    public ChoiceMaker(GameInterface gameInterface){

        box = new VBox();
        box.prefWidthProperty().bind(gameInterface.getBox().widthProperty().divide(2.6));
        box.prefHeightProperty().bind(gameInterface.getBox().heightProperty().multiply(1 - Constants.CONSOLE_HEIGHT_SCALE));

        confirm = new Button("подтвердить");
        confirm.setDisable(true);
        confirm.setOnMouseClicked(event -> {
            choiceGetter.setInputStream(new StringBufferInputStream(input.getText()));
        });

        input = new TextField();
        input.textProperty().addListener(event -> {
            if (input.getText().isEmpty())
                confirm.setDisable(true);
            else
                confirm.setDisable(false);
        });

        box.getChildren().addAll(input, confirm);

    }

    public Pane getBox() {
        return box;
    }

    public void setChoiceGetter(InteractiveConsole.ChoiceGetter choiceGetter) {
        this.choiceGetter = choiceGetter;
        this.getterThread = choiceGetter.getThread();
    }

    public InputStream getStream() {
        return stream;
    }
}
