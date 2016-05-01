package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	
	Model model;
	boolean attiva=true;
	

    public void setModel(Model model) {
		this.model = model;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnTrova"
    private Button btnTrova; // Value injected by FXMLLoader

    @FXML // fx:id="btnGenera"
    private Button btnGenera; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumero"
    private TextField txtNumero; // Value injected by FXMLLoader

    @FXML // fx:id="btnConnessi"
    private Button btnConnessi; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doReset(ActionEvent event) {
    	this.txtResult.clear();
    	this.txtNumero.clear();
    	this.txtParola.clear();
    	attiva=true;
    	this.btnConnessi.setDisable(attiva);
        this.btnTrova.setDisable(attiva);
    	   }

    @FXML
    void doGenera(ActionEvent event) {
    	this.txtResult.clear();
    	//input control
    	boolean controlloNumero=model.controlloNumero(this.txtNumero.getText());
    	if(!controlloNumero){this.txtResult.appendText("Introdurre numeri interi ");return;}
    	//generazione del grafo
    	int numero=Integer.parseInt(this.txtNumero.getText());
    	boolean controlloGrafo=model.creaGrafo(numero);
    	if(!controlloGrafo){this.txtResult.appendText("errore nella creazione del grafo");return;}
       	attiva=false;
    	this.btnConnessi.setDisable(attiva);
        this.btnTrova.setDisable(attiva);
        this.txtParola.setDisable(attiva);
    }

    @FXML
    void doTrova(ActionEvent event) {
    	this.txtResult.clear();
    	//input control
    	boolean controlloNumero=model.controlloNumero(this.txtNumero.getText());
    	if(!controlloNumero){this.txtResult.appendText("Introdurre numeri interi ");return;}
    	//generazione del grafo
    	int numero=Integer.parseInt(this.txtNumero.getText());
    	boolean controlloParola=model.controlloParola(this.txtParola.getText(),numero);
    	if(!controlloParola){this.txtResult.appendText("La parola deve essere della lunghezza indicata");return;}
    	String result=model.listaVicini(this.txtParola.getText());
    	if(result.compareTo("")==0){this.txtResult.appendText("Non è stato trovata nessuna parola");return;}
    	this.txtResult.appendText(result);
     }

    @FXML
    void doConnessi(ActionEvent event) {
    	this.txtResult.clear();
    	//input control
    	boolean controlloNumero=model.controlloNumero(this.txtNumero.getText());
    	if(!controlloNumero){this.txtResult.appendText("Introdurre numeri interi ");return;}
    	//generazione del grafo
    	int numero=Integer.parseInt(this.txtNumero.getText());
    	boolean controlloParola=model.controlloParola(this.txtParola.getText(),numero);
    	if(!controlloParola){this.txtResult.appendText("La parola deve essere della lunghezza indicata");return;}
    	String result=model.listaConnessi(this.txtParola.getText());
    	if(result.compareTo("")==0){this.txtResult.appendText("Non è stato trovata nessuna parola");return;}
    	this.txtResult.appendText(result);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnTrova != null : "fx:id=\"btnTrova\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtNumero != null : "fx:id=\"txtNumero\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnConnessi != null : "fx:id=\"btnConnessi\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Dizionario.fxml'.";
        this.btnConnessi.setDisable(attiva);
        this.btnTrova.setDisable(attiva);
        this.txtParola.setDisable(attiva);

    }
}