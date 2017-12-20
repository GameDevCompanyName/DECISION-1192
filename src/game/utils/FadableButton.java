package game.utils;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class FadableButton extends Button {

    public FadableButton(String text, Boolean visibleAtStart){

        super(text);

        if (visibleAtStart)
            this.setOpacity(1.0);
        else
            this.setOpacity(0.0);
    }

    FadeTransition fade = new FadeTransition(Duration.seconds(0.3), this);

    public void appear(){
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();
    }

    public void dissapear(){
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.play();
    }

    public void setFadeDuration(double durationSecs){
        fade.setDuration(Duration.seconds(durationSecs));
    }

}
