/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author tommkruix
 */
public class NewProfileDocumentController implements Initializable {

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    private ObservableList<String> sexNames = FXCollections.observableArrayList("Male", "Female");
    private ObservableList<String> ageNames = FXCollections.observableArrayList("18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80","81","82","83");
   
    @FXML
    private Label lblStatement;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtCrime;
    @FXML
    private JFXTextField txtState;
    @FXML
    private JFXTextField txtCourt;
    @FXML
    private JFXTextField txtIpo;
    @FXML
    private JFXTextField txtLga;
    @FXML
    private JFXTextField txtCaseno;
    @FXML
    private JFXTextField txtTown;
    @FXML
    private JFXTextField txtCellno;
    @FXML
    private JFXTextField txtVerdict;
    @FXML
    private JFXTextArea txtAddress;
    @FXML
    private DatePicker dptConvictdate;
    @FXML
    private JFXComboBox<String> cmbAge;
    @FXML
    private JFXComboBox<String> cmbSex;
    @FXML
    private JFXButton btnCreate;
    @FXML
    private Label lblErrors;
    @FXML
    private DatePicker dptArrestdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbSex.getItems().addAll(sexNames);
        cmbAge.getItems().addAll(ageNames);
    }    

    public NewProfileDocumentController() {
        con = ConnectionUtil.conDB();
    }

    @FXML
    private void CreateNewProfile(MouseEvent event) {
        try {
            String sql = "SELECT * FROM criminal_details WHERE caseno = ?";
            
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, txtCaseno.getText());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    lblErrors.setTextFill(Color.TOMATO);
                    lblErrors.setText("A Criminal with this Case No already exists.");
            
                }else{
            String st = "INSERT INTO criminal_details ( caseno, name, crime, address, stateoforigin, lga, sex, age, ipo, town, court, verdict, cellno, arrestdate, dateconvicted) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = (PreparedStatement) con.prepareStatement(st);
            preparedStatement.setString(1, txtCaseno.getText());
            preparedStatement.setString(2, txtName.getText());
            preparedStatement.setString(3, txtCrime.getText());
            preparedStatement.setString(4, txtAddress.getText());
            preparedStatement.setString(5, txtState.getText());
            preparedStatement.setString(6, txtLga.getText());
            preparedStatement.setString(7, cmbSex.getValue());
            preparedStatement.setString(8, cmbAge.getValue());
            preparedStatement.setString(9, txtIpo.getText());
            preparedStatement.setString(10, txtTown.getText());
            preparedStatement.setString(11, txtCourt.getText());
            preparedStatement.setString(12, txtVerdict.getText());
            preparedStatement.setString(13, txtCellno.getText());
            preparedStatement.setString(14, dptArrestdate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            preparedStatement.setString(15, dptConvictdate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));

            preparedStatement.executeUpdate();
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Success");
            EmptyFields();
            
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText(ex.getMessage());
        }
    }
    
    public LocalDate LocalDate (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
    }
    
    private void EmptyFields() {
        txtCaseno.setText("");
        txtName.setText("");
        txtCrime.setText("");
        txtAddress.setText("");
        txtState.setText("");
        txtLga.setText("");
        cmbSex.setValue("");
        cmbAge.setValue("");
        txtIpo.setText("");
        txtTown.setText("");
        txtCourt.setText("");
        txtVerdict.setText("");
        txtCellno.setText("");
        dptArrestdate.setValue(LocalDate("01/01/1980"));
        dptConvictdate.setValue(LocalDate("01/01/1980"));
    }
    
}
