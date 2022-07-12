package Medecin;

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
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

public class AjouterPatientController implements Initializable {
	@FXML
	private TextField id;
	@FXML
	private TextField nom;
	@FXML
	private TextField prenom;
	@FXML
	private DatePicker dateNaissance;
	@FXML
	private TextField sex;
	@FXML
	private TextField tel;
	@FXML
	private TextField email;
	@FXML
	private TextField adresse;
	@FXML
	private DatePicker date_premiere_visite;
	@FXML
	private DatePicker date_derniere_visite;
	@FXML
	private ComboBox jour_RD;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@FXML 
	private void Confirmer_ajout(ActionEvent event) throws IOException  {
		// Requete sql de l'ajoute de nv patient 
		
		String sqlInsert = "INSERT INTO patient(id,nom,prenom,dateNaissance,sex,tel,email,adresse,date_premiere_visite,date_derniere_visite) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			//verifier  la saisie de ID
	if(this.id.getText().toString().compareTo("")==0 ) {JOptionPane.showMessageDialog(null, "veillez introduire un id");return;}
	else if(!this.date_derniere_visite.getEditor().getText().toString().matches("\\d{2}/\\d{2}/\\d{4}") ) {JOptionPane.showMessageDialog(null, "veillez introduire une date à partir du date picker");return;}
	   else {
		
			// connextion de la BDD 
			Connection conn = dbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			// PRENDRE les information a partir des taxtFild de l'interface  
			stmt.setString(1, this.id.getText());
			stmt.setString(2, this.nom.getText());
			stmt.setString(3, this.prenom.getText());
			stmt.setString(4, this.dateNaissance.getEditor().getText());
			stmt.setString(5, this.sex.getText());
			stmt.setString(6, this.tel.getText());
			stmt.setString(7, this.email.getText());
			stmt.setString(8, this.adresse.getText());
			stmt.setString(9, this.date_premiere_visite.getEditor().getText());
			stmt.setString(10, this.date_derniere_visite.getEditor().getText());
			
			// execute la requete 
			stmt.execute();
			// close la connextion au BDD
			conn.close();
			
			JOptionPane.showMessageDialog(null, "ajout du patient avec succés ");
			// ovrir la fenetre Gerer les patient et fermer tout les autres fenetres
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/Medecin/Medecin.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"le id que vous avez introduit existe déjà ");
			return;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		} 
		
	}

	@FXML 
    public void Annuler(ActionEvent event) {
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
