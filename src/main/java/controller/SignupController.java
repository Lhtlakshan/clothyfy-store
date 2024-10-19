package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SignupController {

    public JFXPasswordField txtRetypePassword;
    public JFXPasswordField txtPassword;
    public JFXTextField txtUsername;
    public Label lblPasswordMatcher;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    public void signupBtnOnAction(ActionEvent actionEvent) {

        if(!isValidUserName(txtUsername.getText())){
            new Alert(Alert.AlertType.WARNING , "Username should be your email.").show();
        } else if (!isValidPassword(txtPassword.getText())) {
            new Alert(Alert.AlertType.WARNING , "Password must be at least 8 characters, including letters, numbers, and symbols.").show();
            System.out.println(txtPassword.getText() + "  " + txtRetypePassword.getText());
        }else if(!txtPassword.getText().equals(txtRetypePassword.getText())){
            new Alert(Alert.AlertType.WARNING , "Password does not match...").show();
        }else {
            signupUser();
            lblPasswordMatcher.setText(null);
            loginShow();
        }

    }

    private boolean isValidUserName(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    private boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    public void loginLinkOnAction(ActionEvent actionEvent) {
        loginShow();
    }

    void loginShow(){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/login-form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void signupUser(){
        try {
            String verifyLogin = "INSERT INTO USER (username , password) VALUES (?,?)";
            CrudUtil.execute(verifyLogin , txtUsername.getText(),txtPassword.getText());

            new Alert(Alert.AlertType.CONFIRMATION , "You are successfully registered !!!").show();
            }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
