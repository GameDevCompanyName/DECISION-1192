package game.groups.CharacterCreation;


import javafx.scene.control.Label;

import java.util.ArrayList;

public class PointCounter {

    private int points;
    private ArrayList<ParameterChanger> listener = new ArrayList<>();
    private Label outputField;

    public PointCounter(int points) {
        this.points = points;
    }

    public void sub(){
        points--;
        if (points == 0){
            for (ParameterChanger pc: listener) {
                pc.disablePlus();
            }
        }
        if (outputField != null)
            outputField.setText(Integer.toString(points));
    }

    public void plus(){
        points++;
        if (points > 0){
            for (ParameterChanger pc: listener) {
                pc.enablePlus();
                if (pc.getParamValue() > 0)
                    pc.enableMinus();
            }
        }
        if (outputField != null)
            outputField.setText(Integer.toString(points));
    }

    public int getPoints(){
        return points;
    }

    public void addToListener(ParameterChanger parameterChanger) {
        listener.add(parameterChanger);
    }

    public void addOutputField(Label remainingLabel) {
        this.outputField = remainingLabel;
        outputField.setText(Integer.toString(points));
    }
}
