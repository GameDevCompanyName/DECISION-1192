package game.Logic;

import javafx.scene.image.Image;

import java.io.File;

public class Stranger {

    String name;
    Image avatar;
    Attitude attitude;

    public Stranger(String name, String avatarName, Attitude attitude){
        this.name = name;
        this.avatar = new Image(new File("src/resources/images/AVATARS/" + avatarName + ".gif").toURI().toString());
        this.attitude = attitude;
    }

}
