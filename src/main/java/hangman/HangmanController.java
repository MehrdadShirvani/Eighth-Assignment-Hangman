package hangman;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import  org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class HangmanController implements Initializable {
    public static final String API_KEY = "ZJl90h5GIP3KTWEuN5pM9A==UcG1frgxjmDAHSEX";
    public ScrollPane scrollPane;
    public TextField Txt;
    private boolean hasWon = false;
    public HBox HBoxLetterContainer;
    public Label Lbl;
    public Line line5;
    public Line line7;
    public Line line8;
    public Line line6;
    public Line line9;
    public Ellipse circleHead;
    public Line line3;
    public Line line4;
    public Line line1;
    public Line line2;
    public BorderPane container;
    List<LetterViewController> letterViewControllers;
    String word = "";
    int wrongAttemptCount = 0;
    boolean lockInput = false;
Stage stage ;
    @FXML

    protected void onThisButtonClick() throws IOException {

    }

    private void drawLine(Line line) {
        scrollPane.requestFocus();
        double targetEndX = line.getEndX();
        double targetEndY = line.getEndY();
        line.setEndX(line.getStartX());
        line.setEndY(line.getStartY());
        AnimationTimer timer = new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if (startTime < 0) {
                    startTime = now;
                }

                double elapsed = (now - startTime) / 1_000_000.0; // Convert to milliseconds

                if (elapsed < 2000) {
                    double progress = elapsed / 2000;
                    double endX = (1 - progress) * line.getStartX() + progress * targetEndX;
                    double endY = (1 - progress) * line.getStartY() + progress * targetEndY;
                    line.setEndX(endX);
                    line.setEndY(endY);
                } else {
                    line.setEndX(targetEndX);
                    line.setEndY(targetEndY);
                    stop();
                }
            }
        };
        line.setVisible(true);
        timer.start();
    }

    private void fadeInEllipse(Ellipse ellipse) {
        AnimationTimer timer = new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if (startTime < 0) {
                    startTime = now;
                }

                double elapsed = (now - startTime) / 1_000_000.0; // Convert to milliseconds

                if (elapsed < 2000) {
                    double progress = elapsed / 2000;
                    ellipse.setOpacity(progress);
                } else {
                    ellipse.setOpacity(1);
                    gameOver();
                    stop();
                }
            }
        };
        timer.start();
    }

    private void gameOver() {
        DatabaseManager.NewGame(new Game(DatabaseManager.currentUser.getUsername(),word, wrongAttemptCount, timer.time, false));
        stage.close();
    }

    MyTimer timer ;
    public void onKeyPressed(KeyEvent keyEvent)
    {
        if(lockInput)
        {
            return;
        }

        if(letterViewControllers != null)
        {
            boolean wrongAttempt = !word.contains(keyEvent.getText());
            if(word.contains(keyEvent.getText()))
            {
                for(LetterViewController controller : letterViewControllers)
                {
                    if (!controller.isLetterShown() && controller.getLetter().equals(keyEvent.getText()))
                    {
                        controller.showLetter();
                    }
                }

                boolean allShown = true;
                for(LetterViewController controller : letterViewControllers)
                {
                    if(!controller.isLetterShown())
                    {
                        allShown = false;
                        break;
                    }
                }
                if(allShown)
                {
                    hasWon = true;
                    lockInput = true;
                    gameWon();
                }
            }

             if(wrongAttempt)
             {
                 drawNext();
             }
        }
    }

    private void gameWon()
    {

        DatabaseManager.NewGame(new Game(DatabaseManager.currentUser.getUsername(),word, wrongAttemptCount, timer.time, true));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stage.close();
    }

    private void drawNext()
    {
        switch (wrongAttemptCount)
        {
            case 0 : drawLine(line1);break;
            case 1 : drawLine(line2);break;
            case 2 : drawLine(line3);break;
            case 3 : drawLine(line4);break;
            case 4 : drawLine(line5);break;
            case 5 : drawLine(line6);break;
            case 6 : drawLine(line7);break;
            case 7 : drawLine(line8);break;
            case 8 : drawLine(line9);break;
            case 9 : fadeInEllipse(circleHead); lockInput = true;break;
        }
        wrongAttemptCount++;
    }


    public String getRandomWord() throws IOException {
        URL url = new URL("https://api.api-ninjas.com/v1/randomword");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("X-Api-Key", API_KEY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while((line = reader.readLine()) != null)
        {
            stringBuilder.append(line);
        }

        reader.close();

        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        return jsonObject.get("word").toString();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(timer == null)
        {
            timer = new MyTimer(Lbl);
        }
        try {
            word = getRandomWord();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HBoxLetterContainer.getChildren().clear();
        letterViewControllers = new ArrayList<>();
        for(int i = 0; i < word.length(); i++)
        {
            FXMLLoader letterLoader = new FXMLLoader(getClass().getResource("letter-view.fxml"));
            try {
                HBoxLetterContainer.getChildren().add(letterLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LetterViewController letterController =  letterLoader.getController();
            letterController.setLetter(word.charAt(i));
            letterViewControllers.add(letterController);
        }

        Platform.runLater(() -> scrollPane.requestFocus());
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}