module hangman {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.json;

    opens hangman to javafx.fxml;
    exports hangman;
}