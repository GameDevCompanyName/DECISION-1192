package game;

import game.groups.*;
import game.groups.CharacterCreation.CharacterCreationGroup;
import game.utils.FadePane;
import game.utils.GameState;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameController {

    private final Stage gameStage;
    private final Scene gameScene;
    private final Group totalGroup;

    private Group activeGroup;

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
        Group intro = IntroGroup.startIntro(this);
        activeGroup = intro;
        totalGroup.getChildren().add(intro);

    }

    public void runIntro(){
        Group intro = IntroGroup.startIntro(this);
        activeGroup = intro;
        totalGroup.getChildren().add(intro);
    }

    private void runCharCreation() {
        Group creation = CharacterCreationGroup.startCharacterCreation(this);
        activeGroup = creation;
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

        totalGroup.getChildren().remove(activeGroup);

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
