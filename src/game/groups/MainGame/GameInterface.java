package game.groups.MainGame;

import game.GameController;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GameInterface {

    AnchorPane interfacePane;
    AvatarPane playerLeft;
    AvatarPane strangerRight;
    InteractivePane interactive;

    public GameInterface(GameController controller){
        playerLeft = new AvatarPane();
        strangerRight = new AvatarPane();
        interactive = new InteractivePane();

        interfacePane = new AnchorPane();
        interfacePane.getChildren().addAll(
                playerLeft.getBox(),
                interactive.getBox(),
                strangerRight.getBox()
        );
        AnchorPane.setLeftAnchor(playerLeft.getBox(), 0.0);
        AnchorPane.setRightAnchor(strangerRight.getBox(), 0.0);
        AnchorPane.setTopAnchor(interactive.getBox(), 0.0);

        interfacePane.setStyle("-fx-border-color: red");
        interfacePane.prefWidthProperty().bind(controller.getGameScene().widthProperty());
        interfacePane.prefHeightProperty().bind(controller.getGameScene().heightProperty());
    }


    public Node getBox() {
        return interfacePane;
    }
}
