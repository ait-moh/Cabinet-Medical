package Medecin;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import consultation.Etat;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EtatPatientController implements Initializable{
	private dbConnection dc;
	private ObservableList<Etat> data;
	@FXML private TableView<Etat> patientTable;
	
	
	
	@FXML private TableColumn<Etat,String> groupeSanguin_column;
	@FXML private TableColumn<Etat,String> taille_column;
	@FXML private TableColumn<Etat,String> poids_column;
	@FXML private TableColumn<Etat,String> tension_column;
	@FXML private TableColumn<Etat,String> teuxDiabete_column;


	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.dc = new dbConnection();
		fct();
		
	}
// fct petrmetre de charger les information de l'etat de patient et afficher dans la fenetre 
	public void fct() {  
		String sql = "SELECT * FROM EtatPatient where idPatient ='"+MedecinController.id_e+"'";
	try {
		Connection conn = dbConnection.getConnection();
		
		
		ResultSet rs = conn.createStatement().executeQuery(sql);
		this.data = FXCollections.observableArrayList();
		while(rs.next()) {
			this.data.add(    new Etat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) )  );
		}
		
	} catch (SQLException e) {
		System.err.println("Error" + e);
	}
	
	
	
	this.groupeSanguin_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("groupeSanguin"));
	this.taille_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("taille"));
	this.poids_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("poids"));
	this.tension_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("tension"));
	this.teuxDiabete_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("teuxDiabete"));
	
	
	this.patientTable.setItems(null);
	this.patientTable.setItems(this.data);
		
	}
	

	public void SignOut(ActionEvent event) {
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/login.fxml") );
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void retour(ActionEvent event) {
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/Medecin/Medecin.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
