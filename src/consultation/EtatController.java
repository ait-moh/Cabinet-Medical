package consultation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.beans.IDProperty;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EtatController implements Initializable{
	@FXML
	private ComboBox group;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		     idEtat.setText(mainControler.Idcopier);
			ObservableList<String> choise = FXCollections.observableArrayList("null","A+", "A-","B+","B-","AB+","AB-","O+","O-");
			group.setItems(choise);
			this.modifier.setDisable(true);
			this.group.getSelectionModel().selectFirst();
	
	
	}

@FXML
private TextField idEtat;
@FXML
private TextField tauxDiabete;

@FXML
private TextField tension;

@FXML
private TextField taille;

@FXML
private TextField poinds;

@FXML
private Button affiche;


	public void affechierEtat(ActionEvent event) throws IOException {
		if (idEtat.getText().equals("")) {
			
			Alert alert2 = new Alert(AlertType.WARNING);
			alert2.setTitle("Ereur");
			alert2.setHeaderText("Id est vide !!");
			alert2.setContentText(null);
			alert2.showAndWait();
			
			}
	
	
		else {
		 PreparedStatement ps= null ;
			ResultSet rs = null ;
			Connection conn = dbConnection.getConnection();
		  String sql = "SELECT * FROM EtatPatient where idPatient = ?  ";
			try {
              
				int i=0 ;
				ps = conn.prepareStatement(sql);
				ps.setString(1,idEtat.getText());
				
				rs = ps.executeQuery();
				
				
				 while(rs.next()) {
					 i++;
					 if((rs.getString(2)==null)&(rs.getString(3)==null)&(rs.getString(4)==null)&(rs.getString(5)==null)&(rs.getString(6)==null)) {
						 Alert alert = new Alert(AlertType.INFORMATION);
					 
						alert.setTitle("Etat");
						alert.setHeaderText("les information sur  l'Etat est vide ");
						alert.setContentText(null);
						  }
					 this.group.setValue(rs.getString(2));
				      taille.setText(rs.getString(3));
				     this.poinds.setText(rs.getString(4));
				     this.tension.setText(rs.getString(5));
				     this.tauxDiabete.setText(rs.getString(6));
				     this.modifier.setDisable(false);
				}
			if (i==0) {
	        Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(null);
			alert.setHeaderText("le patient n'a pas encore une Etat ");
			
			alert.showAndWait();}
			}catch (SQLException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(null);
				alert.setHeaderText(e.toString());
				
				alert.showAndWait();
			}
		
		}
	}
	  @FXML
	    private Button ajouter;


	@FXML 
	private void AjouterEtat(ActionEvent event) throws IOException{
		try {
			String sql1 = "SELECT * FROM patient where id= ?  ";
			  int y=0 ;
			  ResultSet rs = null ;
				Connection conn0 = dbConnection.getConnection();
				PreparedStatement stmt0 = conn0.prepareStatement(sql1);
					stmt0 = conn0.prepareStatement(sql1);
					stmt0.setString(1,idEtat.getText());
					
					rs = stmt0.executeQuery();
					
					
					 while(rs.next()) { 
						y=1;}
					
			if (y==0 || idEtat.getText().equals(null)|| idEtat.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ereur");
			alert.setHeaderText("Verifier votre ID !!");
			alert.setContentText("le patient n'existe pas dans la base des données ");
			alert.showAndWait();
			 }
			else {
				if(this.taille.getText().equals("")&&this.poinds.getText().equals("")&&this.tension.getText().equals("")&&this.tauxDiabete.getText().equals("")&&group.getSelectionModel().getSelectedItem().toString().equals("null"))
				{  Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Ereur");
				alert.setHeaderText("null des Information ");
				alert.setContentText("Remplissez les information !! ");
				alert.showAndWait();  }
				else {
		 String sql= "INSERT INTO  EtatPatient(idpatient,groupeSanguin,taille,poids,tension,teuxDiabete) VALUES (?,?,?,?,?,?);";
		    
			
				
				Connection conn = dbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, this.idEtat.getText());
				stmt.setString(2,(String)group.getSelectionModel().getSelectedItem().toString());
				stmt.setString(3,this.taille.getText());
				stmt.setString(4, this.poinds.getText());
				stmt.setString(5, this.tension.getText());
				stmt.setString(6, this.tauxDiabete.getText());
				
				
				
			
				
				stmt.execute();
				conn.close();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(null);
				alert.setHeaderText( " l'Etat est bien ajoutez ");
				
				alert.showAndWait();
		        this.idEtat.setDisable(true);
				this.taille.setText(null);this.group.setValue("null");this.poinds.setText(null);  this.tension.setText(null);this.tauxDiabete.setText(null); 
			
			}} }catch(SQLException e) {
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Ereur");
				alert.setHeaderText("il existe déjà une Etat pour ce patient ");
				alert.setContentText("tu peux faire la modification  ");
				alert.showAndWait();
			} 
			
		}
	
	@FXML
	private Button modifier ;

@FXML 
private void Modifier(ActionEvent event) throws IOException{
	
	 PreparedStatement ps= null ;
		Connection conn = dbConnection.getConnection();
		 
		try {
			
	
String sql= "UPDATE   EtatPatient SET groupeSanguin = ?,taille = ?,poids = ? ,tension = ? ,teuxDiabete = ? where idpatient= ?  ";
	    
		
			//Connection conn2 = Dconnection.connectBd() ;
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			stmt.setString(1,(String)group.getSelectionModel().getSelectedItem().toString());
			stmt.setString(2,taille.getText());
			stmt.setString(3, this.poinds.getText());
			stmt.setString(4, this.tension.getText());
			stmt.setString(5, this.tauxDiabete.getText());
			stmt.setString(6, this.idEtat.getText());

			stmt.executeUpdate();
			conn.close();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mise à jour ");
			alert.setHeaderText("Mise à jour terminer avec succée ");
			
			alert.showAndWait();
			
			idEtat.setDisable(true);;this.taille.setText(null);this.group.setValue("null");this.poinds.setText(null);  this.tension.setText(null);this.tauxDiabete.setText(null); 
			
			
			this.modifier.setDisable(true);
		
		} catch(SQLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(e.toString());
			
			alert.showAndWait();	
		} 
		
}
	
	
	
@FXML
private void RotourMenu(ActionEvent event) throws IOException{
	try {
		Parent root1 =  FXMLLoader.load(getClass().getResource("/Medecin/MenuPrincipal.fxml"));

		Scene scene = new Scene(root1);


		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("menu");
		stage.setScene(scene);
		stage.show();

	} catch (Exception e) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setHeaderText(e.toString());
		
		alert.showAndWait();
	}

}
@FXML
private MenuItem close;





@FXML 
private void deconnecter(ActionEvent event) throws IOException{
	try {
		Parent root1 =  FXMLLoader.load(getClass().getResource("/application/login.fxml"));

		Scene scene = new Scene(root1);


		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("menu");
		stage.setScene(scene);
		stage.show();

		
	} catch (Exception e) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setHeaderText(e.toString());
		
		alert.showAndWait();
	}
}
	public void ovrirConsultation(ActionEvent event) throws IOException {
		
		
		
		try {
			//ResourceBundle rs = ResourceBundle.getBundle("languege.lang_pt");
			Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/GererLesConsultation.fxml"));
		    Scene scene = new Scene(root2);
			
			Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setTitle("Consultation");
		    
			stage.setScene(scene);
			stage.show();
		
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setHeaderText(e.toString());
			
			alert.showAndWait();
		}
	
	
	
	
	
	
	}	
	

}
