package game.groups.MainGame;

import game.GameController;
import game.Logic.Character;
import game.groups.StartableGroup;
import game.utils.FadePane;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

import java.io.File;

public class MainGameGroup extends StartableGroup {

    Character player;
    GameController controller;
    Scene scene;
    StackPane backgrounds;
    ImageView backGrooundImageBack;
    ImageView backGroundImageFront;
    FadePane backgroundFader;

    @Override
    public void start(GameController controller) {

        this.controller = controller;
        globalGroup = new Pane();
        scene = controller.getGameScene();
        player = controller.getCharacter();

        backGroundImageFront = new ImageView(new Image(new File("src/resources/images/BACKGROUND.gif").toURI().toString()));
        backGroundImageFront.scaleXProperty().bind(scene.widthProperty().divide(400));
        backGroundImageFront.scaleYProperty().bind(scene.widthProperty().divide(400));

        backgrounds = new StackPane();
        backgrounds.prefWidthProperty().bind(scene.widthProperty());
        backgrounds.prefHeightProperty().bind(scene.heightProperty());

        backgrounds.setAlignment(Pos.CENTER);
        backgrounds.getChildren().add(backGroundImageFront);

        globalGroup.getChildren().add(backgrounds);

        backgroundFader = new FadePane(5);
        globalGroup.getChildren().add(backgroundFader.getNode());

        GameInterface gameInterface = new GameInterface(controller);
        globalGroup.getChildren().add(gameInterface.getBox());

        controller.getFadeIn().setOnFinished(event -> {
            gameInterface.playerLeft.putCharacter(player);
        });

        controller.fadeIn();

        backgroundFader.fadeIn();

    }

    public static Pane startMainGame(GameController controller){

        MainGameGroup mainGame = new MainGameGroup();
        mainGame.start(controller);
        return mainGame.globalGroup;

    }

}
