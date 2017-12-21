package game.Logic;

import javafx.scene.image.Image;

import java.io.File;
import java.util.Random;

public class Character {

    private String name;
    private String ID;
    private Image avatar;
    private int speech;
    private int cunning;
    private int mentality;

    public Character(String name, int speech, int cunning, int mentality) {
        this.avatar = new Image(new File("src/resources/images/AVATARS/MAIN_CHARACTER.gif").toURI().toString());
        this.name = name;
        ID = Integer.toString(new Random().nextInt(100000));
        this.speech = speech;
        this.cunning = cunning;
        this.mentality = mentality;
    }

    public String getName() {
        return name;
    }

    public String getID(){
        return ID;
    }

    public int getSpeech() {
        return speech;
    }

    public int getCunning() {
        return cunning;
    }

    public int getMentality() {
        return mentality;
    }

    public Image getAvatar() {
        return avatar;
    }
}
