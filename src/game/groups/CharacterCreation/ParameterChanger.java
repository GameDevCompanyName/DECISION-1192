package game.groups.CharacterCreation;

import game.Logic.CharacterParameters;
import game.utils.PointCounter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ParameterChanger {

    private HBox boxParameter;
    private CharacterParameters parameter;

    private Button plus;
    private Label parameterName;
    private int valueCounter;
    private Label counterLabel;
    private Button minus;

    private PointCounter counter;


    public ParameterChanger(PointCounter counter, CharacterParameters parameter) {

        this.counter = counter;

        plus = new Button("+");
        this.parameter = parameter;
        parameterName = new Label(this.parameter.getName() + ": ");
        valueCounter = 0;
        counterLabel = new Label(Integer.toString(valueCounter));
        minus = new Button("-");

        boxParameter = new HBox();
        boxParameter.getChildren().addAll(minus, parameterName, counterLabel, plus);
        boxParameter.setSpacing(10);
        boxParameter.setAlignment(Pos.CENTER);

        minus.setDisable(true);
        minus.setBackground(Background.EMPTY);
        plus.setBackground(Background.EMPTY);

        plus.setOnMouseClicked(clicked -> {
            addParam();
        });

        minus.setOnMouseClicked(clicked -> {
            substrParam();
        });

        counter.addToListener(this);

    }

    private void substrParam() {
        if (valueCounter > 0){
            valueCounter--;
            counter.plus();
            updateCounter();
            plus.setDisable(false);
        }
        if (valueCounter == 0){
            minus.setDisable(true);
        }
    }

    private void addParam() {
        if (counter.getPoints() > 0){
            valueCounter++;
            counter.sub();
            updateCounter();
            minus.setDisable(false);
        }
        if (counter.getPoints() == 0){
            plus.setDisable(true);
        }
    }

    private void updateCounter() {
        counterLabel.setText(Integer.toString(valueCounter));
    }

    public void setFont(Font font){
        plus.setFont(font);
        parameterName.setFont(font);
        counterLabel.setFont(font);
        minus.setFont(font);
    }

    public HBox getHBox(){
        return boxParameter;
    }

    public void enableMinus(){
        minus.setDisable(false);
    }

    public void enablePlus(){
        plus.setDisable(false);
    }

    public void disablePlus() {
        plus.setDisable(true);
    }

    public int getParamValue() {
        return valueCounter;
    }
}
