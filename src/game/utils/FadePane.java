package game.utils;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;

public class FadePane {

    public static double FADE_TIME = 0.5;

    private Rectangle blackie = new Rectangle(Screen.getPrimary().getBounds().getWidth(),
                                              Screen.getPrimary().getBounds().getHeight());

    protected FadeTransition fadeIn = new FadeTransition(Duration.seconds(FADE_TIME), blackie);
    protected FadeTransition fadeOut = new FadeTransition(Duration.seconds(FADE_TIME), blackie);


    public FadePane(Group group){
        group.getChildren().add(blackie);
        blackie.setFill(Color.BLACK);
        blackie.setOpacity(1.0);
    }

    public void fadeIn() {
        blackie.setOpacity(1.0);
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.0);
        fadeIn.playFromStart();
    }

    public void fadeOut() {
        blackie.setOpacity(0.0);
        fadeOut.setFromValue(0.0);
        fadeOut.setToValue(1.0);
        fadeOut.playFromStart();
    }

    public FadeTransition getFadeIn() {
        return fadeIn;
    }

    public FadeTransition getFadeOut() {
        return fadeOut;
    }
}
