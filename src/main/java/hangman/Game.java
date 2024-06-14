package hangman;

import org.bson.Document;

import java.util.UUID;

public class Game
{
    private UUID gameId;
    private String username;
    private String word;
    private int wrongGuesses;
    private int time;
    private boolean win;

    public Game(Document doc)
    {
        gameId = UUID.fromString(doc.getString("gameId"));
        username = doc.getString("username");
        word = doc.getString("word");
        wrongGuesses = doc.getInteger("wrongGuesses");
        time = doc.getInteger("time");
        win = doc.getBoolean("win");
    }
    public Game(String username, String word, int wrongGuesses, int time, boolean win)
    {
        gameId = UUID.randomUUID();
        this.username = username;
        this.word = word;
        this.wrongGuesses = wrongGuesses;
        this.time = time;
        this.win = win;
    }

    public Document getDocument()
    {
        return new Document("gameId",gameId.toString())
                .append("username", username)
                .append("word",word)
                .append("wrongGuesses", wrongGuesses)
                .append("time", time)
                .append("win", win);
    }

    public String getGameSummary()
    {
        String formattedTime =String.format("%02d:%02d", time / 60, time % 60);
        String status = "";
        if(win)
        {
            status = " WON";
        }
        else
        {
            status = " LOST";
        }
        return "Time: " + formattedTime + " | Word: " + word + " " + status;
    }
}
