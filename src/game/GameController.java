package game;

import game.Logic.Character;
import game.groups.*;
import game.groups.CharacterCreation.CharacterCreationGroup;
import game.groups.Intro.IntroGroup;
import game.groups.MainGame.MainGameGroup;
import game.groups.StartLoading.StartLoadingGroup;
import game.utils.FadePane;
import game.utils.GameState;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class GameController {

    private final Stage gameStage;
    private final Scene gameScene;
    private final Group totalGroup;
    private final MediaPlayer musicPlayer = initPlayer();

    private Character character;

    private Pane activePane;

    private GameState state;

    private FadePane fade;


    public GameController(Stage stage) {
        gameStage = stage;
        totalGroup = new Group();
        fade = new FadePane();
        totalGroup.getChildren().add(fade.getNode());
        gameScene = new Scene(totalGroup,
                Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight(),
                Color.WHITE);
        state = GameState.INTRO;
    }

    private MediaPlayer initPlayer() {
        String uriString = new File("src/resources/music/Ambient_Cycle.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.setVolume(0.5);
        return player;
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

    private void runMainGame() {
        Pane mainGame = MainGameGroup.startMainGame(this);
        activePane = mainGame;
        totalGroup.getChildren().add(mainGame);
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
            case MAIN_GAME:
                runMainGame();
                break;
        }

    }

    public void playMusic(double secs){
        musicPlayer.setVolume(0.03);
        musicPlayer.play();
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.setOnRepeat(() -> {
            playMusic(0.2);
        });
        Timeline musicFade = new Timeline();
        musicFade.getKeyFrames().add(new KeyFrame(Duration.seconds(secs),
                new KeyValue(musicPlayer.volumeProperty(), 0.34)));
        musicFade.play();
    }

    public void stopMusic(){
        musicPlayer.stop();
    }

    public void createCharacter(Character character){
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

}