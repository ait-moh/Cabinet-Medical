package Secretariat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
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

import Medecin.MedecinController;
import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

public class ModifierRDController implements Initializable{
	@FXML
	private TextField id_modif;
	@FXML
	private TextField nom_patient_modi;
	@FXML
	private TextField prenom_patient_modi;
	@FXML
	private TextField tel_patient_modi;
	@FXML
	private TextField email_patient_modi;
	@FXML
	private TextField num_RD_modi;
	@FXML
	private TextField heure_RD_modi;
	@FXML
	private DatePicker date_RD_modi;
	@FXML
	private ComboBox jour_RD;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//initialiser les texte fields par les informations du patients séléctionné 
		this.id_modif.setText(GererRDController.id_modif);
		this.nom_patient_modi.setText(GererRDController.nom_modif);
		this.prenom_patient_modi.setText(GererRDController.prenom_modif);
		this.tel_patient_modi.setText(GererRDController.tel_modif);
		this.email_patient_modi.setText(GererRDController.email_modif);
		this.num_RD_modi.setText(GererRDController.numRD_modif);
		this.heure_RD_modi.setText(GererRDController.heure_modif);
		this.date_RD_modi.getEditor().setText(GererRDController.date_modif);
	
		
	}
	@FXML
	public void Confirmer(ActionEvent event) throws IOException{
		
		
		
		String sqlInsert = "UPDATE rendez_vous SET num_RD=? ,id=? ,nom_patient=? ,prenom_patient=? ,tel_patient=? ,email_patient=? ,jour_RD=? ,heure_RD=? ,date_RD=? ,inscrit_en_BD=? WHERE num_RD='"+GererRDController.numRD_modif+"'";
	
		Connection conn = dbConnection.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		if(this.num_RD_modi.getText().toString().compareTo("")==0 ) {JOptionPane.showMessageDialog(null, "veillez introduire un num_RD");return;}
		else if(!this.date_RD_modi.getEditor().getText().toString().matches("\\d{2}/\\d{2}/\\d{4}") ) {JOptionPane.showMessageDialog(null, "veillez introduire une date à partir du date picker");return;}
		else if(this.heure_RD_modi.getText().toString().compareTo("")==0 ) {JOptionPane.showMessageDialog(null, "veillez introduire une heure");return;}
		   else {	
		
		String sql = "SELECT * FROM patient WHERE id ='"+this.id_modif.getText()+"'";
		
			
		 ResultSet rs = conn.createStatement().executeQuery(sql);
		 
		    stmt.setString(1, this.num_RD_modi.getText());
		    stmt.setString(3, this.nom_patient_modi.getText());
			stmt.setString(4, this.prenom_patient_modi.getText());
			stmt.setString(5, this.tel_patient_modi.getText());
			stmt.setString(6, this.email_patient_modi.getText());
			stmt.setString(8, this.heure_RD_modi.getText());
			stmt.setString(9, this.date_RD_modi.getEditor().getText());
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date =sdf.parse(this.date_RD_modi.getEditor().getText());
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
			 
			if(rs.isClosed()) stmt.setString(10, "non");
			 else stmt.setString(10, "oui");
			
			stmt.execute();	
			conn.close();
			
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
