package Medecin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

public class ModifierPatientController implements Initializable {
	@FXML
	private TextField id_modi;
	@FXML
	private TextField nom_modi;
	@FXML
	private TextField prenom_modi;
	@FXML
	private DatePicker dateNaissance_modi;
	@FXML
	private TextField sex_modi;
	@FXML
	private TextField tel_modi;
	@FXML
	private TextField email_modi;
	@FXML
	private TextField adresse_modi;
	@FXML
	private DatePicker date_premiere_visite_modi;
	@FXML
	private DatePicker date_derniere_visite_modi;
	@FXML
	private ComboBox jour_RD;

// Annuler permetre de routeur au fenetre Gerer les patient 
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//initiliser la fenêtre par ces infos
		this.id_modi.setText(MedecinController.id_ap);
		this.nom_modi.setText(MedecinController.nom_ap);
		this.prenom_modi.setText(MedecinController.prenom_ap);
		this.dateNaissance_modi.getEditor().setText(MedecinController.dateNaissance_ap);
	    this.sex_modi.setText(MedecinController.sex_ap);
		this.tel_modi.setText(MedecinController.tel_ap);
		this.email_modi.setText(MedecinController.email_ap);
		this.adresse_modi.setText(MedecinController.adresse_ap);
		this.date_premiere_visite_modi.getEditor().setText(MedecinController.date_premiere_visite_ap);
		this.date_derniere_visite_modi.getEditor().setText(MedecinController.date_derniere_visite_ap);
		
		
	}
	
	public void confirmer_modif(ActionEvent event)
	{
		String sqlInsert = "UPDATE patient SET id=?, nom = ?,prenom = ? ,dateNaissance = ?, sex = ?,tel = ?, email = ? ,adresse = ? ,date_premiere_visite = ?, date_derniere_visite = ?  WHERE id ='"+MedecinController.id_ap+"'";
		
		
try {
	
	
	
	if(this.id_modi.getText().toString().compareTo("")==0 ) {JOptionPane.showMessageDialog(null, "veillez introduire un id");return;}
	else if(!this.date_derniere_visite_modi.getEditor().getText().toString().matches("\\d{2}/\\d{2}/\\d{4}") ) {JOptionPane.showMessageDialog(null, "veillez introduire une date à partir du date picker");return;}
	   else {
	Connection conn = dbConnection.getConnection();
	PreparedStatement stmt = conn.prepareStatement(sqlInsert);

	

//remplacement des infos introduites	
	stmt.setString(1, this.id_modi.getText());
	stmt.setString(2, this.nom_modi.getText());
	stmt.setString(3,this.prenom_modi.getText() );
	stmt.setString(4, this.dateNaissance_modi.getEditor().getText());
	stmt.setString(5, this.sex_modi.getText());
	stmt.setString(6, this.tel_modi.getText());
	stmt.setString(7, this.email_modi.getText());
	stmt.setString(8, this.adresse_modi.getText());
	stmt.setString(9, this.date_premiere_visite_modi.getEditor().getText());
	stmt.setString(10, this.date_derniere_visite_modi.getEditor().getText());
	stmt.execute();
	conn.close();

	JOptionPane.showMessageDialog(null, "Le patient est modifier avec succés");
	
	
	((Node)event.getSource()).getScene().getWindow().hide();
	Stage primaryStage = new Stage();
	
	FXMLLoader loader = new FXMLLoader();
	Pane root = loader.load(getClass().getResource("/Medecin/Medecin.fxml"));
	Scene scene = new Scene(root);
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
	
	   }} catch (SQLException e) {
	//JOptionPane.showMessageDialog(null, "le id que vous avez introduit existe déjà ");
	JOptionPane.showMessageDialog(null, e);
}catch (Exception e) {
	JOptionPane.showMessageDialog(null,e);
}
		

		
		
	}
	
	
}
