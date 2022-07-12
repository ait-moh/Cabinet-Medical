package Secretariat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Medecin.Patient;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class PresAjoutRDController implements Initializable{

	@FXML private TextField nom;
	

 public static String id_a=null,nom_a=null,prenom_a=null,tel_a=null,email_a=null;
		
	
	@FXML private TableView<Patient> patientTable;
	
	@FXML private TableColumn<Patient,String> id_column;
	@FXML private TableColumn<Patient,String> nom_column;
	@FXML private TableColumn<Patient,String> prenom_column;
	@FXML private TableColumn<Patient,String> dateNaissance_column;
	@FXML private TableColumn<Patient,String> sex_column;
	@FXML private TableColumn<Patient,String> tel_column;
	@FXML private TableColumn<Patient,String> email_column;
	@FXML private TableColumn<Patient,String> adresse_column;
	@FXML private TableColumn<Patient,String> date_premiere_visite_column;
	@FXML private TableColumn<Patient,String> date_derniere_visite_column;

	
	private dbConnection dc;
    private ObservableList<Patient> data;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.dc = new dbConnection();	
		
	}
	
	@FXML 
	private void loadPatientData(ActionEvent event)throws SQLException 
	{// charger les patients dans la fenêtres
	  String sql = "SELECT * FROM patient";
		try {
			Connection conn = dbConnection.getConnection();
			
			
			ResultSet rs = conn.createStatement().executeQuery(sql);
			this.data = FXCollections.observableArrayList();
			while(rs.next()) {
				this.data.add(    new Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) , rs.getString(7) , rs.getString(8) , rs.getString(9), rs.getString(10)) );
			}
			
		} catch (SQLException e) {
			System.err.println("Error" + e);
		}
		
		
		this.id_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("id"));
		this.nom_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("nom"));
		this.prenom_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("prenom"));
		this.dateNaissance_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("dateNaissance"));
		this.sex_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("sex"));
		this.tel_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("tel"));
		this.email_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("email"));
		this.adresse_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("adresse"));
		this.date_premiere_visite_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("date_premiere_visite"));
		this.date_derniere_visite_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("date_derniere_visite"));
	
		
		this.patientTable.setItems(null);
		this.patientTable.setItems(this.data);
	}
	
	public void rechercher(ActionEvent event) {
		 String sql = "SELECT * FROM patient WHERE nom = '"+this.nom.getText()+"'";
			try {
				Connection conn = dbConnection.getConnection();
				this.data = FXCollections.observableArrayList();
				
				ResultSet rs = conn.createStatement().executeQuery(sql);
				
				
				while(rs.next()) {
					this.data.add(    new Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) , rs.getString(7) , rs.getString(8) , rs.getString(9), rs.getString(10) ) );
				}
				
			} catch (SQLException e) {
				System.err.println("Error" + e);
			}
			
			
			this.id_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("id"));
			this.nom_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("nom"));
			this.prenom_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("prenom"));
			this.dateNaissance_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("dateNaissance"));
			this.sex_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("sex"));
			this.tel_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("tel"));
			this.email_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("email"));
			this.adresse_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("adresse"));
			this.date_premiere_visite_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("date_premiere_visite"));
			this.date_derniere_visite_column.setCellValueFactory(new PropertyValueFactory<Patient,String>("date_derniere_visite"));
			
			this.patientTable.setItems(null);
			this.patientTable.setItems(this.data);
		
	}
	
	@FXML
	public void ajout(ActionEvent event)throws IOException {
	try {
		
		if (this.patientTable.getSelectionModel().getSelectedIndex()==-1) JOptionPane.showMessageDialog(null, "veuillez sélectionner un patient dans la table");
		else {
			
			id_a=this.id_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
			nom_a=this.nom_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
			prenom_a=this.prenom_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
			tel_a=this.tel_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
			email_a=this.email_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
		
			
			((Node)event.getSource()).getScene().getWindow().hide();
					
					Stage adminStage = new Stage();
					FXMLLoader Loader = new FXMLLoader();
					Pane root =(Pane) Loader.load(getClass().getResource("/Secretariat/AjouterRD.fxml").openStream());
					AjouterRDController ajouterRDController = (AjouterRDController)Loader.getController();
					
					Scene scene = new Scene(root);
					//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					adminStage.setScene(scene);
					adminStage.setTitle("Ajouter un rendez-vous en base de données");
					adminStage.setResizable(false);
					adminStage.show();
				
		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	@FXML
	public void nouvel_ajout(ActionEvent event)throws IOException {
	try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
					
					Stage adminStage = new Stage();
					FXMLLoader Loader = new FXMLLoader();
					Pane root =(Pane) Loader.load(getClass().getResource("/Secretariat/AjouterRD.fxml").openStream());
					AjouterRDController ajouterRDController = (AjouterRDController)Loader.getController();
					
					Scene scene = new Scene(root);
					//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					adminStage.setScene(scene);
					adminStage.setTitle("Ajouter un rendez-vous en base de données");
					adminStage.setResizable(false);
					adminStage.show();
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	
	
	@FXML
    public void Annuler(ActionEvent event) {
try {
		
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/Secretariat/gererRD.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	} catch (Exception e) {
		// TODO: handle exception
	}
    }
	
}
