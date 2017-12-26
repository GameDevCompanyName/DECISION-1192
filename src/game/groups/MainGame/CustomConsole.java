package game.groups.MainGame;

import java.util.Random;

import game.GameTexts;
import game.utils.Constants;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class CustomConsole {

    StackPane mainBox;
    ScrollPane scrollPane;
    VBox textBox;
    Rectangle consoleBackground;
    Font font;

    public CustomConsole(GameInterface gameInterface){

        mainBox = new StackPane();
        textBox = new VBox();
        scrollPane = new ScrollPane();
        font = Font.font("Courier New", FontWeight.LIGHT, 16);

        mainBox.prefWidthProperty().bind(gameInterface.getBox().widthProperty().multiply(Constants.CONSOLE_WIDTH_SCALE));
        mainBox.prefHeightProperty().bind(gameInterface.getBox().heightProperty().multiply(Constants.CONSOLE_HEIGHT_SCALE));
        mainBox.setStyle("-fx-border-color: red");
        mainBox.setAlignment(Pos.CENTER);

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                mainBox.setMaxSize(gameInterface.getBox().getWidth()/2.6,
                        gameInterface.getBox().getHeight()*Constants.CONSOLE_HEIGHT_SCALE);
            }
        };
        gameInterface.getBox().widthProperty().addListener(changeListener);
        gameInterface.getBox().heightProperty().addListener(changeListener);

        consoleBackground = new Rectangle();
        consoleBackground.setStyle("-fx-border-color: blue");
        consoleBackground.widthProperty().bind(mainBox.prefWidthProperty());
        consoleBackground.heightProperty().bind(mainBox.prefHeightProperty());
        consoleBackground.setFill(Color.BLACK);
        consoleBackground.setOpacity(0.6);

        textBox.heightProperty().addListener(event -> {
            slowScrollToBottom();
        });

        scrollPane.setContent(textBox);
        scrollPane.setStyle("-fx-border-color: green");
        scrollPane.prefWidthProperty().bind(mainBox.prefWidthProperty());
        scrollPane.prefHeightProperty().bind(mainBox.prefHeightProperty());
        scrollPane.setBackground(Background.EMPTY);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;\n" +
                "-fx-background: transparent;");

        mainBox.getChildren().addAll(consoleBackground, scrollPane);

        for (int i = 0; i < 100; i++){
            textAppend(GameTexts.randomLoadingText());
        }

    }

    private void slowScrollToBottom() {
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(scrollPane.vvalueProperty(), 1.0)));
        animation.play();
    }

    public void textAppend(String text, MessageType messageType) {

        Label newText = new Label(text);

        newText.setFont(font);
        newText.setWrapText(true);
        newText.setStyle("-fx-border-color: orange");
        newText.maxWidthProperty().bind(scrollPane.widthProperty());

        switch (messageType){

            case DEFAULT:
                newText.setTextFill(Color.LIGHTGREEN);
                break;

        }

        textBox.getChildren().add(newText);

        slowScrollToBottom();

    }

    public void textAppend(String text) {

        textAppend(text, MessageType.DEFAULT);

    }

    public Node getBox() {
        return mainBox;
    }

    private enum MessageType {

        DEFAULT, IMPORTANT

    }

}
