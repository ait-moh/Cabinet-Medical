package Secretariat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

public class AjouterRDController implements Initializable{
	@FXML
	private TextField id_patient;
	@FXML
	private TextField nom_patient;
	@FXML
	private TextField prenom_patient;
	@FXML
	private TextField tel_patient;
	@FXML
	private TextField email_patient;
	@FXML
	private TextField num_RD;
	@FXML
	private TextField heure_RD;
	@FXML
	private DatePicker date_RD;
	@FXML
	private ComboBox jour_RD;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.id_patient.setText(PresAjoutRDController.id_a);
		this.nom_patient.setText(PresAjoutRDController.nom_a);
		this.prenom_patient.setText(PresAjoutRDController.prenom_a);
		this.tel_patient.setText(PresAjoutRDController.tel_a);
		this.email_patient.setText(PresAjoutRDController.email_a);
		
		
	}
	@FXML 
	private void confirmer_ajout(ActionEvent event) throws IOException {
		   	
		String sqlInsert = "INSERT INTO rendez_vous(num_RD,id,nom_patient,prenom_patient,tel_patient,email_patient,jour_RD,heure_RD,date_RD,inscrit_en_BD) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
		Connection conn = dbConnection.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			//si aucun patient n'est sélectionner on affiche ce méssage
		if(this.num_RD.getText().toString().compareTo("")==0 ) {JOptionPane.showMessageDialog(null, "veillez introduire un num_RD");return;}
		// si la date n'est pas compatible avec le format conventionel du "date picker" ce message sera affiché
		//donc il faut choisir une date à partir du calendrier donné dans l'interface
		else if(!this.date_RD.getEditor().getText().toString().matches("\\d{2}/\\d{2}/\\d{4}") ) {JOptionPane.showMessageDialog(null, "veillez introduire une date à partir du date picker");return;}
		else if(this.heure_RD.getText().toString().compareTo("")==0 ) {JOptionPane.showMessageDialog(null, "veillez introduire une heure");return;}
		   else {	
		
		String sql = "SELECT * FROM patient WHERE id ='"+this.id_patient.getText()+"'";
		
			
		 ResultSet rs = conn.createStatement().executeQuery(sql);
		
		
			stmt.setString(1, this.num_RD.getText());
			stmt.setString(3, this.nom_patient.getText());
			stmt.setString(4, this.prenom_patient.getText());
			stmt.setString(5, this.tel_patient.getText());
			stmt.setString(6, this.email_patient.getText());
			stmt.setString(8, this.heure_RD.getText());
			stmt.setString(9, this.date_RD.getEditor().getText());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date =sdf.parse(this.date_RD.getEditor().getText());
			 switch (date.getDay()) {
			case 0:
				stmt.setString(7,"dimanche");
				break;
            case 1:
            	stmt.setString(7,"lundi");
				break;
            case 2:
            	stmt.setString(7,"mardi");
	            break;
            case 3:
            	stmt.setString(7,"mercredi"); 
	            break;
            case 4:
            	stmt.setString(7,"jeudi");
	            break;
           case 5:
          	    stmt.setString(7,"vendredi");
	           break; 
  
			default: 
				stmt.setString(7,"samedi");	
				break;
			}
			 stmt.setString(2,null);
			 
			 
			if(rs.isClosed()) stmt.setString(10, "non");//indiquer que le patient n'existe pas dans la base de données 
			 else stmt.setString(10, "oui");
			
		   
			
			stmt.execute();
	
			
			conn.close();
			
			this.id_patient.setText(null);
			this.nom_patient.setText(null);
			this.prenom_patient.setText(null);
			this.tel_patient.setText(null);
			this.email_patient.setText(null);
			
			
			
			
			JOptionPane.showMessageDialog(null, "ajout du rendez-vous avec succés ");
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/Secretariat/gererRD.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		}} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "soit le num_RD est déjà existant\nsoit le médecin est occupé à cette heure ci");
		}catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
	   

	}
	@FXML
    public void Annuler(ActionEvent event) {
try {
		
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/Secretariat/PresAjoutRD.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	} catch (Exception e) {
		// TODO: handle exception
	}
    }
	
}
