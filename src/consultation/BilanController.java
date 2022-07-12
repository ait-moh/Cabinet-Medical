package consultation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class BilanController implements Initializable {
	 
  
	
	  @FXML
	    private TextField code;

	    @FXML
	    private TextField ID;

	    @FXML
	    private TextField analyse;

	    @FXML
	    private TableView<Bilan> tableanalyse;

	    @FXML
	    private TableColumn<Bilan, String> analyse_column;

	    @FXML
	    private TableColumn<Bilan, String> resultat_column;

	    @FXML
	    private TextArea resultat;
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	ID.setText(mainControler.Idcopier);
			initiali();
		    SuprimierBilan.setDisable(true);
			modifier.setDisable(true);
		}
	    @FXML
	    private Button SuprimierBilan;

	    @FXML
	    private Button affechier;

	    @FXML
	    private Button ajouterbilan;
	    @FXML
	    private Button modifier;
		  public void initiali() {
		    	
			   this.medica2 = FXCollections.observableArrayList();
			    	
			    	
				}
		   private ObservableList<Bilan> medica;
		    private ObservableList<Bilan> medica2;

		@FXML 
		private void Ajouterbilan(ActionEvent event) throws IOException{

			try {
				String sql1 = "SELECT * FROM Patient where id= ?  ";
				  int y=0 ;
				  ResultSet rs = null ;
					Connection conn0 = dbConnection.getConnection();
					PreparedStatement stmt0 = conn0.prepareStatement(sql1);
						stmt0 = conn0.prepareStatement(sql1);
						stmt0.setString(1,ID.getText());
						
						rs = stmt0.executeQuery();
						
						
						 while(rs.next()) { 
							y=1;}
					// vérification de	saisir
				if (y==0 || ID.getText().equals(null)|| ID.getText().equals("")) {
					//Message d'erreur 
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Ereur");
				alert.setHeaderText("Verifier votre ID !!");
				alert.setContentText("le patient n'existe pas dans la base des données ");
				alert.showAndWait();
				 }else { if (analyse.getText().equals("")) {
						
						Alert alert2 = new Alert(AlertType.WARNING);
						alert2.setTitle("Ereur");
						alert2.setHeaderText("les information vide  !!");
						alert2.setContentText(null);
						alert2.showAndWait();
						
						}
				
				
					else {
			// ***************** insertion de bilan au BDD ***************************
			
	String sql= "INSERT INTO  bilan(id ,analyse_demander ,resultat_analyse) VALUES (?,?,?);";
			    
				
					
					analyse.setDisable(false);
					//ID.setDisable(false);
					Connection conn = dbConnection.getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql);
					
					stmt.setString(1, this.ID.getText());
					
					stmt.setString(2,this.analyse.getText());
					stmt.setString(3,this.resultat.getText());
					
					// Chargrment des information dans la liste pour l'affichage
					this.medica = FXCollections.observableArrayList(medica2);
	           
				this.medica.add(new Bilan(this.analyse.getText(),this.resultat.getText()));
				
				medica2= medica ;	
				this.analyse_column.setCellValueFactory(new PropertyValueFactory<Bilan,String>("analyse"));
				this.resultat_column.setCellValueFactory(new PropertyValueFactory<Bilan,String>("resultat"));
				
				this.tableanalyse.setItems(this.medica2);
				ajouterbilan.setText("Ajouter ");
				modifier.setDisable(false);
				SuprimierBilan.setDisable(false);
				ID.setDisable(true);
				stmt.execute();
					conn.close();
					
					
				
					}} }catch(SQLException e) {
					
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Ereur");
						alert.setHeaderText(null);
						alert.setContentText(e.toString());
						alert.showAndWait();
				} 
			   
	            
				this.analyse.setText(null);
				this.resultat.setText(null);	
				}
				
		@FXML 
		private void SuprimierBilan(ActionEvent event) throws IOException{
			/// ******************* Message de Confirmation *******************************
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete Bilan");
			alert.setHeaderText("Voulez-vous vraiment déplacer ce bilan vers la corbeille ?");
			alert.setContentText(null);
			 
			Optional<ButtonType> option= alert.showAndWait();
			if (option.get()==ButtonType.OK) {
					 PreparedStatement ps= null ;
						Connection conn = dbConnection.getConnection();
						
				// suprission de bilan dans la BDD
						  String sql = "DELETE FROM bilan where id= ? and analyse_demander = ? ";
						  
						try { ps =conn.prepareStatement(sql);
						ps.setString(1,ID.getText());
						ps.setString(2,this.analyse_column.getCellData(this.tableanalyse.getSelectionModel().getSelectedIndex()));
						ps.execute();
						tableanalyse.getItems().remove(this.tableanalyse.getSelectionModel().getSelectedIndex());
		                
						
						}
		                catch (Exception e) {
							
							Alert alert2 = new Alert(AlertType.WARNING);
							alert2.setTitle("Ereur");
							alert2.setHeaderText(null);
							alert2.setContentText("Selectionner votre ligne ");
							alert2.showAndWait();
									}
				}
			
				
			
			
			
		}
		
		@FXML 
		private void Affechierbilan(ActionEvent event) throws IOException{

			// Vérification de saisir du ID
			 if (ID.getText().equals("")) {
					
					Alert alert2 = new Alert(AlertType.WARNING);
					alert2.setTitle("Ereur");
					alert2.setHeaderText("Id patient est vide  !!");
					alert2.setContentText(null);
					alert2.showAndWait();
					
					}
			
			
				else {
			// Charger les bilan a partir du BDD
	String sql= "Select * from  bilan where id = ?";
			    
				
					try {
						
						
					ResultSet rs = null ;
					int i=0;
					Connection conn = dbConnection.getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql);
					
					stmt.setString(1, this.ID.getText());
					rs = stmt.executeQuery();
					
					
					this.medica2 = FXCollections.observableArrayList();
					 while(rs.next()) { 
							i++;
				this.medica2.add(new Bilan(rs.getString(2),rs.getString(3)));
					 }
					
				this.analyse_column.setCellValueFactory(new PropertyValueFactory<Bilan,String>("analyse"));
				this.resultat_column.setCellValueFactory(new PropertyValueFactory<Bilan,String>("resultat"));
				
				this.tableanalyse.setItems(this.medica2);
				
			
				stmt.execute();
					conn.close();
				if (i==0) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Ereur");
					alert.setHeaderText(null);
					alert.setContentText("vous n'avez pas des bilans !!");
					alert.showAndWait();}
				else {   
					ID.setDisable(true);
					SuprimierBilan.setDisable(false);
				modifier.setDisable(false);}
				
					 }catch(SQLException e) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Ereur");
						alert.setHeaderText(null);
						alert.setContentText(e.toString());
						alert.showAndWait();
					
				}
		}
				}
				    
	    
		public void modifier(ActionEvent event) throws IOException, SQLException {
			try { 
				PreparedStatement ps= null ;
				Connection conn = dbConnection.getConnection();
				// verifier le choix de l'utilisateur a partir tableView si index = -1 donc l'utilisateur pas encore choisit sont bilan que va modifier 
				if (this.tableanalyse.getSelectionModel().getSelectedIndex()==-1) {Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Ereur");
				alert.setHeaderText(null);
				alert.setContentText("Sélectionner une ligne!!");
				alert.showAndWait();}
				else {
					
					
			   // copier les information a partir de tableView dans les textFilds 
				analyse.setText(this.analyse_column.getCellData(this.tableanalyse.getSelectionModel().getSelectedIndex()));
				resultat.setText(this.resultat_column.getCellData(this.tableanalyse.getSelectionModel().getSelectedIndex()));
				analyse.setDisable(true);
				ID.setDisable(true);
					
				// suprimer le bilan et modifier le button "ajouter "vers "mise a jour "
					  String sql = "DELETE FROM bilan where id= ? and analyse_demander = ? ";
					  
					 ps =conn.prepareStatement(sql);
					ps.setString(1,ID.getText());
					ps.setString(2,this.analyse_column.getCellData(this.tableanalyse.getSelectionModel().getSelectedIndex()));
					ps.execute();
					tableanalyse.getItems().remove(this.tableanalyse.getSelectionModel().getSelectedIndex());
					modifier.setDisable(true);
					ajouterbilan.setText(" mise a jour ");
				    						
				}
				
			} catch (Exception e) {

					Alert alert2 = new Alert(AlertType.WARNING);
					alert2.setTitle("Ereur");
					alert2.setHeaderText(null);
					alert2.setContentText(e.toString());
					alert2.showAndWait();
						
				
			}
			
		
		
		}
}
