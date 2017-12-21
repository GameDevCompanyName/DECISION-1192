package game.groups.MainGame;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

import java.io.File;

public class AvatarPane {

    VBox box;
    Button avatarBox;
    ImageView avatar;
    Image defaultImage;

    public AvatarPane(){
        box = new VBox();
        box.setAlignment(Pos.CENTER);

        defaultImage = new Image(new File("src/resources/images/AVATARS/LOADING.gif").toURI().toString());

        avatarBox = new Button();
        avatar = new ImageView();
        avatarBox.setGraphic(avatar);

        box.setStyle("-fx-border-color: black");

        avatarBox.setMinWidth(500.0);
        avatarBox.setMaxHeight(500.0);

        box.getChildren().add(avatarBox);
    }

    public Node getBox() {
        return box;
    }



}
