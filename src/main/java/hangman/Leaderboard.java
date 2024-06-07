package hangman;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class Leaderboard
{
    public ScrollPane scrollPane;
    public VBox VBoxContainer;

    public void showLeaderboard() throws IOException {
        List<DatabaseManager.LeaderboardItem> results = DatabaseManager.getLeaderboard();
        for(DatabaseManager.LeaderboardItem item : results)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HangmanApp.class.getResource("leaderboardItem.fxml"));
            Node node = loader.load();
            LeaderboardItemController controller = loader.getController();
            controller.setState(item);
            VBoxContainer.getChildren().add(node);
        }
    }
    public void showPreviousResults() throws IOException {
        List<DatabaseManager.LeaderboardItem> results = DatabaseManager.getPreviousResults();
        for(DatabaseManager.LeaderboardItem item : results)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HangmanApp.class.getResource("leaderboardItem.fxml"));
            Node node = loader.load();
            LeaderboardItemController controller = loader.getController();
            controller.setState(item);
            VBoxContainer.getChildren().add(node);
        }
    }
}