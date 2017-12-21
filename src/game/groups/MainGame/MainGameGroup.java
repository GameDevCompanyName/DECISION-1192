package game.groups.MainGame;

import game.GameController;
import game.Logic.Character;
import game.groups.StartableGroup;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainGameGroup extends StartableGroup {

    Character player;
    GameController controller;
    Scene scene;

    @Override
    public void start(GameController controller) {

        this.controller = controller;
        globalGroup = new Pane();
        scene = controller.getGameScene();
        player = controller.getCharacter();

        GameInterface gameInterface = new GameInterface(controller);
        globalGroup.getChildren().add(gameInterface.getBox());

        controller.getFadeIn().setOnFinished(event -> {
        });

        controller.fadeIn();

    }

    public static Pane startMainGame(GameController controller){

        MainGameGroup mainGame = new MainGameGroup();
        mainGame.start(controller);
        return mainGame.globalGroup;

    }

}
