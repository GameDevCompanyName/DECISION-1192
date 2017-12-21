package game.groups.MainGame;

import game.Logic.Character;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.File;

public class AvatarPane {

    VBox box;
    Label nameLabel;
    StackPane avatarPane;
    Button avatarButton;
    ImageView imageAvatar;
    ImageView imageLoading;
    ImageView imageDefault;

    public AvatarPane(GameInterface gameInterface){

        box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        box.spacingProperty().bind(gameInterface.getBox().heightProperty().divide(22));
        box.prefWidthProperty().bind(gameInterface.getBox().widthProperty().divide(4.6));

        imageDefault = new ImageView(new Image(new File("src/resources/images/AVATARS/UNKNOWN.gif").toURI().toString()));
        imageLoading = new ImageView(new Image(new File("src/resources/images/AVATARS/LOADING.gif").toURI().toString()));
        imageAvatar = new ImageView();

        avatarButton = new Button();

        imageAvatar.fitHeightProperty().bind(avatarButton.heightProperty().divide(1.05));
        imageAvatar.fitWidthProperty().bind(avatarButton.widthProperty().divide(1.05));
        imageLoading.fitHeightProperty().bind(avatarButton.heightProperty().divide(1.05));
        imageLoading.fitWidthProperty().bind(avatarButton.widthProperty().divide(1.05));
        imageDefault.fitHeightProperty().bind(avatarButton.heightProperty().divide(1.05));
        imageDefault.fitWidthProperty().bind(avatarButton.widthProperty().divide(1.05));

        avatarPane = new StackPane();

        avatarButton.setStyle("-fx-outer-border: linear-gradient(to bottom, #3333ff, #9900cc);\n" +
                "  -fx-background-color: \n" +
                "       -fx-outer-border, \n" +
                "       -fx-body-color;\n" +
                "  -fx-background-insets: 0, 50;\n" +
                "  -fx-background-radius: 6px, 0px;\n");

        avatarPane.maxWidthProperty().bind(box.widthProperty().divide(1.1));
        avatarPane.maxHeightProperty().bind(box.widthProperty().divide(1.1));
        avatarPane.prefWidthProperty().bind(box.widthProperty().divide(1.1));
        avatarPane.prefHeightProperty().bind(box.widthProperty().divide(1.1));

        avatarButton.maxWidthProperty().bind(avatarPane.widthProperty());
        avatarButton.maxHeightProperty().bind(avatarPane.widthProperty());
        avatarButton.prefWidthProperty().bind(avatarPane.widthProperty());
        avatarButton.prefHeightProperty().bind(avatarPane.widthProperty());

        avatarPane.getChildren().add(avatarButton);
        avatarPane.getChildren().addAll(imageAvatar, imageLoading, imageDefault);

        nameLabel = new Label("**********");
        nameLabel.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 50));

        box.setStyle("-fx-border-color: black");
        avatarPane.setStyle("-fx-border-color: green");

        box.getChildren().add(new Pane());
        box.getChildren().add(nameLabel);
        box.getChildren().add(avatarPane);

    }

    public Pane getBox() {
        return box;
    }

    public void putCharacter(Character character){
        putAvatar(character.getAvatar());
        nameLabel.setText(character.getName());
    }

    public void putAvatar(Image avatar) {
        imageAvatar.setImage(avatar);
        imageDefault.setOpacity(0.0);
        Timeline putting = new Timeline();
        putting.getKeyFrames().add(new KeyFrame(Duration.seconds(2.0),
                new KeyValue(imageLoading.visibleProperty(), false)));
        putting.play();
    }

    public void goAwayCharacter(){
        nameLabel.setText("--неизвестно--");
        FadeTransition goaway = new FadeTransition(Duration.seconds(2), imageDefault);
        goaway.setFromValue(0.0);
        goaway.setToValue(1.0);
        goaway.setOnFinished(event -> {
            imageAvatar.setImage(null);
            imageLoading.setVisible(true);
        });
        goaway.play();
    }

}
