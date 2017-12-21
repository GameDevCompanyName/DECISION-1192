package game.groups.MainGame;

import game.GameController;
import game.Logic.Character;
import game.groups.StartableGroup;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.File;

public class MainGameGroup extends StartableGroup {

    Character player;
    GameController controller;
    Scene scene;
    ImageView backGroundImage;

    @Override
    public void start(GameController controller) {

        this.controller = controller;
        globalGroup = new Pane();
        scene = controller.getGameScene();
        player = controller.getCharacter();

        backGroundImage = new ImageView(new Image(new File("src/resources/images/DEFAULT_BACKGROUND.gif").toURI().toString()));
        //backGroundImage.scaleXProperty().bind(scene.widthProperty().divide(10));
        //backGroundImage.scaleYProperty().bind(scene.widthProperty().divide(10));

        StackPane imagePane = new StackPane();
        imagePane.prefWidthProperty().bind(scene.widthProperty());
        imagePane.prefHeightProperty().bind(scene.heightProperty());

        imagePane.setAlignment(Pos.CENTER);
        imagePane.getChildren().add(backGroundImage);


        GameInterface gameInterface = new GameInterface(controller);
        globalGroup.getChildren().add(gameInterface.getBox());

        controller.getFadeIn().setOnFinished(event -> {
            gameInterface.playerLeft.putCharacter(player);
        });

        controller.fadeIn();

        globalGroup.getChildren().add(imagePane);

    }

    public static Pane startMainGame(GameController controller){

        MainGameGroup mainGame = new MainGameGroup();
        mainGame.start(controller);
        return mainGame.globalGroup;

    }

}
