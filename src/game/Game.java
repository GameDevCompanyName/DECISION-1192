package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

    private GameController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{

        controller = new GameController(primaryStage);
        primaryStage.setTitle("DECISION#1192");
        primaryStage.setFullScreen(false);
        controller.startGame();
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }

}
