package game.groups.CharacterCreation;

import game.utils.FadableButton;
import game.utils.LimitedTextField;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class NameChanger {

    private HBox boxName;
    private Label labelName;
    private LimitedTextField fieldName;


    public NameChanger(FadableButton fbutton) {

        labelName = new Label("ИМЯ:");
        fieldName = new LimitedTextField(9);
        fieldName.setBackground(Background.EMPTY);
        fieldName.textProperty().addListener(event -> {
            if (fieldName.getText().isEmpty())
                fbutton.dissapear();
            else
                if (fbutton.getOpacity() == 0.0)
                    fbutton.appear();
        });
        boxName = new HBox();
        boxName.getChildren().addAll(labelName, fieldName);
        boxName.setSpacing(0);
        boxName.setAlignment(Pos.CENTER);
        fieldName.setMaxWidth(labelName.getWidth()*2);

    }

    public void setFont(Font font){
        labelName.setFont(font);
        fieldName.setFont(font);
        fieldName.setMaxWidth(labelName.getWidth()*3.06);
    }

    public String getName(){
        return fieldName.getText();
    }

    public HBox getHBox(){
        return boxName;
    }

}
