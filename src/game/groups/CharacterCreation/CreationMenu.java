package game.groups.CharacterCreation;

import game.Logic.CharacterParameters;
import game.utils.FadableButton;
import game.utils.PointCounter;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CreationMenu {

    private VBox vBox;
    private NameChanger nameChanger;
    private ParameterChanger speechChanger;
    private ParameterChanger cunnungChanger;
    private ParameterChanger mentalityChanger;
    private Label remainingLabel;
    private FadeTransition fadeIn;
    private FadeTransition fadeOut;

    public CreationMenu(PointCounter counter, FadableButton fbutton) {

        nameChanger = new NameChanger(fbutton);
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
        vBox.setOpacity(0.0);

        fadeIn = new FadeTransition(Duration.seconds(1.5), vBox);
        fadeOut = new FadeTransition(Duration.seconds(1.5), vBox);

    }

    public void setFont(Font font){
        nameChanger.setFont(font);
        speechChanger.setFont(font);
        cunnungChanger.setFont(font);
        mentalityChanger.setFont(font);
        remainingLabel.setFont(font);
    }

    public void appear(){
        vBox.setOpacity(0.0);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(2.0);
        fadeIn.playFromStart();
    }

    public void dissapear(){
        vBox.setOpacity(1.0);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.playFromStart();
    }

    public VBox getMenuBox() {
        return vBox;
    }

}
