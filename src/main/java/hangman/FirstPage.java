package hangman;

import com.mongodb.internal.operation.DropDatabaseOperation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPage
{

    public AnchorPane signupContainer;
    public TextField TxtSignupName;
    public TextField TxtSignUpUsername;
    public TextField TxtSignupPassword;
    public AnchorPane loginContainer;
    public Button BtnGoToLogin;
    public Button BtnGoToSignUp;
    public TextField TxtLoginUsername;
    public PasswordField TxtLoginPassword;

    public void BtnSignUpClicked(ActionEvent actionEvent) {
        if(!DatabaseManager.usernameExists(TxtSignUpUsername.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Username already exists");
            alert.showAndWait();
            return;
        }

        if(TxtSignupName.getText().isBlank() || TxtSignUpUsername.getText().isBlank() || TxtSignupPassword.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fill in all the fields");
            alert.showAndWait();
            return;
        }

        DatabaseManager.NewUser(new User(TxtSignupName.getText(),TxtSignUpUsername.getText(),TxtSignupPassword.getText()));
        signupContainer.setVisible(false);
        loginContainer.setVisible(true);
    }

    public void BtnCancelClicked(ActionEvent actionEvent) {
        BtnGoToLogin.setVisible(true);
        BtnGoToSignUp.setVisible(true);
        loginContainer.setVisible(false);
        signupContainer.setVisible(false);
    }

    public void BtnLoginClicked(ActionEvent actionEvent) throws IOException {
        if(!DatabaseManager.login(TxtLoginUsername.getText(), TxtLoginPassword.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Login Failed");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HangmanApp.class.getResource("mainmenu-view.fxml"));
            Parent root = loader.load();
            MainmenuView theController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BtnGoToLoginClicked(ActionEvent actionEvent) {
        loginContainer.setVisible(true);
        BtnGoToLogin.setVisible(false);
        BtnGoToSignUp.setVisible(false);
    }

    public void BtnGoToSignUpClicked(ActionEvent actionEvent) {
        signupContainer.setVisible(true);
        BtnGoToLogin.setVisible(false);
        BtnGoToSignUp.setVisible(false);
    }
}