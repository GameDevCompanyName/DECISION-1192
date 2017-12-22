package game.groups.MainGame;

import java.util.Random;

import game.GameTexts;
import game.utils.Constants;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CustomConsole {

    ScrollPane box;
    Label textArea;

    public CustomConsole(GameInterface gameInterface){

        box = new ScrollPane();
        box.prefWidthProperty().bind(gameInterface.getBox().widthProperty().divide(2.6));
        box.prefHeightProperty().bind(gameInterface.getBox().heightProperty().multiply(Constants.CONSOLE_HEIGHT_SCALE));
        box.setBackground(Background.EMPTY);
        box.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        box.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //box.setFitToWidth(true);
        box.setStyle("-fx-background-color: transparent;\n" +
                "-fx-background: transparent;");

        textArea = new Label();
        textArea.setStyle("-fx-border-color: orange");
        textArea.setTextFill(Color.RED);
        textArea.textProperty().addListener(event -> {
            slowScrollToBottom(box);
        });

        box.setContent(textArea);

        texttext(box, textArea);

    }

    private void slowScrollToBottom(ScrollPane scrollPane) {
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(scrollPane.vvalueProperty(), 1)));
        animation.play();
    }

    private void texttext(ScrollPane scrollPane, Label textArea) {
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        null));
        animation.setOnFinished(event -> {
            texttext(scrollPane, textArea);
            textAppend("RJRJ RJRJRJ RAJLSS :UDGFIUW 123123 #$@#@ @#*&@($@# ASKJDHLASUD (#&$@# ");
        });
        animation.play();
    }

    private void textAppend(String text) {
        for (char character: text.toCharArray()) {
            textArea.setText(textArea.getText() + character);
            checkLenght();
        }
    }

    private void checkLenght() {
        if (textArea.getWidth() > box.getWidth()){
            String lolex = textArea.getText();
            int index = 0;
            for (int i = lolex.length() - 1; i > 0; i--){
                if (lolex.charAt(i) == ' '){
                    index = i;
                    break;
                }
            }
            String kekas = lolex.substring(0, index) + "\n" + lolex.substring(index, lolex.length() - 1);
            textArea.setText(kekas);
        }
    }

    public Node getBox() {
        return box;
    }

}
