package game.groups.CharacterCreation;

import game.GameController;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.File;

public class CharacterCreationGroup {

    private Group globalGroup;
    GameController controller;

    Font mainFont;


    public void start(GameController controller) {

        Scene scene = controller.getGameScene();
        mainFont = Font.font("Courier New", FontWeight.BOLD, (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 18));

        PointCounter counter = new PointCounter(9);
        CreationMenu creationMenu = new CreationMenu(counter);

        VBox menuBox = creationMenu.getMenuBox();

        creationMenu.setFont(mainFont);

        globalGroup = new Group();

        String imageUri = new File("src/resources/images/GLITCH.gif").toURI().toString();
        Image image = new Image(imageUri);
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);

        double coef = Screen.getPrimary().getBounds().getHeight()/image.getHeight() * 1.5;
        scaleCenterImage(imageView, coef);

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                mainFont = Font.font("Courier New", FontWeight.BOLD, (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 18));
                creationMenu.setFont(mainFont);
                menuBox.setTranslateX(scene.getWidth()/2 - menuBox.getLayoutBounds().getWidth()/2);
                menuBox.setTranslateY(scene.getHeight()/2 - menuBox.getLayoutBounds().getHeight()/2);
            }
        };

        scene.widthProperty().addListener(changeListener);
        scene.heightProperty().addListener(changeListener);
        controller.getGameStage().iconifiedProperty().addListener(changeListener);
        controller.getGameStage().onShowingProperty().addListener(changeListener);

        this.controller = controller;

        globalGroup.getChildren().add(imageView);

        menuBox.setOpacity(0.0);
        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), menuBox);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        globalGroup.getChildren().add(menuBox);

        controller.getFade().setOnFinished(e ->
        {
            changeListener.changed(null, null, null);
            fade.play();
        });

        controller.fadeIn();

    }

    private void scaleCenterImage(ImageView imageView, double coef) {
        imageView.setScaleX(coef);
        imageView.setScaleY(coef);
        imageView.setTranslateX(Screen.getPrimary().getBounds().getWidth()/2 - imageView.getImage().getWidth()/2);
        imageView.setTranslateY(Screen.getPrimary().getBounds().getHeight()/2 - imageView.getImage().getHeight()/2);
        imageView.setOpacity(0.7);
    }

    public static Group startCharacterCreation(GameController controller) {

        CharacterCreationGroup characterCreation = new CharacterCreationGroup();
        characterCreation.start(controller);
        return characterCreation.globalGroup;

    }

}
