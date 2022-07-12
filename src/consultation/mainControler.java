
package consultation;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.sound.midi.SysexMessage;
import javax.sql.StatementEvent;
import javax.swing.JOptionPane;
import dbUtil.dbConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.Node;


public class mainControler implements Initializable{
	
	

	@FXML
    private  TextField code;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		codeCopier = null ;
		Idcopier = null ;
		rechercheIDnomController.idC=null ;
			o=1;
	}
	//pour controller la fenetre (recherche Id )
	public static int o=0 ;

	@FXML private Button ovrirC;

	  @FXML
	    private Button ajouterConsultation;

	    @FXML
	    private Button modifierConsultation;

	    @FXML
	    private Button Suprimier;

	    @FXML
	    private Button recharche;

	    @FXML
	    private Button deconected;

	    @FXML
	    private Button menupRINCIPAL;

	    

	    @FXML
	    public   TextField idPatient;

	    @FXML
	    private DatePicker dateDeConsultation;

	    @FXML
	    private TextField montant;

	    @FXML
	    private TextArea Commantaire;

	   	public static String Idcopier = null  ;
	   	public static String codeCopier = null ;
	
	public void ovrirConsultation(ActionEvent event) throws IOException {
		
		
		
		try {
			
			Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/GererLesConsultation.fxml"));
		    Scene scene = new Scene(root2);
			
			Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setTitle("Consultation");
		    
			stage.setScene(scene);
			stage.show();
		
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ereur");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
			
		}
	
	
	
	
	
	
	}
	
   
    
	
	
	@FXML 
	private void AjouterConsultation(ActionEvent event) throws IOException{
		
		

		    
			try {
				String sql = "SELECT * FROM patient where id= ?  ";
				  int i=0 ;
				  ResultSet rs = null ;
				//connextion de BDD 
					Connection conn0 = dbConnection.getConnection();
					PreparedStatement stmt0 = conn0.prepareStatement(sql);
						stmt0 = conn0.prepareStatement(sql);
						stmt0.setString(1,idPatient.getText());
						
						rs = stmt0.executeQuery();
						
						
						 while(rs.next()) { 
							i=1;}
					// verification des information 	
				if (i==0 || idPatient.getText().equals(null)|| idPatient.getText().equals("")) {Alert alert = new Alert(AlertType.WARNING);
				//message D'erruer 
				alert.setTitle("Ereur");
				alert.setHeaderText("Verifier votre ID !!");
				alert.setContentText("le patient n'existe pas dans la base des données ");
				alert.showAndWait();}
				else {
					if (this.code.getText().equals("")) {Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Ereur");
					alert.setHeaderText("Verifier votre Code !!");
					alert.setContentText(null);
					alert.showAndWait();}
					else {
						
				String sql2= "INSERT  INTO  Consultation(code,id,date,montant,commantaire) VALUES (?,?,?,?,?)";
				Connection conn = dbConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(sql2);
				
				stmt.setString(1, this.code.getText());
				stmt.setString(2, this.idPatient.getText());
				stmt.setString(3,( (TextField)dateDeConsultation.getEditor()).getText());
				
				Integer.parseInt(this.montant.getText());
			
				stmt.setString(4, this.montant.getText());
				
				stmt.setString(5, this.Commantaire.getText());
				
				
			
				
				stmt.execute();
				conn.close();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Action Réussi ");
				alert.setHeaderText("l'ajout est terminée avec succées ");
				alert.setContentText(null);
				alert.showAndWait();
				
				}
			
			} }catch(SQLException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Ereur");
				alert.setHeaderText("Verifier votre information !!");
				alert.setContentText(e.toString());
				alert.showAndWait();
				
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "veillez intoduire un nombre dand le montant");
			}
			
			
		}
	
	
	@FXML 
	private void RecharcheConsultation(ActionEvent event) throws IOException{
		
		try {
			Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/RecherchConsulation.fxml"));
		    Scene scene = new Scene(root2);
			
		
		    
		    o=0;
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setTitle( "Recharche");
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {
			System.out.println(e);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ereur");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}
	
	
	
public void Modifier(ActionEvent event) throws IOException {
		
		
		
		try {
			
			Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/modifierConsulation.fxml"));
		    Scene scene = new Scene(root2);
			
			Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setTitle("Modifier ");
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ereur");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	
	
	
	}
     

@FXML
private TextField code2;
@FXML
private TextArea Commantaire2;
@FXML
private Button okaffiche;

@FXML
private TextField idPatient2;

@FXML
private TextField montant2;

@FXML
private DatePicker dateDeConsultation2;

@FXML
private Button modifierConsultation2;

@FXML 
private void Sauvgarder(ActionEvent event) throws IOException{
	
	 PreparedStatement ps= null ;
		Connection conn = dbConnection.getConnection();

		 
		try {
		
	
String sql2= "UPDATE   Consultation SET id= ? ,date = ?,montant = ?  ,Commantaire = ? where code = ? ";
	    
		
			
			
			//Connection conn2 = Dconnection.connectBd() ;
			PreparedStatement stmt = conn.prepareStatement(sql2);
			
			stmt.setString(5, this.code2.getText());
			stmt.setString(1, this.idPatient2.getText());
			stmt.setString(2,( (TextField)dateDeConsultation2.getEditor()).getText());
			
			stmt.setString(3, this.montant2.getText());
			
			stmt.setString(4, this.Commantaire2.getText());
			
			stmt.executeUpdate();
			conn.close();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mise à jour ");
			//alert.setHeaderText(null);
			alert.setContentText("Mise à jour terminer avec succés  ");
			alert.showAndWait();
			
			code2.setText(null);this.idPatient2.setText(null);this.dateDeConsultation2.getEditor().setText(null);  this.montant2.setText(null); this.Commantaire2.setText(null);
			
			
			this.modifierConsultation2.setDisable(true);
		
		} catch(SQLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ereur");
			alert.setHeaderText("Verifier votre information !!");
			alert.setContentText(e.toString());
			alert.showAndWait();
			
		} 
		
}
////////////////--------------	l'etat patient -----------------------/////////////////////////////////

@FXML
private Button Etat;






public void Etat(ActionEvent event) throws IOException {
	
	
	
	try {
		Idcopier = this.idPatient.getText();
		
		Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/Etat De patient.fxml"));
	    Scene scene = new Scene(root2);
	    
	    //group.setItems(FXCollections.observableArrayList(choix.valueProperty());
		Stage stage = new Stage();
		
		stage.setTitle("Etat de patient ");
		
	     
		stage.setScene(scene);
		stage.show();
		
	} catch (Exception e) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Ereur");
		alert.setHeaderText(null);
		alert.setContentText(e.toString());
		alert.showAndWait();
	}
 }


public void ovrirFznztreMedicament(ActionEvent event) throws IOException {
	
	
	
	try {
		Idcopier = this.idPatient.getText();
		codeCopier = this.code.getText();
		//ResourceBundle rs = ResourceBundle.getBundle("languege.lang_pt");
		Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/Traitement Prescrit.fxml"));
	    Scene scene = new Scene(root2);
		
	    Stage stage = new Stage();
		stage.setTitle("Consultation");
		stage.setScene(scene);
		stage.show();
		
	} catch (Exception e) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Ereur");
		alert.setHeaderText(null);
		alert.setContentText(e.toString());
		alert.showAndWait();
	}






}
@FXML
private Button routour ;
@FXML
private void RotourMenu(ActionEvent event) throws IOException{
	try {
		Parent root1 =  FXMLLoader.load(getClass().getResource("/Medecin/MenuPrincipal.fxml"));

		Scene scene = new Scene(root1);


		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("menu");
		stage.setScene(scene);
		stage.show();

	} catch (Exception e) {Alert alert = new Alert(AlertType.WARNING);
	alert.setTitle("Ereur");
	alert.setHeaderText(null);
	alert.setContentText(e.toString());
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
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();

		
	} catch (Exception e) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Ereur");
		alert.setHeaderText(null);
		alert.setContentText(e.toString());
		alert.showAndWait();
	}

	
	
}
public void ovrirMaladies(ActionEvent event) throws IOException {
	
	
	
	try {
		Idcopier = this.idPatient.getText();
		codeCopier = this.code.getText();
		
		Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/maladies chroniques .fxml"));
	    Scene scene = new Scene(root2);
	    
	    //group.setItems(FXCollections.observableArrayList(choix.valueProperty());
		Stage stage = new Stage();
		
		stage.setTitle("Maladies chroniques ");
		
	     
		stage.setScene(scene);
		stage.show();
		
	} catch (Exception e) {
		System.out.println(e);
	}
 }
public void ovrirMaladiesDiagnostique(ActionEvent event) throws IOException {
	
	
	
	try {
		Idcopier = this.idPatient.getText();
		codeCopier = this.code.getText();
		Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/maladies Diagnostique.fxml"));
	    Scene scene = new Scene(root2);
	    
	    //group.setItems(FXCollections.observableArrayList(choix.valueProperty());
		Stage stage = new Stage();
		
		stage.setTitle("Maladies Diagnostique ");
		
	     
		stage.setScene(scene);
		stage.show();
		
	} catch (Exception e) {
		System.out.println(e);
	}
 }
public void ovrirBilan(ActionEvent event) throws IOException {
	
	
	
	try {
		Idcopier = this.idPatient.getText();
		
		Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/bilan.fxml"));
	    Scene scene = new Scene(root2);
	    
	    //group.setItems(FXCollections.observableArrayList(choix.valueProperty());
		Stage stage = new Stage();
		
		stage.setTitle(" Bilan  ");
		
	     
		stage.setScene(scene);
		stage.show();
		
	} catch (Exception e) {
		System.out.println(e);
	}
}	
public void recherchID(ActionEvent event) throws IOException {
	
	
	
	try {
		Parent root1 =  FXMLLoader.load(getClass().getResource("/consultation/rechercheId.fxml"));

		Scene scene = new Scene(root1);


		Stage stage =new Stage();
		stage.setTitle("Recherche ID ");
		stage.setScene(scene);
		stage.show();
	} catch (Exception e) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Ereur");
		alert.setHeaderText(null);
		alert.setContentText(e.toString());
		alert.showAndWait();
		
	}

}
public void coller (ActionEvent event) throws IOException {
	
	idPatient.setText(rechercheIDnomController.idC);
	
	
}
/// la methode GnereteCode prendre la date de l'ajoute deconsultation et verifier avec la date de la BDD SI le meme date donc elle prendre le numiro séquenciel de la BDD ET  Le incrémanter et modifier 
//le numéro dans la base sinon elle modifier la date ET le numiro séquenciel  de la BDD .

public static String variable ;
public static  int nbSéq;
@FXML
private Button generate;
public void GenerateCode (ActionEvent event) throws IOException, SQLException {
	
	int nbr = 0 ;
	Date today = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String str0 =sdf.format(today);
	String sql = "SELECT * FROM CodeConsultation";
	Connection conn0 = dbConnection.getConnection();

	PreparedStatement ps = conn0.prepareStatement(sql);
	ps = conn0.prepareStatement(sql);
	  ResultSet rs = null ;
	rs = ps.executeQuery();
	while (rs.next()) {
		
	
		variable = rs.getString("date");
		
	   nbr = rs.getInt("NumSequenciel");	
	}
	if (str0.equals(variable))
	{   
		nbSéq = nbr ;
		nbSéq ++ ;   
		 String sc = str0.concat(" "+ nbSéq);
		this.code.setText(sc);
		String sql2= "UPDATE   CodeConsultation SET NumSequenciel= ? where date = ? ";	
		
		PreparedStatement stmt7 = conn0.prepareStatement(sql2);
		
		stmt7.setInt(1, nbSéq);
		stmt7.setString(2, variable);
		stmt7.executeUpdate();
		conn0.close();
		
	}else {
		String sql2= "DELETE From codeConsultation ";
		Connection conn1 = dbConnection.getConnection();

		PreparedStatement stmt1 = conn1.prepareStatement(sql2);
		stmt1.execute();
		conn1.close();
		  nbSéq=1;
			String sql3= "INSERT  INTO  CodeConsultation(NumSequenciel,date) VALUES ("+nbSéq+",?)";
			Connection conn = dbConnection.getConnection();
          
			PreparedStatement stmt = conn.prepareStatement(sql3);
			stmt.setString(1,str0);
			stmt.execute();
			conn.close();
			 variable = str0; 
			  String sc = str0.concat(" "+ nbSéq);
				this.code.setText(sc);
		
	}
	
	

	
	
}
}
	





