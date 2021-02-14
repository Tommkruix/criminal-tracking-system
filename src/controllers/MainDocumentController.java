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
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import utils.ConnectionUtil;
import controllers.DashboardDocumentController;

/**
 * FXML Controller class
 *
 * @author tommkruix
 */
public class MainDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private AnchorPane parent;
    @FXML
    private Pane content_area;
    @FXML
    private Label closeBtn;
    
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Label lblErrors;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton loginButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    } 
    
    public MainDocumentController() {
        con = ConnectionUtil.conDB();
    }
    
    @FXML
    private String logIn() throws IOException {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM admin Where email = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    String name = resultSet.getString("name");
                    RedirectToDashboard(name);
                    
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    @FXML
    private void close_app(MouseEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
        System.exit(0);
    }

    @FXML
    private void open_registration_page(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegistrationDocument.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    private void RedirectToDashboard(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DashboardDocument.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        DashboardDocumentController displayLoggedIn = fxmlLoader.getController();
        displayLoggedIn.setLoggedInDetails(name);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Criminal Tracking System");
        stage.setScene(new Scene(root1));  
        stage.show();
        Stage stage1 = (Stage) loginButton.getScene().getWindow();
        stage1.close();
    }

    
}
