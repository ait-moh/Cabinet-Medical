package consultation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dbUtil.*;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ConsultationModifier implements Initializable 
{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		if (mainControler.o ==-1)	
			this.id2.setText(rechercheIDnomController.idC);
		mainControler.o=-1;
	code2.setDisable(true);
	montant2.setDisable(true);
	dateDeConsultation2.setDisable(true);
	Commantaire2.setDisable(true);
   modifierConsultation2.setDisable(true);
   okaffiche1.setDisable(true);
		
	}
	 private ObservableList<Consultation> data;
	 public ObservableList<Consultation> data2;
	  @FXML
	    private TextField code2;

	    @FXML
	    private Button okaffiche;

	    @FXML
	    private TextField id2;

	    @FXML
	    private TextField montant2;

	    @FXML
	    private DatePicker dateDeConsultation2;

	    @FXML
	    private Button modifierConsultation2;

	    @FXML
	    private TextArea Commantaire2;

	    @FXML
	    private TableView<Consultation> ConsultationTable2;

	    @FXML
	    private TableColumn<Consultation,String> code_column2;

	    @FXML
	    private TableColumn<Consultation,String> montant_column2;

	    @FXML
	    private TableColumn<Consultation,String> date_column2;

	    @FXML
	    private TableColumn<Consultation,String> Commantaire_column2;

	    @FXML
	    private Button okaffiche1;
	
	public void affichage(ActionEvent event) throws IOException, SQLException {
		if (id2.getText().equals("")) {
			
			Alert alert2 = new Alert(AlertType.WARNING);
			alert2.setTitle("Ereur");
			alert2.setHeaderText("Id est vide !!");
			alert2.setContentText(null);
			alert2.showAndWait();
			
			}
	
	
		else {
		PreparedStatement ps= null ;
		ResultSet rs = null ;
		//connextion de BDD 
		Connection conn = dbConnection.getConnection();
		String sql = "SELECT * FROM Consultation where id= ?  ";
	 int i = 0 ;
		try {
			 
			ps = conn.prepareStatement(sql);
			ps.setString(1,id2.getText());
			
			rs = ps.executeQuery();
			
			this.data= FXCollections.observableArrayList();
			 while(rs.next()) {
				 i = 1;
				this.data.add(   
			new Consultation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) ) );
				
			}
			
		} catch (SQLException e) {
			Alert alert0 = new Alert(AlertType.WARNING);
			alert0.setTitle("Ereur");
			alert0.setHeaderText(e.toString());
			
			alert0.showAndWait();
		}
		 // chargement des données dans le tableView pour l'afichage 
		
		this.code_column2.setCellValueFactory(new PropertyValueFactory<Consultation,String>("code"));
		
		this.date_column2.setCellValueFactory(new PropertyValueFactory<Consultation,String>("dateConsultation"));
		
		this.montant_column2.setCellValueFactory(new PropertyValueFactory<Consultation,String>("montant"));
		
		this.Commantaire_column2.setCellValueFactory(new PropertyValueFactory<Consultation,String>("Commantaire"));
		
		this.ConsultationTable2.setItems(this.data);
		 
			
		if (i==0) {
				Alert alert0 = new Alert(AlertType.WARNING);
				alert0.setTitle(null);
				alert0.setHeaderText(" Il n'a pas des Consultations 'verifier votre ID'");
				
				alert0.showAndWait();
					}
		else okaffiche1.setDisable(false);
		ps.close();
		}
	}   
	
	public void modifier(ActionEvent event) throws IOException, SQLException {
		try {
			if (this.ConsultationTable2.getSelectionModel().getSelectedIndex()==-1)
				{Alert alert0 = new Alert(AlertType.WARNING);
				alert0.setTitle("Ereur");
				alert0.setHeaderText("Sélectionner la Consultation !!");
				
				alert0.showAndWait();  }
			else {
				
				code2.setDisable(false);
				montant2.setDisable(false);
				dateDeConsultation2.setDisable(false);
				Commantaire2.setDisable(false);
			modifierConsultation2.setDisable(false);
			id2.setDisable(true);
			//chargement des données dans la tableView
			code2.setText(this.code_column2.getCellData(this.ConsultationTable2.getSelectionModel().getSelectedIndex()));
			montant2.setText(this.montant_column2.getCellData(this.ConsultationTable2.getSelectionModel().getSelectedIndex()));
			dateDeConsultation2.getEditor().setText(this.date_column2.getCellData(this.ConsultationTable2.getSelectionModel().getSelectedIndex()));
			Commantaire2.setText(this.Commantaire_column2.getCellData(this.ConsultationTable2.getSelectionModel().getSelectedIndex()));
			okaffiche1.setDisable(true);
			}
			
		} catch (Exception e) {
			Alert alert0 = new Alert(AlertType.WARNING);
			alert0.setTitle("Ereur");
			alert0.setHeaderText(" verifier votre information !! ");
			alert0.setContentText(e.toString());
			alert0.showAndWait();
			
		}
		
	
	
	}
	
	
	
	
public void Sauvgarder(ActionEvent event) throws IOException, SQLException{
		
		 
	Connection conn = dbConnection.getConnection();			 
			try {
			
		// requete Sql pour la modification
	String sql2= "UPDATE   Consultation SET code = ? ,date = ?,montant = ?  ,Commantaire = ? where id = ? ";
		    
			
				
				
				//Connection conn2 = Dconnection.connectBd() ;
				PreparedStatement stmt = conn.prepareStatement(sql2);
				// obtenir les information a partir des taxtFils de interface
				stmt.setString(5, this.id2.getText());
				stmt.setString(1, this.code2.getText());
				stmt.setString(2,( (TextField)dateDeConsultation2.getEditor()).getText());
				
				Integer.parseInt(this.montant2.getText());
				stmt.setString(3, this.montant2.getText());
				
				stmt.setString(4, this.Commantaire2.getText());
				
				//executer le Update 
				stmt.executeUpdate();
				conn.close();
				
				Alert alert0 = new Alert(AlertType.INFORMATION);
				alert0.setTitle("Mise à jour ");
				alert0.setHeaderText(" Mise à jour terminer avec succès !!");
				alert0.setContentText(null);
				alert0.showAndWait();
				
				code2.setText(null);this.id2.setText(null);this.dateDeConsultation2.getEditor().setText(null);  this.montant2.setText(null); this.Commantaire2.setText(null);
				this.ConsultationTable2.setItems(null);
				
				
				
				this.id2.setDisable(false);
				okaffiche1.setDisable(true);
				this.modifierConsultation2.setDisable(true);
			
			} catch(SQLException e) {
				Alert alert0 = new Alert(AlertType.WARNING);
				alert0.setTitle("Ereur");
				alert0.setHeaderText(" verifier votre information !! ");
				alert0.setContentText(e.toString());
				alert0.showAndWait();
				
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "veillez intoduire un nombre dand le montant");
			} 
			
	}

@FXML
private Button modifierB;

@FXML
private Button MMaladies;

@FXML
private Button MEtat;
public void Etat(ActionEvent event) throws IOException {
	
	
	
	try {
		
		Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/Etat De patient.fxml"));
	    Scene scene = new Scene(root2);
	    
	    
		Stage stage = new Stage();
		
		stage.setTitle("Etat de patient ");
		
	     
		stage.setScene(scene);
		stage.show();
		
	} catch (Exception e) {
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText(null);
		alert0.setContentText(e.toString());
		alert0.showAndWait();
	}
 }
public void ovrirMaladies(ActionEvent event) throws IOException {
	
	
	
	try {
		
		Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/maladies chroniques .fxml"));
	    Scene scene = new Scene(root2);
	    
	    
		Stage stage = new Stage();
		
		stage.setTitle("Maladies chroniques ");
		
	     
		stage.setScene(scene);
		stage.show();
		
	} catch (Exception e) {
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText(null);
		alert0.setContentText(e.toString());
		alert0.showAndWait();
	}
 }
	
public void ovrirBilan(ActionEvent event) throws IOException {
	
	
	
	try {
		
		Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/bilan.fxml"));
	    Scene scene = new Scene(root2);
	    
	   
		Stage stage = new Stage();
		
		stage.setTitle(" Bilan  ");
		
	     
		stage.setScene(scene);
		stage.show();
		
	} catch (Exception e) {
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText(null);
		alert0.setContentText(e.toString());
		alert0.showAndWait();
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
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText(null);
		alert0.setContentText(e.toString());
		alert0.showAndWait();
		
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
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText(null);
		alert0.setContentText(e.toString());
		alert0.showAndWait();
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
			Alert alert0 = new Alert(AlertType.WARNING);
			alert0.setTitle("Ereur");
			alert0.setHeaderText(null);
			alert0.setContentText(e.toString());
			alert0.showAndWait();
		}
	
	
	}	
	public void ovrirMaladiesDiagno(ActionEvent event) throws IOException {
		
		
		
		try {
			
			Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/maladies Diagnostique.fxml"));
		    Scene scene = new Scene(root2);
		    
		    //group.setItems(FXCollections.observableArrayList(choix.valueProperty());
			Stage stage = new Stage();
			
			stage.setTitle("Maladies Diagnostique ");
			
		     
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {
			Alert alert0 = new Alert(AlertType.WARNING);
			alert0.setTitle("Ereur");
			alert0.setHeaderText(null);
			alert0.setContentText(e.toString());
			alert0.showAndWait();
		}
	 }
	public void recherchID(ActionEvent event) throws IOException {
		
		
		
		try {
			Parent root1 =  FXMLLoader.load(getClass().getResource("/consultation/rechercheId.fxml"));

			Scene scene = new Scene(root1);


			Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
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
}
