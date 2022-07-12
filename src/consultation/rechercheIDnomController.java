package consultation;

import java.awt.Frame;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.webkit.ThemeClientImpl;

import dbUtil.dbConnection;
import Medecin.*;
import consultation.*;
import javafx.application.Platform;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class rechercheIDnomController implements Initializable{
	  @FXML
	    private TextField nom1;
	  public static String idC = null ;

    @FXML
    private TableColumn<Patient, String> nom;

    @FXML
    private TableView<Patient> infoPatient;

    @FXML
    private TableColumn<Patient,String> id;

    @FXML
    private TableColumn<Patient,String> prenom;

    @FXML
    private TableColumn<Patient,String> dateNaissance;

    @FXML
    private TableColumn<Patient,String> sex;

    @FXML
    private TableColumn<Patient,String> tel;

    @FXML
    private TableColumn<Patient,String> adresse;

    @FXML
    private Button recherche;

    @FXML
    private Button copier;
    private ObservableList<Patient> info;
    @FXML 
	private void AffechierInfo(ActionEvent event) throws IOException{

		
		
		
String sql= "Select * from  patient where nom = ?";
		    
			
				try {
					if (id.getText().equals("")) {
						
						Alert alert2 = new Alert(AlertType.WARNING);
						alert2.setTitle("Ereur");
						alert2.setHeaderText("Id est vide !!");
						alert2.setContentText(null);
						alert2.showAndWait();
						
						}
				
				
					else {
					
				ResultSet rs = null ;
				int i=0;
				Connection conn = dbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				this.info= FXCollections.observableArrayList();
				stmt.setString(1, this.nom1.getText());
				rs = stmt.executeQuery();
				while(rs.next())
				 {
					i++ ;
				this.info.add(new Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) , rs.getString(7) , rs.getString(8) , rs.getString(9), rs.getString(10)) );
				}
				this.id.setCellValueFactory(new PropertyValueFactory<Patient,String>("id"));
				this.nom.setCellValueFactory(new PropertyValueFactory<Patient,String>("nom"));
				this.prenom.setCellValueFactory(new PropertyValueFactory<Patient,String>("prenom"));
				this.dateNaissance.setCellValueFactory(new PropertyValueFactory<Patient,String>("dateNaissance"));
				this.sex.setCellValueFactory(new PropertyValueFactory<Patient,String>("sex"));
				this.tel.setCellValueFactory(new PropertyValueFactory<Patient,String>("tel"));
				this.adresse.setCellValueFactory(new PropertyValueFactory<Patient,String>("adresse"));
				
				this.infoPatient.setItems(this.info);
				    stmt.execute();
					conn.close();
				
				
				
				if (i==0)
				{
					
					Alert alert0 = new Alert(AlertType.WARNING);
					alert0.setTitle("Ereur");
					alert0.setHeaderText("vérifier votre Nom");
					alert0.setContentText( "n'existe pas des patients avec cette Nom !!");	
					alert0.showAndWait();	
					
				}
				
				
				
				}}
				catch (Exception e) {
					Alert alert0 = new Alert(AlertType.WARNING);
					alert0.setTitle("Ereur");
					alert0.setHeaderText(null);
					alert0.setContentText( e.toString());
					alert0.showAndWait();
				}
	}
	
	
    @FXML 
	private void CopierID(ActionEvent event) throws IOException{
	
		if (this.infoPatient.getSelectionModel().getSelectedIndex()==-1) {
			Alert alert0 = new Alert(AlertType.WARNING);
			alert0.setTitle("Ereur");
			alert0.setHeaderText(null);
			alert0.setContentText( "Sélectionner votre ligne ");
			alert0.showAndWait();
		}
			
		else {
		idC=this.id.getCellData(this.infoPatient.getSelectionModel().getSelectedIndex());
            int z =0;
		if (mainControler.o==0)	
			{
			Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/RecherchConsulation.fxml"));
		    Scene scene = new Scene(root2);
			
			//Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		    z=1 ;
		    
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setTitle( "Recharche");
			stage.setScene(scene);
			stage.show();
			
			
			
			}
		
		
		
		if (mainControler.o==-1)	
		{
		Parent root2 =  FXMLLoader.load(getClass().getResource("/consultation/modifierConsulation.fxml"));
	    Scene scene = new Scene(root2);
		
		//Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
	    
	    z=1 ;
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle( "Modifier");
		stage.setScene(scene);
		stage.show();
		
		
		
		}
		// pour quitter la fenetre 
	if (z==0) {	Node source =(Node)event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow(); 
            stage.close();
	}
		}
		
	
	
	
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		idC=null;
	}		
				
	}
