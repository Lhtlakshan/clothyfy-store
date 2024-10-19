package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private JFXButton loginBtnOnAction;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void loginBtnOnAction(ActionEvent event) {
        if(txtUsername.getText().isEmpty() == false && txtPassword.getText().isEmpty() == false){
            validDataLogin();
            if(txtUsername.getText().equals("thilina@admin.com")){
                try {
                    clearLogin();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/owner/owner_dashboard.fxml"))));
                    stage.show();
                    new Alert(Alert.AlertType.INFORMATION , "You are successfully login !!!").show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    clearLogin();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/employee/employee_dashboard.fxml"))));
                    stage.show();
                    new Alert(Alert.AlertType.INFORMATION , "You are successfully login !!!").show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            new Alert(Alert.AlertType.WARNING , "Please enter username and password !!!").show();
        }
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        File imgFile= new File("Images/cloths_store_login.jpg");
//
//    }

    public void signupLinkOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/signup-form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void validDataLogin(){
        try {
            String verifyLogin = "SELECT COUNT(1) FROM user WHERE username = ? AND password = ?";
            ResultSet resultSet = CrudUtil.execute(verifyLogin, txtUsername.getText(), txtPassword.getText());

            while (resultSet.next()){
                if(!(resultSet.getInt(1) == 1)){
                    new Alert(Alert.AlertType.WARNING , "Invalid login please try again").show();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void clearLogin(){
        txtUsername.setText(null);
        txtPassword.setText(null);
    }
}
