/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.ConnectionUtil;
import utils.CriminalDetails;

/**
 * FXML Controller class
 *
 * @author tommkruix
 */
public class DashboardTableViewDocumentController implements Initializable {

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    @FXML
    private JFXComboBox<String> loadBtn;
    
    @FXML
    private TableView<CriminalDetails> RecordTable;
    @FXML
    private TableColumn<CriminalDetails, String> colCASENO;
    @FXML
    private TableColumn<CriminalDetails, String> colNAME;
    @FXML
    private TableColumn<CriminalDetails, String> colCRIME;
    @FXML
    private TableColumn<CriminalDetails, String> colADDRESS;
    @FXML
    private TableColumn<CriminalDetails, String> colSTATE;
    @FXML
    private TableColumn<CriminalDetails, String> colSEX;
    @FXML
    private TableColumn<CriminalDetails, String> colIPO;
    @FXML
    private TableColumn<CriminalDetails, String> colVERDICT;
    @FXML
    private TableColumn<CriminalDetails, String> colCELLNO;
    @FXML
    private TableColumn<CriminalDetails, String> colARRESTDATE;
    
    private ObservableList<String> names = FXCollections.observableArrayList("Daily", "Weekly", "Monthly", "All");
    private ObservableList<CriminalDetails> tableData;
    @FXML
    private Label lblStatement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBtn.getItems().addAll(names);
    }    

    public DashboardTableViewDocumentController() {
        con = ConnectionUtil.conDB();
    }
    
    private void GetRecordTableData(String Date) {
        tableData = FXCollections.observableArrayList();
        tableData.clear();
        try {
            ResultSet rs = null;
            switch (Date) {
                case "Daily":
                    rs = con.createStatement().executeQuery("SELECT * FROM criminal_details WHERE reg_date >= DATE_SUB(NOW(), INTERVAL 1 DAY)");
                    break;
                case "Weekly":
                    rs = con.createStatement().executeQuery("SELECT * FROM criminal_details WHERE reg_date >= DATE_SUB(NOW(), INTERVAL 1 WEEK)");
                    break;
                case "Monthly":
                    rs = con.createStatement().executeQuery("SELECT * FROM criminal_details WHERE reg_date >= DATE_SUB(NOW(), INTERVAL 1 MONTH)");
                    break;
                case "All":
                    rs = con.createStatement().executeQuery("SELECT * FROM criminal_details");
                    break;
                default:
                    break;
            }
            while(rs.next()){
            String id = rs.getString(1);
            String caseno = rs.getString(2);
            String name = rs.getString(3);
            String crime = rs.getString(4);
            String address = rs.getString(5);
            String stateoforigin = rs.getString(6);
            String lga = rs.getString(7);
            String sex = rs.getString(8);
            String age = rs.getString(9);
            String ipo = rs.getString(10);
            String town = rs.getString(11);
            String court = rs.getString(12);
            String verdict = rs.getString(13);
            String cellno = rs.getString(14);
            String arrestdate = rs.getString(15);
            String dateconvicted = rs.getString(16);
            String reg_date = rs.getString(17);
                tableData.add(new CriminalDetails(id, caseno, name, crime, address, stateoforigin, lga, sex, age, ipo, town, court, verdict, cellno, arrestdate, dateconvicted, reg_date));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        
        colCASENO.setCellValueFactory(new PropertyValueFactory<>("caseno"));
        colNAME.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCRIME.setCellValueFactory(new PropertyValueFactory<>("crime"));
        colADDRESS.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSTATE.setCellValueFactory(new PropertyValueFactory<>("stateoforigin"));
        colSEX.setCellValueFactory(new PropertyValueFactory<>("sex"));
        colIPO.setCellValueFactory(new PropertyValueFactory<>("ipo"));
        colVERDICT.setCellValueFactory(new PropertyValueFactory<>("verdict"));
        colCELLNO.setCellValueFactory(new PropertyValueFactory<>("cellno"));
        colARRESTDATE.setCellValueFactory(new PropertyValueFactory<>("arrestdate"));
       
        RecordTable.setItems(null);
        RecordTable.setItems(tableData);
        
    }

    @FXML
    private void handleLoadBtn(ActionEvent event) {
        String reportType = loadBtn.getValue();
        GetRecordTableData(reportType);
    }

    @FXML
    private void GeneratePDF(MouseEvent event) throws FileNotFoundException, DocumentException, SQLException, BadElementException, IOException{
        String reportType = loadBtn.getValue();
        if(reportType != null){
        Statement stmt = con.createStatement();
        ResultSet rs = null;         
        
        switch (reportType) {
                case "Daily":
                    rs = con.createStatement().executeQuery("SELECT * FROM criminal_details WHERE reg_date >= DATE_SUB(NOW(), INTERVAL 1 DAY)");
                    break;
                case "Weekly":
                    rs = con.createStatement().executeQuery("SELECT * FROM criminal_details WHERE reg_date >= DATE_SUB(NOW(), INTERVAL 1 WEEK)");
                    break;
                case "Monthly":
                    rs = con.createStatement().executeQuery("SELECT * FROM criminal_details WHERE reg_date >= DATE_SUB(NOW(), INTERVAL 1 MONTH)");
                    break;
                case "All":
                    rs = con.createStatement().executeQuery("SELECT * FROM criminal_details");
                    break;
                default:
                    break;
            }
        /* Define the SQL query */
        Document my_pdf_report = new Document();
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("GeneratedReports/" + reportType + " Osun State Police Command"  + ".pdf"));
        my_pdf_report.open(); 
        Paragraph para = new Paragraph("Criminal Tracking System\nOsun State Police Command "+ reportType + " Report\n\n");
        my_pdf_report.add(para);
        
        my_pdf_report.add(new Paragraph(""));
        my_pdf_report.add(new Paragraph(""));
        my_pdf_report.add(new Paragraph(""));
        //we have ten columns in our table
                    PdfPTable my_report_table = new PdfPTable(10);
                    PdfPCell c1 = new PdfPCell(new Phrase("Case No"));
                    my_report_table.addCell(c1);
                    PdfPCell c2 = new PdfPCell(new Phrase("Name"));
                    my_report_table.addCell(c2);
                    PdfPCell c3 = new PdfPCell(new Phrase("Crime"));
                    my_report_table.addCell(c3);
                    PdfPCell c4 = new PdfPCell(new Phrase("Address"));
                    my_report_table.addCell(c4);
                    PdfPCell c5 = new PdfPCell(new Phrase("State"));
                    my_report_table.addCell(c5);
                    PdfPCell c6 = new PdfPCell(new Phrase("Sex"));
                    my_report_table.addCell(c6);
                    PdfPCell c7 = new PdfPCell(new Phrase("IPO"));
                    my_report_table.addCell(c7);
                    PdfPCell c8 = new PdfPCell(new Phrase("Verdict"));
                    my_report_table.addCell(c8);
                    PdfPCell c9 = new PdfPCell(new Phrase("Cell No"));
                    my_report_table.addCell(c9);
                    PdfPCell c10 = new PdfPCell(new Phrase("Arrest Date"));
                    my_report_table.addCell(c10);
                    my_report_table.setHeaderRows(1);
                    
                    //create a cell object
                    //PdfPCell table_cell;

                    while (rs.next()) {                
                                    String caseno = rs.getString("caseno");
                                    //table_cell=new PdfPCell(new Phrase(caseno));
                                    my_report_table.addCell(caseno);
                                    String name=rs.getString("name");
                                    //table_cell=new PdfPCell(new Phrase(name));
                                    my_report_table.addCell(name);
                                    String crime=rs.getString("crime");
                                    //table_cell=new PdfPCell(new Phrase(crime));
                                    my_report_table.addCell(crime);
                                    String address=rs.getString("address");
                                    //table_cell=new PdfPCell(new Phrase(address));
                                    my_report_table.addCell(address);
                                    String stateoforigin=rs.getString("stateoforigin");
                                    //table_cell=new PdfPCell(new Phrase(stateoforigin));
                                    my_report_table.addCell(stateoforigin);
                                    String sex=rs.getString("sex");
                                    //table_cell=new PdfPCell(new Phrase(sex));
                                    my_report_table.addCell(sex);
                                    String ipo=rs.getString("ipo");
                                    //table_cell=new PdfPCell(new Phrase(ipo));
                                    my_report_table.addCell(ipo);
                                    String verdict=rs.getString("verdict");
                                    //table_cell=new PdfPCell(new Phrase(verdict));
                                    my_report_table.addCell(verdict);
                                    String cellno=rs.getString("cellno");
                                    //table_cell=new PdfPCell(new Phrase(cellno));
                                    my_report_table.addCell(cellno);
                                    String arrestdate=rs.getString("arrestdate");
                                    //table_cell=new PdfPCell(new Phrase(arrestdate));
                                    my_report_table.addCell(arrestdate);
                                    }
                    /* Attach report table to PDF */
                    my_pdf_report.add(my_report_table); 
                    
                    my_pdf_report.add(Image.getInstance("src/assets/icon.png"));
                    
                    my_pdf_report.close();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Criminal Tracking System");
                    alert.setHeaderText(null);
                    alert.setContentText("Report Generated! Check \"GeneratedReports\" folder at the root folder of the project.");

                    alert.showAndWait();
                    /* Close all DB related objects */
                    /*rs.close();
                    stmt.close(); 
                    con.close(); */ 
        
    }else{
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Criminal Tracking System");
                    alert.setHeaderText(null);
                    alert.setContentText("Select Report Type in the dropdown at the \"Top Right Corner\" of the app.");

                    alert.showAndWait();
}
    }
    
}
