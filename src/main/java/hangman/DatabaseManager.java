package hangman;
import com.mongodb.*;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.connection.ConnectionPoolSettings;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Indexes.descending;

// Use JDBC to connect to your database and run queries

public class DatabaseManager {
    private static MongoCollection<Document> Users;
    private static MongoCollection<Document> Games;
    public static User currentUser;
    private static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

    public static MongoDatabase getDatabase(String dbName) {
        return mongoClient.getDatabase(dbName);
    }

    static
    {
        ConnectionPoolSettings connectionPoolSettings = ConnectionPoolSettings.builder()
                .maxConnectionIdleTime(10, TimeUnit.HOURS) // Set max idle time to 10 minutes
                .build();

        // Set up the MongoClient settings
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
                .applyToConnectionPoolSettings(builder -> builder.applySettings(connectionPoolSettings))
                .build();

        // Create a MongoClient
        try {
            MongoDatabase database = getDatabase("HangmanMESH2");
            Users =  database.getCollection("Users");
            Games =  database.getCollection("Games");
        }
        catch (Exception ignore)
        {
        }

    }

    public static boolean login(String username, String password)
    {
        Document doc = Users.find(and(eq("password",password),eq("username", username))).first();

        if(doc == null)
        {
            return false;
        }
        else
        {
            try
            {
                currentUser = new User(doc);
            }
            catch (Exception ignore)
            {
                return  false;
            }

            return true;
        }
    }

    public static boolean usernameExists(String username)
    {
        Document doc = Users.find(eq("username",username)).first();
        return doc == null;
    }

    public static void NewUser(User user)
    {
        Users.insertOne(user.getDocument());
    }
    public static void NewGame(Game game)
    {
        Games.insertOne(game.getDocument());
    }

    public static List<LeaderboardItem> getPreviousResults() {
        FindIterable<Document> games = Games.find(eq("username", currentUser.getUsername()));
        List<LeaderboardItem> leaderboardItems = new ArrayList<>();

        for(Document gameDocument : games)
        {
             Game game = new Game(gameDocument);

            LeaderboardItem item = new LeaderboardItem(game.getGameSummary() ,-1);
            item.setRank(-1);
            leaderboardItems.add(item);
        }

        return leaderboardItems;
    }

    public static List<LeaderboardItem> getLeaderboard() {
        FindIterable<Document> users = Users.find();
        List<LeaderboardItem> leaderboardItems = new ArrayList<>();

        for(Document user : users)
        {
            long count = Games.countDocuments(and(eq("username", user.getString("username")), eq("win", true)));
            leaderboardItems.add(new LeaderboardItem(user.getString("username"),count));
        }
        Collections.sort(leaderboardItems, new Comparator<LeaderboardItem>() {
            public int compare(LeaderboardItem o1, LeaderboardItem o2) {
                return Long.compare(o2.getVictories(),o1.getVictories());
            }
        });
        int rank = 1;
        for(LeaderboardItem leaderboardItem : leaderboardItems)
        {
            leaderboardItem.setRank(rank);
            rank++;
        }
        return  leaderboardItems;
    }

    public static class LeaderboardItem
    {
        public LeaderboardItem(String username, long victories)
        {
            this.username = username;
            this.victories = victories;
        }
        int rank;
        String username;
        long victories;
        public long getVictories()
        {
            return victories;
        }
        public String getUsername()
        {
            return username;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank)
        {
            this.rank = rank;
        }
    }
}