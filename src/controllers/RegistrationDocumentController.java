/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.MainDocument;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author tommkruixSdk
 */
public class RegistrationDocumentController implements Initializable {

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    private AnchorPane pane;
    @FXML
    private Label closeBtn;
    @FXML
    private Label backArrow;
    @FXML
    private JFXButton registerBtn;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtName;
    @FXML
    private Label lblErrors;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public RegistrationDocumentController() {
        con = ConnectionUtil.conDB();
    }
    
    @FXML
    private void open_registration_page(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/MainDocument.fxml"));
        MainDocument.stage.getScene().setRoot(fxml); 
    }

    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
        Stage stage = (Stage) closeBtn.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

    private void back_to_menu(MouseEvent event) throws IOException {
    Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/MainDocument.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().setAll(fxml);
    }

    @FXML
    private void CreateAccount(MouseEvent event) {
        try {
            String st = "INSERT INTO admin ( name, email, password) VALUES (?,?,?)";
            preparedStatement = (PreparedStatement) con.prepareStatement(st);
            preparedStatement.setString(1, txtName.getText());
            preparedStatement.setString(2, txtEmail.getText());
            preparedStatement.setString(3, txtPassword.getText());

            preparedStatement.executeUpdate();
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText(ex.getMessage());
        }
    }
    
}
