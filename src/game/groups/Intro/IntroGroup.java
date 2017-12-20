package game.groups.Intro;

import game.GameController;
import game.groups.StartableGroup;
import game.groups.GameGroups;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class IntroGroup extends StartableGroup {

    private Text startGameText;
    GameController controller;

    @Override
    public void start(GameController controller) {

        globalGroup = new Pane();
        Scene scene = controller.getGameScene();

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                startGameText.setTranslateX(scene.getWidth()/2 - startGameText.getLayoutBounds().getWidth()/2);
                startGameText.setTranslateY(scene.getHeight()/2 - startGameText.getLayoutBounds().getHeight()/2);
                int fontSize = (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 14);
                startGameText.setFont(new Font("Courier New", fontSize));
            }
        };

        scene.widthProperty().addListener(changeListener);
        scene.heightProperty().addListener(changeListener);

        this.controller = controller;

        startGameText = new Text("");
        startGameText.setTextAlignment(TextAlignment.CENTER);
        int fontSize = (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 20);
        startGameText.setFont(new Font("Courier New", fontSize));
        startGameText.setFill(Color.BLACK);
        startGameText.setTranslateX(scene.getWidth()/2 - startGameText.getLayoutBounds().getWidth()/2);
        startGameText.setTranslateY(scene.getHeight()/2 - startGameText.getLayoutBounds().getHeight()/2);

        globalGroup.getChildren().add(startGameText);

        controller.getFadeIn().setOnFinished(e ->
        {
            textAppend(startGameText, "ПРОСНУТЬСЯ", 2000);
        });

        changeListener.changed(null, null, null);
        controller.fadeIn();

    }

    public void textAppend(Text text, String string, int time) {

        String content = string;

        final Animation animation = new Transition() {
            {
                Duration durationFade = Duration.millis(time);
                Duration durationTyping = Duration.millis(time * 2);
                Duration durationScale = Duration.millis(time * 6);

                FadeTransition fadeTransition = new FadeTransition(durationFade, text);
                fadeTransition.setFromValue(0.0);
                fadeTransition.setToValue(1.0);

                ScaleTransition scaleTransition = new ScaleTransition(durationScale, text);
                scaleTransition.setFromX(0.6);
                scaleTransition.setToX(1.3);
                scaleTransition.setFromY(0.8);
                scaleTransition.setToY(1.2);

                scaleTransition.playFromStart();
                fadeTransition.playFromStart();

                setCycleDuration(durationTyping);
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                text.setText(content.substring(0, n));
                startGameText.setTranslateX(controller.getGameScene().getWidth()/2 - startGameText.getLayoutBounds().getWidth()/2);
                startGameText.setTranslateY(controller.getGameScene().getHeight()/2 - startGameText.getLayoutBounds().getHeight()/2);
                if (text.getText() == string){
                    text.setOnMousePressed(event -> {
                        controller.getFadeOut().setOnFinished(event1 -> {
                            controller.changeGroup(GameGroups.CHAR_CREATION);
                        });
                        controller.fadeOut();
                    });
                }
            }

        };

        animation.play();

    }

    public static Pane startCharacterCreation(GameController controller) {

        IntroGroup introGroup = new IntroGroup();
        introGroup.start(controller);
        Pane group = introGroup.globalGroup;
        return group;

    }

    public static Pane startIntro(GameController controller) {
        IntroGroup introGroup = new IntroGroup();
        introGroup.start(controller);
        return introGroup.globalGroup;
    }

}
