package Medecin;

import java.awt.HeadlessException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Secretariat.GererRDController;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Date;

public class MedecinController implements Initializable{
	
	@FXML private TextField nom;
	
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
		////         connection au BDD 
		this.dc = new dbConnection();	
		fct();
		
	}
//  LA FCT fct PERMETRE DE charger tout les patient qui sont  dans la BDD et leur afficher
	public void fct() {  String sql = "SELECT * FROM patient";
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
	
	// chargement et l'affichage dans la TableView 
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
	
	
	// ovrir la fenetre ajouter patient 
	@FXML 
	private void addPatient(ActionEvent event) throws IOException  {
		try {
				
		((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage adminStage = new Stage();
				FXMLLoader Loader = new FXMLLoader();
				Pane root =(Pane) Loader.load(getClass().getResource("/Medecin/AjouterPatient.fxml").openStream());
				AjouterPatientController ajouterPatientController = (AjouterPatientController)Loader.getController();
				
				Scene scene = new Scene(root);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				adminStage.setScene(scene);
				adminStage.setTitle("Ajouter un patient en base de données");
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
			Pane root = loader.load(getClass().getResource("/Medecin/Medecin.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    }
	
	

	
	
	
	public int subYear() {
	//cette méthode va nous permettre de savoir si on peut supprimer un patient en se calculant
	//la diofférence entre la date actuel et la date de la dernière visite du médecin en jours	
	String s0=null;

	if (this.patientTable.getSelectionModel().getSelectedIndex()==-1) return 2;
	else {	
		s0=this.date_derniere_visite_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());

	   
 String s1=null,s2=null,s3=null;
	     s1= s0.substring(0, 2); //extraire le jour
		 s2= s0.substring(3, 5); //extraire le mois
		 s3= s0.substring(6, 10); //extraire l'année
		 int n1= new Integer(s1).intValue(); //transformer le jour en un nombre
		 int n2= new Integer(s2).intValue(); //transformer le mois en un nombre
		 int n3= new Integer(s3).intValue(); //transformer l'année en un nombre
	
		 
		
		 
Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 String str0 =sdf.format(today);//récupérer la date actuel
		 String str1=null,str2=null,str3=null;
		 str1=str0.substring(0, 2);
		 str2=str0.substring (3, 5);
		 str3=str0.substring (6, 10);
		 int sn1= new Integer(str1).intValue();
		 int sn2= new Integer(str2).intValue();
		 int sn3= new Integer(str3).intValue();
		
       int date1=n1 +n2*30 + n3*365;
       int date2=sn1 +sn2*30 + sn3*365;
       if(date2-date1>(365*5)) return 0;
       else return 1;
	
	}

				
	}
	

	// permetre de supprimier une patient dans la BDD SI la date de dernier visite > 5 ans 
	@FXML 
	private void deletePatient(ActionEvent event) throws Exception {
	

   if(this.subYear()==0) {   //si la différence entre la date actuel et la date de la dernière viste du patient
	   //est plus de 5ans donc on peut supprimer
		String sqlInsert = "Delete From patient Where id = ?";
		
	    
		try {
			//récupérer l'id du patient sélectionné dans la table
			String s = this.id_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
			String sql1 = "SELECT code FROM Consultation WHERE id ='"+s+"'";
			String sql2 = "Delete From Medicament Where codeConsultation = ?";
			String sql3= "Delete From maladies_diagnostique Where code = ?";
			Connection conn = dbConnection.getConnection();
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			PreparedStatement stmt3 = conn.prepareStatement(sql3);
			
			ResultSet rs1 = conn.createStatement().executeQuery(sql1);
			while(rs1.next()) {
				stmt2.setString(1,rs1.getString(1));
				stmt2.execute();
				
				stmt3.setString(1,rs1.getString(1));
				stmt3.execute();
			}
			// suprimer tout les information concernant cette patient 
			String sql4 = "Delete From EtatPatient Where idPatient = ?";
			String sql5 = "Delete From bilan Where id = ?";
			String sql6 = "Delete From maladies Where id = ?";
			String sql7 = "Delete From rendez_vous Where id = ?";
			String sql8 = "Delete From Consultation Where id = ?";
			PreparedStatement stmt4 = conn.prepareStatement(sql4);
			PreparedStatement stmt5 = conn.prepareStatement(sql5);
			PreparedStatement stmt6 = conn.prepareStatement(sql6);
			PreparedStatement stmt7 = conn.prepareStatement(sql7);
			PreparedStatement stmt8 = conn.prepareStatement(sql8);
			
			
			stmt4.setString(1,s);
			stmt4.execute();
			
			stmt5.setString(1,s);
			stmt5.execute();
			
			stmt6.setString(1,s);
			stmt6.execute();
			
			stmt7.setString(1,s);
			stmt7.execute();
			
			stmt8.setString(1,s);
			stmt8.execute();
			
			
			
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1,s);
			stmt.execute();
			patientTable.getItems().remove(this.patientTable.getSelectionModel().getSelectedIndex());
			conn.close();
			JOptionPane.showMessageDialog(null, "suprresion efféctuée avec succés");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
		
     }
   
   else if(this.subYear()==2) JOptionPane.showMessageDialog(null,"Sélectionner un patient dans la table");
	  else JOptionPane.showMessageDialog(null, "la dernière consultation date moins de cinq ans");

   
   
	}
	
public static String id_e=null;
	@FXML 
	private void afficherEtatPatient(ActionEvent event) throws Exception {

		try {
			
			if (this.patientTable.getSelectionModel().getSelectedIndex()==-1) JOptionPane.showMessageDialog(null, "veuillez sélectionner un patient dans la table");
			else {
				//récupérer l'id du patient sélectionné dans la table
				id_e=this.id_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
				
			
				
		((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage adminStage = new Stage();
				FXMLLoader Loader = new FXMLLoader();
				Pane root =(Pane) Loader.load(getClass().getResource("/Medecin/etatPatient.fxml").openStream());
				EtatPatientController etatPatientController = (EtatPatientController)Loader.getController();
				
				Scene scene = new Scene(root);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				adminStage.setScene(scene);
				adminStage.setTitle("afficher l'état du patient");
				adminStage.setResizable(false);
				adminStage.show();
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	   
		public static String id_ap=null,nom_ap=null,prenom_ap=null,dateNaissance_ap=null,sex_ap=null,tel_ap=null,email_ap=null,adresse_ap=null,date_premiere_visite_ap=null,date_derniere_visite_ap=null;
	 
		// modifyPatient permetre de modifier les information d'une patient existe déjà dans la BDD 
	@FXML
	public void modifyPatient(ActionEvent event)throws IOException
	{	
			try {
				
				if (this.patientTable.getSelectionModel().getSelectedIndex()==-1) JOptionPane.showMessageDialog(null, "veuillez sélectionner un patient dans la table");
				
				//récupérer les infos du patient sélectionné dans la table
				else {
					
					id_ap=this.id_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
					nom_ap=this.nom_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
					prenom_ap=this.prenom_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
					dateNaissance_ap=this.dateNaissance_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
					sex_ap=this.sex_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
					tel_ap=this.tel_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
					email_ap=this.email_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
					adresse_ap=this.adresse_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
					date_premiere_visite_ap=this.date_premiere_visite_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
					date_derniere_visite_ap=this.date_derniere_visite_column.getCellData(this.patientTable.getSelectionModel().getSelectedIndex());
                     

				
					
			((Node)event.getSource()).getScene().getWindow().hide();
					
					Stage adminStage = new Stage();
					FXMLLoader Loader = new FXMLLoader();
					Pane root =(Pane) Loader.load(getClass().getResource("/Medecin/ModifierPatient.fxml").openStream());
					ModifierPatientController modifierPatientController = (ModifierPatientController)Loader.getController();
					
					Scene scene = new Scene(root);
					//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					adminStage.setScene(scene);
					adminStage.setTitle("Modifier un patient en base de données");
					adminStage.setResizable(false);
					adminStage.show();
				
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	
	
	
	
	
	
	// rechercher PERETRE de rechercher des patient a patrir d'un nom donner par l'utilisateur 
	public void rechercher(ActionEvent event) {
		 String sql = "SELECT * FROM patient WHERE nom = '"+this.nom.getText()+"'";
			try {
				// connextion au BDD
				Connection conn = dbConnection.getConnection();
				this.data = FXCollections.observableArrayList();
				
				ResultSet rs = conn.createStatement().executeQuery(sql);
				
				
				while(rs.next()) {
					this.data.add(    new Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) , rs.getString(7) , rs.getString(8) , rs.getString(9), rs.getString(10) ) );
				}
				
			} catch (SQLException e) {
				System.err.println("Error" + e);
			}
			
			// AFFICHER tout les patien existe 
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
			Pane root = loader.load(getClass().getResource("/Medecin/MenuPrincipal.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
