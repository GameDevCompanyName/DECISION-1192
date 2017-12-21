package game.groups.CharacterCreation;

import game.GameController;
import game.groups.GameGroups;
import game.groups.StartableGroup;
import game.utils.FadableButton;
import game.utils.PointCounter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

import java.io.File;

public class CharacterCreationGroup extends StartableGroup {

    GameController controller;

    Font mainFont;

    @Override
    public void start(GameController controller) {

        Scene scene = controller.getGameScene();
        mainFont = Font.font("Courier New", FontWeight.BOLD, (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 18));

        FadableButton continueButton = new FadableButton("Далее...", false);
        continueButton.setFont(mainFont);
        continueButton.setBackground(Background.EMPTY);

        CreationMenu creationMenu = new CreationMenu(new PointCounter(13), continueButton);

        VBox menuBox = creationMenu.getMenuBox();
        creationMenu.setFont(mainFont);

        globalGroup = new Pane();

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
                continueButton.setFont(mainFont);
                continueButton.setTranslateX(scene.getWidth()/2 - continueButton.getLayoutBounds().getWidth()/2);
                continueButton.setTranslateY(scene.getHeight() - continueButton.getLayoutBounds().getHeight());
            }
        };

        scene.widthProperty().addListener(changeListener);
        scene.heightProperty().addListener(changeListener);
        controller.getGameStage().fullScreenProperty().addListener(changeListener);

        this.controller = controller;

        globalGroup.setOpacity(1.0);

        globalGroup.getChildren().addAll(imageView, menuBox, continueButton);

        mainFont = Font.font("Courier New", FontWeight.BOLD, (int) (Math.sqrt(scene.getHeight()*scene.getWidth()) / 18));
        creationMenu.setFont(mainFont);
        menuBox.setTranslateX(scene.getWidth()/2 - menuBox.getLayoutBounds().getWidth()/2);
        menuBox.setTranslateY(scene.getHeight()/2 - menuBox.getLayoutBounds().getHeight()/2);
        continueButton.setFont(mainFont);
        continueButton.setTranslateX(scene.getWidth()/2 - continueButton.getLayoutBounds().getWidth()/2);
        continueButton.setTranslateY(scene.getHeight() - continueButton.getLayoutBounds().getHeight());

        controller.getFadeIn().setOnFinished((ActionEvent e) ->
        {
            creationMenu.appear();
            changeListener.changed(null, null, null);
            continueButton.setOnMouseClicked(event -> {
                controller.fadeOut();
                controller.getFadeOut().setOnFinished(event1 -> {
                    controller.createCharacter(creationMenu.getCharacter());
                    controller.changeGroup(GameGroups.MAIN_GAME);
                });
            });
        });

        controller.fadeIn();

    }

    private void updatePositions(){

    }

    private void scaleCenterImage(ImageView imageView, double coef) {
        imageView.setScaleX(coef);
        imageView.setScaleY(coef);
        imageView.setTranslateX(Screen.getPrimary().getBounds().getWidth()/2 - imageView.getImage().getWidth()/2);
        imageView.setTranslateY(Screen.getPrimary().getBounds().getHeight()/2 - imageView.getImage().getHeight()/2);
        imageView.setOpacity(0.5);
    }

    public static Pane startCharacterCreation(GameController controller) {

        CharacterCreationGroup characterCreation = new CharacterCreationGroup();
        characterCreation.start(controller);
        return characterCreation.globalGroup;

    }

}
