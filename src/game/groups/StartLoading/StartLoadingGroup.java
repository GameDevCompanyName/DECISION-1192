package game.groups.StartLoading;

import game.GameController;
import game.GameTexts;
import game.groups.GameGroups;
import game.groups.StartableGroup;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.File;


public class StartLoadingGroup extends StartableGroup {

    @Override
    public void start(GameController controller) {

        Scene scene = controller.getGameScene();
        globalGroup = new Pane();

        Rectangle rect = new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK);
        globalGroup.getChildren().add(rect);

        String imageUri = new File("src/resources/images/STATIC.gif").toURI().toString();
        Image image = new Image(imageUri);
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);
        double coef = Screen.getPrimary().getBounds().getHeight()/image.getHeight() * 1.5;
        scaleCenterImage(imageView, coef);
        globalGroup.getChildren().add(imageView);

        Label consoleText = new Label("");
        consoleText.setFont(Font.font("Courier New", FontWeight.NORMAL, (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 75)));
        consoleText.setTextFill(Color.LIGHTGREEN);
        globalGroup.getChildren().add(consoleText);

        Text title = new Text(GameTexts.randomLoadingText());
        alignTitle(title, scene);
        title.setOpacity(0.0);
        title.setFill(Color.LIGHTGREEN);
        globalGroup.getChildren().add(title);

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double coef = Screen.getPrimary().getBounds().getHeight()/image.getHeight() * 1.5;
                scaleCenterImage(imageView, coef);
                consoleText.setFont(Font.font("Courier New", FontWeight.NORMAL, (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 75)));
                alignTitle(title, scene);
            }
        };

        scene.widthProperty().addListener(changeListener);
        scene.heightProperty().addListener(changeListener);
        title.scaleXProperty().addListener(changeListener);
        title.scaleYProperty().addListener(changeListener);
        controller.getGameStage().fullScreenProperty().addListener(changeListener);



        changeListener.changed(null, null, null);
        controller.getFadeIn().setOnFinished((ActionEvent event) -> {
            animateText(consoleText, GameTexts.LOADING_TEXT, 500, controller);
            title.setFont(Font.font("Courier New", FontWeight.NORMAL, (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 25)));
        });

        titleAppend(title, 4000);
        controller.fadeIn();

    }

    private void alignTitle(Text text, Scene scene) {
        text.setTranslateX(scene.getWidth()*0.22 - text.getLayoutX()/2);
        text.setTranslateY(scene.getWidth()*0.24 - text.getLayoutY()/2);
        text.setFont(Font.font("Century Gothic", FontWeight.LIGHT, (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 7)));
    }

    private void scaleCenterImage(ImageView imageView, double coef) {
        imageView.setScaleX(coef);
        imageView.setScaleY(coef);
        imageView.setTranslateX(Screen.getPrimary().getBounds().getWidth()/2 - imageView.getImage().getWidth()/2);
        imageView.setTranslateY(Screen.getPrimary().getBounds().getHeight()/2 - imageView.getImage().getHeight()/2);
        imageView.setOpacity(0.1);
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

    public void titleAppend(Text text, int time) {

        Duration durationFade = Duration.millis(time);
        Duration durationScale = Duration.millis(time * 4);

        FadeTransition fadeTransition = new FadeTransition(durationFade, text);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(0.1);

        ScaleTransition scaleTransition = new ScaleTransition(durationScale, text);
        scaleTransition.setFromX(0.85);
        scaleTransition.setToX(1.15);
        scaleTransition.setFromY(0.95);
        scaleTransition.setToY(1.05);

        scaleTransition.playFromStart();
        fadeTransition.playFromStart();

    }

    public static Pane startStartLoading(GameController controller) {

        StartLoadingGroup startGroup = new StartLoadingGroup();
        startGroup.start(controller);
        return startGroup.globalGroup;

    }

}
