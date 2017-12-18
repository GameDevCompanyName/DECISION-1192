package game.utils;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;

public class FadePane {

    private Rectangle blackie = new Rectangle(Screen.getPrimary().getBounds().getWidth(),
                                              Screen.getPrimary().getBounds().getHeight());

    protected FadeTransition fade = new FadeTransition(Duration.seconds(1), blackie);

    public FadePane(Group group){

        group.getChildren().add(blackie);
        blackie.setFill(Color.BLACK);
        blackie.setOpacity(1.0);

    }

    public void fadeIn() {
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.playFromStart();
    }

    public void fadeOut() {
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.playFromStart();
    }

    public FadeTransition getFade() {
        return fade;
    }
}
