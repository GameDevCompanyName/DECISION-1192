package game;

import game.groups.*;
import game.groups.CharacterCreation.CharacterCreationGroup;
import game.groups.Intro.IntroGroup;
import game.groups.StartLoading.StartLoadingGroup;
import game.utils.FadePane;
import game.utils.GameState;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameController {

    private final Stage gameStage;
    private final Scene gameScene;
    private final Group totalGroup;

    private Pane activePane;

    private GameState state;

    private FadePane fade;


    public GameController(Stage stage) {
        gameStage = stage;
        totalGroup = new Group();
        fade = new FadePane(totalGroup);
        gameScene = new Scene(totalGroup,
                Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight(),
                Color.WHITE);
        state = GameState.INTRO;
    }

    public void startGame() {

        gameStage.setScene(gameScene);
        Pane start = StartLoadingGroup.startStartLoading(this);
        activePane = start;
        totalGroup.getChildren().add(start);

    }

    public void runIntro(){
        Pane intro = IntroGroup.startIntro(this);
        activePane = intro;
        totalGroup.getChildren().add(intro);
    }

    private void runCharCreation() {
        Pane creation = CharacterCreationGroup.startCharacterCreation(this);
        activePane = creation;
        totalGroup.getChildren().add(creation);
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public FadeTransition getFadeIn() {
        return fade.getFadeIn();
    }

    public FadeTransition getFadeOut(){
        return fade.getFadeOut();
    }

    public void fadeIn() {
        fade.fadeIn();
    }

    public void fadeOut() {
        fade.fadeOut();
    }

    public void changeGroup(GameGroups gameGroups) {

        totalGroup.getChildren().remove(activePane);

        switch (gameGroups){
            case INTRO:
                runIntro();
                break;
            case CHAR_CREATION:
                runCharCreation();
                break;
        }

    }

}
