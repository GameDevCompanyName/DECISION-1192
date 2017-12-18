package game.groups.CharacterCreation;

import game.Logic.CharacterParameters;
import game.utils.PointCounter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CreationMenu {

    private VBox vBox;
    private NameChanger nameChanger;
    private ParameterChanger speechChanger;
    private ParameterChanger cunnungChanger;
    private ParameterChanger mentalityChanger;
    private Label remainingLabel;

    public CreationMenu(PointCounter counter) {

        nameChanger = new NameChanger();
        speechChanger = new ParameterChanger(counter, CharacterParameters.SPEECH);
        cunnungChanger = new ParameterChanger(counter, CharacterParameters.CUNNING);
        mentalityChanger = new ParameterChanger(counter, CharacterParameters.MENTALITY);
        remainingLabel = new Label(Integer.toString(counter.getPoints()));

        counter.addOutputField(remainingLabel);

        vBox = new VBox();
        vBox.getChildren().addAll(nameChanger.getHBox(),
                speechChanger.getHBox(),
                cunnungChanger.getHBox(),
                mentalityChanger.getHBox(),
                remainingLabel);

        vBox.setAlignment(Pos.CENTER);

    }

    public void setFont(Font font){
        nameChanger.setFont(font);
        speechChanger.setFont(font);
        cunnungChanger.setFont(font);
        mentalityChanger.setFont(font);
        remainingLabel.setFont(font);
    }

    public VBox getMenuBox() {
        return vBox;
    }
}
