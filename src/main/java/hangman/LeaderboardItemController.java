package hangman;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LeaderboardItemController {
    public Label LblName;
    public Label LblVictory;
    public Button BtnRank;
    public void setState(DatabaseManager.LeaderboardItem leaderboardItem)
    {
        if(leaderboardItem.getRank() == -1)
        {
            LblVictory.setVisible(false);
            BtnRank.setVisible(false);
            LblName.setText(leaderboardItem.getUsername());
            return;
        }
        BtnRank.setText(leaderboardItem.getRank() + "");
        LblName.setText(leaderboardItem.getUsername());
        LblVictory.setText("Victories: " + leaderboardItem.getVictories());
    }
}
