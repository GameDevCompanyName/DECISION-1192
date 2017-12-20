package game.groups.StartLoading;

import game.GameController;
import game.groups.GameGroups;
import game.groups.StartableGroup;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


public class StartLoadingGroup extends StartableGroup {

    @Override
    public void start(GameController controller) {

        Scene scene = controller.getGameScene();
        globalGroup = new Pane();
        Rectangle rect = new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK);
        globalGroup.getChildren().add(rect);

        Label consoleText = new Label("");
        consoleText.setFont(Font.font("Courier New", FontWeight.NORMAL, (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 75)));
        consoleText.setTextFill(Color.LIGHTGREEN);
        globalGroup.getChildren().add(consoleText);

        controller.getFadeIn().setOnFinished((ActionEvent event) -> {
            animateText(consoleText, GameTexts.LOADING_TEXT, 5000, controller);
        });

        controller.fadeIn();

    }

    public void animateText(Label label, String string, int time, GameController controller) {

        String content = string;

        final Animation animation = new Transition() {

            {
                setCycleDuration(Duration.millis(time));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                label.setText(content.substring(0, n));
                this.setOnFinished(event -> {
                    controller.changeGroup(GameGroups.INTRO);
                });
            }
        };

        animation.play();

    }

    public static Pane startStartLoading(GameController controller) {

        StartLoadingGroup startGroup = new StartLoadingGroup();
        startGroup.start(controller);
        return startGroup.globalGroup;

    }

}
