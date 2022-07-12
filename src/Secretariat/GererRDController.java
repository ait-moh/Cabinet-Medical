package Secretariat;


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

import Medecin.AjouterPatientController;
import Medecin.MedecinController;
import Medecin.Patient;
import application.LoginModel;
import application.option;
import dbUtil.dbConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class GererRDController implements Initializable {

   
	@FXML private TextField nom_patient;
	@FXML private ComboBox<jour> jour_RD;
	
	
	
	@FXML private TableView<Rendez_vous> RD_Table;
	@FXML private TableColumn<Rendez_vous,String> num_RD_column;
	@FXML private TableColumn<Rendez_vous,String> id_patient_column;
	@FXML private TableColumn<Rendez_vous,String> nom_patient_column;
	@FXML private TableColumn<Rendez_vous,String> prenom_patient_column;
	@FXML private TableColumn<Rendez_vous,String> tel_patient_column;
	@FXML private TableColumn<Rendez_vous,String> email_patient_column;
	@FXML private TableColumn<Rendez_vous,String> jour_RD_column;
	@FXML private TableColumn<Rendez_vous,String> heure_RD_column;
	@FXML private TableColumn<Rendez_vous,String> date_RD_column;
	@FXML private TableColumn<Rendez_vous,String> inscrit_en_BD_column;
	
	
	
	private dbConnection dc;
	ObservableList<Rendez_vous> data;
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.dc = new dbConnection();
		jour_RD.setItems(FXCollections.observableArrayList(jour.values()));
	}

	@FXML 
	private void loadRDdata(ActionEvent event)throws SQLException {
		if(jour_RD.getValue() == null) JOptionPane.showMessageDialog(null, "veuillez spécifier un jour pour l'agenda");
		else {
	  String sql = "SELECT * FROM rendez_vous WHERE jour_RD ='"+this.jour_RD.getValue().toString()+"'";
		try {
			Connection conn = dbConnection.getConnection();
			
			
			ResultSet rs = conn.createStatement().executeQuery(sql);
			this.data = FXCollections.observableArrayList();
			while(rs.next()) {
				this.data.add(    new Rendez_vous(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) , rs.getString(7) , rs.getString(8) , rs.getString(9), rs.getString(10) ) );
		        	
			}
			
		} catch (SQLException e) {
			System.err.println("Error" + e);
		}
		
		
		this.num_RD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("num_RD"));
		this.id_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("id"));
		this.nom_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("nom_patient"));
		this.prenom_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("prenom_patient"));
		this.tel_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("tel_patient"));
		this.email_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("email_patient"));
		this.jour_RD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("jour_RD"));
		this.heure_RD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("heure_RD"));
		this.date_RD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("date_RD"));
		this.inscrit_en_BD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("inscrit_en_BD"));

		
		this.RD_Table.setItems(null);
		this.RD_Table.setItems(this.data);
		}
	}
	
	
	@FXML 
	private void addRD(ActionEvent event) throws IOException {
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
					
					Stage adminStage = new Stage();
					FXMLLoader Loader = new FXMLLoader();
					Pane root =(Pane) Loader.load(getClass().getResource("/Secretariat/PresAjoutRD.fxml").openStream());
					PresAjoutRDController presAjoutRDController = (PresAjoutRDController)Loader.getController();
					
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
	
	@FXML private TextField id ;
	@FXML private DatePicker dateNaissance;
	@FXML private TextField sex;
	@FXML private TextField adresse;
	@FXML private DatePicker date_premiere_visite;
	@FXML private DatePicker date_derniere_visite;
	
	 public static String nom_aBD=null,prenom_aBD=null,tel_aBD=null,email_aBD=null;
	public static String num_RD = null;
	@FXML 
	private void AjoutBD(ActionEvent event) {
try {
	if (this.RD_Table.getSelectionModel().getSelectedIndex()==-1) JOptionPane.showMessageDialog(null, "veuillez sélectionner un rendez-vous dans la table");
	else {
		
		nom_aBD=this.nom_patient_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
		prenom_aBD=this.prenom_patient_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
		tel_aBD=this.tel_patient_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
		email_aBD=this.email_patient_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
		num_RD = this.num_RD_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
		
		
	
		((Node)event.getSource()).getScene().getWindow().hide();
		
		Stage adminStage = new Stage();
		FXMLLoader Loader = new FXMLLoader();
		Pane root =(Pane) Loader.load(getClass().getResource("/Secretariat/AjoutPatientEnBD.fxml").openStream());
		GererRDController gererRDController = (GererRDController)Loader.getController();
		
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		adminStage.setScene(scene);
		adminStage.setTitle("Ajouter un patient en base de données");
		adminStage.setResizable(false);
		adminStage.show();
	}	

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
}
	
	@FXML 
	private void Confirmer(ActionEvent event)throws SQLException {
		
		
			String sqlInsert = "INSERT INTO patient(id,nom,prenom,dateNaissance,sex,tel,email,adresse,date_premiere_visite,date_derniere_visite) VALUES (?,?,?,?,?,?,?,?,?,?)";
	       
	try {
		if(this.id.getText().toString().compareTo("")==0 ) {JOptionPane.showMessageDialog(null, "veillez introduire un id");return;}
		else if(!this.date_derniere_visite.getEditor().getText().toString().matches("\\d{2}/\\d{2}/\\d{4}") ) {JOptionPane.showMessageDialog(null, "veillez introduire une date à partir du date picker");return;}
		   else {
			
		

		Connection conn = dbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		
		stmt.setString(1, this.id.getText());
		stmt.setString(2, nom_aBD);
		stmt.setString(3,prenom_aBD );
		stmt.setString(4, this.dateNaissance.getEditor().getText());
		stmt.setString(5, this.sex.getText());
		stmt.setString(6, tel_aBD);
		stmt.setString(7, email_aBD);
		stmt.setString(8, this.adresse.getText());
		stmt.setString(9, this.date_premiere_visite.getEditor().getText());
		stmt.setString(10, this.date_derniere_visite.getEditor().getText());
		stmt.execute();
		

		String sql5 = "UPDATE rendez_vous SET id=?  WHERE num_RD ='"+this.num_RD+"'";
		PreparedStatement stmt5 = conn.prepareStatement(sql5);
		stmt5.setString(1, this.id.getText());
		stmt5.execute();
	
		
		
		
		conn.close();
		
		
	
		JOptionPane.showMessageDialog(null, "Le patient est ajouté à la base de données avec succés");
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/Secretariat/gererRD.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		   }} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "le id que vous avez introduit existe déjà ");
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
			Pane root = loader.load(getClass().getResource("/Secretariat/gererRD.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    }
	
	
	@FXML 
	private void deleteRD(ActionEvent event) {
		if (this.RD_Table.getSelectionModel().getSelectedIndex()==-1) JOptionPane.showMessageDialog(null, "veuillez sélectionner un rendez-vous dans la table");
		else {
		String sqlInsert = "Delete From rendez_vous Where num_RD = ?";
	       
	try {
		
		Connection conn = dbConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1,this.num_RD_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex()));
		stmt.execute();
		RD_Table.getItems().remove(this.RD_Table.getSelectionModel().getSelectedIndex());
		conn.close();
		JOptionPane.showMessageDialog(null, "suprresion efféctuée avec succés");
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e);
	}}
	
   
	}
	
	
	
	
	
	
	
	
	
	
	public void rechercher(ActionEvent event) {
		 String sql = "SELECT * FROM rendez_vous WHERE nom_patient = '"+this.nom_patient.getText()+"'";
			try {
				Connection conn = dbConnection.getConnection();
				this.data = FXCollections.observableArrayList();
				
				ResultSet rs = conn.createStatement().executeQuery(sql);
				
				
				while(rs.next()) {
					this.data.add(    new Rendez_vous(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) , rs.getString(7) , rs.getString(8) , rs.getString(9), rs.getString(10) ) );
				}
				
			} catch (SQLException e) {
				System.err.println("Error" + e);
			}
			
			
			this.num_RD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("num_RD"));
			this.id_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("id"));
			this.nom_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("nom_patient"));
			this.prenom_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("prenom_patient"));
			this.tel_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("tel_patient"));
			this.email_patient_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("email_patient"));
			this.jour_RD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("jour_RD"));
			this.heure_RD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("heure_RD"));
			this.date_RD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("date_RD"));
			this.inscrit_en_BD_column.setCellValueFactory(new PropertyValueFactory<Rendez_vous,String>("inscrit_en_BD"));

			

			this.RD_Table.setItems(null);
			this.RD_Table.setItems(this.data);
		
	}
	
	
		
	
	
	public void SignOut(ActionEvent event) {
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/login.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void retourAuMenuPrincipal(ActionEvent event) {
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/Medecin/MenuPrincipal.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	    @FXML private TextField id_patient_modif;
		@FXML private TextField nom_patient_modi;
		@FXML private TextField prenom_patient_modi;
		@FXML private TextField tel_patient_modi;
		@FXML private TextField email_patient_modi;
		@FXML private TextField num_RD_modi;
		//@FXML private ComboBox<jour> jour_RD_modi;
		@FXML private TextField heure_RD_modi;
		@FXML private DatePicker date_RD_modi;
		 public static String id_modif=null,nom_modif=null,prenom_modif=null,tel_modif=null,email_modif=null,numRD_modif=null,heure_modif=null,date_modif=null;
		@FXML 
		private void modifierRD(ActionEvent event) {
	try {
		if (this.RD_Table.getSelectionModel().getSelectedIndex()==-1) JOptionPane.showMessageDialog(null, "veuillez sélectionner un rendez-vous dans la table");
		else {
			//récupérer les infos du patient sélectionné dans des variables static pour que l'utilisateur
			//les trouvent dans la fenêtre modifer
			id_modif=this.nom_patient_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
			nom_modif=this.prenom_patient_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
			tel_modif=this.tel_patient_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
			email_modif=this.email_patient_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
			numRD_modif=this.num_RD_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
			heure_modif=this.heure_RD_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
			date_modif=this.date_RD_column.getCellData(this.RD_Table.getSelectionModel().getSelectedIndex());
			
			
		
			((Node)event.getSource()).getScene().getWindow().hide();
			
			Stage adminStage = new Stage();
			FXMLLoader Loader = new FXMLLoader();
			Pane root =(Pane) Loader.load(getClass().getResource("/Secretariat/ModifierRD.fxml").openStream());
			ModifierRDController modifierRDController = (ModifierRDController)Loader.getController();
			
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			adminStage.setScene(scene);
			adminStage.setTitle("Modifier un patient en base de données");
			adminStage.setResizable(false);
			adminStage.show();
		}	

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			
	}
		
		

}



