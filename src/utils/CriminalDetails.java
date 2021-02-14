/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tommkruix
 */
public class CriminalDetails {
    private final StringProperty id;
    private final StringProperty caseno;
    private final StringProperty name;
    private final StringProperty crime;
    private final StringProperty address;
    private final StringProperty stateoforigin;
    private final StringProperty lga;
    private final StringProperty sex;
    private final StringProperty age;
    private final StringProperty ipo;
    private final StringProperty town;
    private final StringProperty court;
    private final StringProperty verdict;
    private final StringProperty cellno;
    private final StringProperty arrestdate;
    private final StringProperty dateconvicted;
    private final StringProperty reg_date;
    
    public CriminalDetails(
            String id, 
            String caseno, 
            String name, 
            String crime, 
            String address,
            String stateoforigin,
            String lga,
            String sex,
            String age,
            String ipo,
            String town,
            String court,
            String verdict,
            String cellno,
            String arrestdate,
            String dateconvicted,
            String reg_date
            ){
            this.id = new SimpleStringProperty(id);
            this.caseno = new SimpleStringProperty(caseno); 
            this.name = new SimpleStringProperty(name);
            this.crime = new SimpleStringProperty(crime);
            this.address = new SimpleStringProperty(address);
            this.stateoforigin = new SimpleStringProperty(stateoforigin);
            this.lga = new SimpleStringProperty(lga);
            this.sex = new SimpleStringProperty(sex);
            this.age = new SimpleStringProperty(age);
            this.ipo = new SimpleStringProperty(ipo);
            this.town = new SimpleStringProperty(town);
            this.court = new SimpleStringProperty(court);
            this.verdict = new SimpleStringProperty(verdict);
            this.cellno = new SimpleStringProperty(cellno);
            this.arrestdate = new SimpleStringProperty(arrestdate);
            this.dateconvicted = new SimpleStringProperty(dateconvicted);
            this.reg_date = new SimpleStringProperty(reg_date);
    }
    
    // Getters
public String getId() {
        return id.get();
    }

    public String getCaseno() {
        return caseno.get();
    }

    public String getName() {
        return name.get();
    }

    public String getCrime() {
        return crime.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getStateoforigin() {
        return stateoforigin.get();
    }

    public String getLga() {
        return lga.get();
    }

    public String getSex() {
        return sex.get();
    }

    public String getAge() {
        return age.get();
    }

    public String getIpo() {
        return ipo.get();
    }

    public String getTown() {
        return town.get();
    }

    public String getCourt() {
        return court.get();
    }
    
    public String getVerdict(){
        return verdict.get();
    }

    public String getCellno() {
        return cellno.get();
    }

    public String getArrestdate() {
        return arrestdate.get();
    }

    public String getDateconvicted() {
        return dateconvicted.get();
    }

    public String getReg_date() {
        return reg_date.get();
    }
    
    // Setters
    public void setId(String value){
        id.set(value);
    }
    
    public void setName(String value){
        name.set(value);
    }
    
    public void setCaseno(String value){
        caseno.set(value);
    }
    
    public void setCrime(String value){
        crime.set(value);
    }
    
    public void setAddress(String value){
        address.set(value);
    }
    
    public void setStateoforigin(String value){
        stateoforigin.set(value);
    }
    
    public void setLga(String value){
        lga.set(value);
    }
 
    public void setSex(String value){
        sex.set(value);
    }
 
    public void setAge(String value){
        age.set(value);
    }
 
    public void setIpo(String value){
        ipo.set(value);
    }
 
    public void setTown(String value){
        town.set(value);
    }
 
    public void setCourt(String value){
        court.set(value);
    }
 
    public void setVerdict(String value){
        verdict.set(value);
    }
 
    public void setCellno(String value){
        cellno.set(value);
    }
 
    public void setArrestdate(String value){
        arrestdate.set(value);
    }
 
    public void setDateconvicted(String value){
        dateconvicted.set(value);
    }
 
    public void setReg_date(String value){
        reg_date.set(value);
    }
    
    // Property values
    public StringProperty idProperty(){
        return id;
    }
    
    public StringProperty idCaseno(){
        return caseno;
    }
    
    public StringProperty idName(){
        return name;
    }
    
    public StringProperty idCrime(){
        return crime;
    }
    
    public StringProperty idAddress(){
        return address;
    }
    
    public StringProperty idStateoforigin(){
        return stateoforigin;
    }
    
    public StringProperty idLga(){
        return lga;
    }
    
    public StringProperty idSex(){
        return sex;
    }
    
    public StringProperty idAge(){
        return age;
    }
    
    public StringProperty idIpo(){
        return ipo;
    }
    
    public StringProperty idTown(){
        return town;
    }
    
    public StringProperty idCourt(){
        return court;
    }
    
    public StringProperty idVerdict(){
        return verdict;
    }
    
    public StringProperty idCellno(){
        return cellno;
    }
    
    public StringProperty idArrestdate(){
        return arrestdate;
    }
    
    public StringProperty idDateconvicted(){
        return dateconvicted;
    }
    
    public StringProperty idReg_date(){
        return reg_date;
    }
    
}
