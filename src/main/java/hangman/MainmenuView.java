package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainmenuView {

    public void BtnPlay(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HangmanApp.class.getResource("hangman-view.fxml"));
            Parent root = loader.load();
            HangmanController theController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            theController.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BtnLeaderboard(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HangmanApp.class.getResource("leaderboard.fxml"));
            Parent root = loader.load();
            Leaderboard theController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            theController.showLeaderboard();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BtnPreviousResults(ActionEvent actionEvent)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HangmanApp.class.getResource("leaderboard.fxml"));
            Parent root = loader.load();
            Leaderboard theController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            theController.showPreviousResults();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
