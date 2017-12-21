package game.groups.MainGame;

import game.GameController;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GameInterface {

    BorderPane interfacePane;
    AvatarPane playerLeft;
    AvatarPane strangerRight;
    InteractivePane interactive;

    public GameInterface(GameController controller){

        interfacePane = new BorderPane();

        interfacePane.setStyle("-fx-border-color: red");
        interfacePane.prefWidthProperty().bind(controller.getGameScene().widthProperty());
        interfacePane.prefHeightProperty().bind(controller.getGameScene().heightProperty());

        playerLeft = new AvatarPane(this);
        strangerRight = new AvatarPane(this);
        interactive = new InteractivePane(this);


        interfacePane.setLeft(playerLeft.getBox());
        interfacePane.setRight(strangerRight.getBox());
        interfacePane.setCenter(interactive.getBox());

    }


    public BorderPane getBox() {
        return interfacePane;
    }
}
