package hangman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Date;

public class MyTimer {

    int time = 0;
    public MyTimer(Label lblTime)
    {
        Timeline timerTimelie = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time++;
            lblTime.setText(String.format("%02d:%02d", time / 60, time % 60));
        }));

        timerTimelie.setCycleCount(Timeline.INDEFINITE);
        timerTimelie.play();
    }
}
