package game.groups;

import game.GameController;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class IntroGroup {

    private Text startGameText;
    private Group globalGroup;
    GameController controller;

    public void start(GameController controller) {

        globalGroup = new Group();
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

        controller.getFade().setOnFinished(e ->
        {
            animateText(startGameText, "НАЧАТЬ", 2000);
        });

        controller.fadeIn();

    }

    public void animateText(Text text, String string, int time) {

        String content = string;

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(time));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                text.setText(content.substring(0, n));
                startGameText.setTranslateX(controller.getGameScene().getWidth()/2 - startGameText.getLayoutBounds().getWidth()/2);
                startGameText.setTranslateY(controller.getGameScene().getHeight()/2 - startGameText.getLayoutBounds().getHeight()/2);
                if (text.getText() == string){
                    text.setOnMousePressed(event -> {
                        controller.getFade().setOnFinished(event1 -> {
                            controller.changeGroup(GameGroups.CHAR_CREATION);
                        });
                        controller.fadeOut();
                    });
                }
            }
        };

        animation.play();

    }

    public static Group startCharacterCreation(GameController controller) {

        IntroGroup introGroup = new IntroGroup();
        introGroup.start(controller);
        Group group = introGroup.globalGroup;
        return group;

    }

    public static Group startIntro(GameController controller) {
        IntroGroup introGroup = new IntroGroup();
        introGroup.start(controller);
        return introGroup.globalGroup;
    }

}
