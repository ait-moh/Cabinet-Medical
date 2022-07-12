package consultation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class recharcheConsultation implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		supprimer.setDisable(true);
	if (mainControler.o ==0)	
		this.ID.setText(rechercheIDnomController.idC);
	mainControler.o=0;	
	}
    public static int  o =0 ;
    @FXML
    private TextField ID;

    @FXML
    private Button okRecharche;

    @FXML
    private TableView<Consultation> ConsultationTable;

    @FXML
    private TableColumn<Consultation,String> code_column;

    @FXML
    private TableColumn<Consultation,String> id_column;

    @FXML
    private TableColumn<Consultation,String> date_column;

    @FXML
    private TableColumn<Consultation,String> montant_column;

    @FXML
    private TableColumn<Consultation,String> Commantaire_column;

    @FXML
    private TableView<Bilan> tableanalyse;

    @FXML
    private TableColumn<Bilan,String> analyse_column;
    @FXML
    private TableColumn<maladies_diagnostique, String> CODE_column;

    @FXML
    private TableColumn<Bilan,String> resultat_column;

    @FXML
    private TableView<maladies> tablemaladies;
    @FXML
    private TableView<maladies_diagnostique> tablemaladiesDiagnostique;

    @FXML
    private TableColumn<maladies_diagnostique, String> maladiesDiagno_column;
    @FXML
    private TableView<Etat> tableEtat;

    @FXML
    private TableColumn<Etat,String> taille_column;

    @FXML
    private TableColumn<Etat,String> poinds_column;

    @FXML
    private TableColumn<Etat,String> tension_column;

    @FXML
    private TableColumn<Etat,String> teux_column;
    @FXML
    private Button affechier;

    @FXML
    private Button modifier;
    @FXML
    private TableColumn<Etat,String> group_column;


    @FXML
    private TableColumn<maladies, String> maladies_column;
    private ObservableList<Consultation> data0;
    private ObservableList<Bilan> data1;
    private ObservableList<maladies> data2;
    private ObservableList<maladies_diagnostique> dataD;
    private ObservableList<Etat> dataE;
    @FXML
    private Button supprimer;
     // ChargerConsultatient permetre de charger les information relatif a le patient et les consultation de patient 
    @FXML 
	private void ChargerConsultatient(ActionEvent event)throws SQLException, SQLException
    {
	   try {
		   if (ID.getText().equals("")) {
				
				Alert alert2 = new Alert(AlertType.WARNING);
				alert2.setTitle("Ereur");
				alert2.setHeaderText("Id est vide !!");
				alert2.setContentText(null);
				alert2.showAndWait();
				
		   }else {
	    	PreparedStatement ps= null ;
			ResultSet rs = null ;
			Connection conn = dbConnection.getConnection();
			String sql = "SELECT * FROM Consultation where id= ?  ";
		  int i=0 ;
	          try {
					ps = conn.prepareStatement(sql);
					ps.setString(1,ID.getText());
					
					rs = ps.executeQuery();
					
					this.data0 = FXCollections.observableArrayList();
					this.dataD = FXCollections.observableArrayList();
					
					 while(rs.next()) { 
						this.data0.add(  //  creation du l'objet Consultation
					new Consultation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) ) );
						i=1;
						String sql6 = "SELECT * FROM maladies_diagnostique where code= ?  ";
								try {
									ResultSet rs2 = null ;
									ps = conn.prepareStatement(sql6);
									ps.setString(1,rs.getString(1));
									
									rs2 = ps.executeQuery();
									
							
							 while(rs2.next()) {
								 this.dataD.add( 
										 //creation du l'objet maladies_diagnostique
							new maladies_diagnostique(rs2.getString(2),rs2.getString(1)));
								
							}
							
						} catch (SQLException e) {
							Alert alert0 = new Alert(AlertType.WARNING);
							alert0.setTitle("Ereur");
							alert0.setHeaderText(e.toString());
							
							alert0.showAndWait();
						}
						
						// l'affichage des maladies diagnostique 
						this.maladiesDiagno_column.setCellValueFactory(new PropertyValueFactory<maladies_diagnostique,String>("maladies_diagnostique"));
						this.CODE_column.setCellValueFactory(new PropertyValueFactory<maladies_diagnostique,String>("Code"));
						this.tablemaladiesDiagnostique.setItems(this.dataD);
						
						ps.close();
					}
					
		     }catch (SQLException e) {
				Alert alert0 = new Alert(AlertType.WARNING);
				alert0.setTitle("Ereur");
				alert0.setHeaderText(e.toString());
				 
				alert0.showAndWait();
			}
			
			// l'affichage des consultations
			this.code_column.setCellValueFactory(new PropertyValueFactory<Consultation,String>("code"));
			
			this.date_column.setCellValueFactory(new PropertyValueFactory<Consultation,String>("dateConsultation"));
			
			this.montant_column.setCellValueFactory(new PropertyValueFactory<Consultation,String>("montant"));
			
			this.Commantaire_column.setCellValueFactory(new PropertyValueFactory<Consultation,String>("Commantaire"));
			
			
			
			
			this.ConsultationTable.setItems(this.data0);
			ps.close();
			
			
			
			String sql2 = "SELECT * FROM bilan where id= ?  ";
			try {
				ps = conn.prepareStatement(sql2);
				ps.setString(1,ID.getText());
				
				rs = ps.executeQuery();
				
				this.data1 = FXCollections.observableArrayList();
				 while(rs.next()) {
					 i=1;
					this.data1.add(   
				new Bilan(rs.getString(2), rs.getString(3)) );
				}
				
			} catch (SQLException e) {
				Alert alert0 = new Alert(AlertType.WARNING);
				alert0.setTitle("Ereur");
				alert0.setHeaderText(e.toString());
				
				alert0.showAndWait();
			}
			
			
			this.analyse_column.setCellValueFactory(new PropertyValueFactory<Bilan,String>("analyse"));
			this.resultat_column.setCellValueFactory(new PropertyValueFactory<Bilan,String>("resultat"));
			
		
			this.tableanalyse.setItems(this.data1);
			ps.close();
			
			
			
			String sql3 = "SELECT * FROM maladies where id= ?  ";
			try {
				ps = conn.prepareStatement(sql3);
				ps.setString(1,ID.getText());
				
				rs = ps.executeQuery();
				
				this.data2 = FXCollections.observableArrayList();
				 while(rs.next()) {
					 i=1 ;
					this.data2.add(   
				new maladies(rs.getString(2)) );
				}
				
			} catch (SQLException e) {
				Alert alert0 = new Alert(AlertType.WARNING);
				alert0.setTitle("Ereur");
				alert0.setHeaderText(e.toString());
				
				alert0.showAndWait();
			}
			
			
			this.maladies_column.setCellValueFactory(new PropertyValueFactory<maladies,String>("maladies_chroniques"));
			this.tablemaladies.setItems(this.data2);
			
			ps.close();
			
			
			String sql4 = "SELECT * FROM EtatPatient where idPatient = ?  ";
			try {
				ps = conn.prepareStatement(sql4);
				ps.setString(1,ID.getText());
				
				rs = ps.executeQuery();
				
				this.dataE = FXCollections.observableArrayList();
				 while(rs.next()) {
					 i=1;
					this.dataE.add(   // creation de l'objet Etat 
				new Etat(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)) );
				}
				
			} catch (SQLException e) {
				Alert alert0 = new Alert(AlertType.WARNING);
				alert0.setTitle("Ereur");
				alert0.setHeaderText(e.toString());
				
				alert0.showAndWait();
			}
		            
			
			this.taille_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("taille"));
			this.group_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("groupeSanguin"));
			this.poinds_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("poids"));
			this.tension_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("tension"));
			this.teux_column.setCellValueFactory(new PropertyValueFactory<Etat,String>("teuxDiabete"));
		
			this.tableEtat.setItems(this.dataE);

	         ps.close();

	if (i==0) {
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText(" il n'a pas des Consultation ");
		
		alert0.showAndWait();
	}else supprimer.setDisable(false);
	} }catch (Exception e) {
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText(e.toString());
		System.out.println("kljrfndoks,pk,op");
		alert0.showAndWait();
	}
		
    }
	
    /* cette methode permetre de supprimer dans la BDD Les information que l'utisateur doit suprimer d'aprés les tableView
     * c'est-a-dire l'utilisateur affichier les information dans la les tableView aprés il sélectionner des lignes pour le supprimier   */
    public void deleteConsultation (ActionEvent event) throws IOException, SQLException {
		 PreparedStatement ps= null ;
			Connection conn = dbConnection.getConnection();
			int i=0;
		  
		try {
			
			/* verifier est-ce-que dans la table consultation  il y'a consultation que l'utulisateur doit suprimier 
			si this.ConsultationTable.getSelectionModel().getSelectedIndex()=-1 donc il y'a pas des consultation sélectionner */  
			if(this.ConsultationTable.getSelectionModel().getSelectedIndex()!=-1)
			{
				 // supprimer les maladies_diagnostique relier avec cette consultation 
				String sql1 = "DELETE FROM maladies_diagnostique where code= ? ";
				  i=1;
				 ps =conn.prepareStatement(sql1);
				 ps.setString(1,this.code_column.getCellData(this.ConsultationTable.getSelectionModel().getSelectedIndex()));
				
				ps.execute();
			    ps.close();
			 // supprimer les Ordonnances  relier avec cette consultation 
			    String sql10 = "DELETE FROM Medicament  where codeConsultation= ? ";
				  i=1;
				 ps =conn.prepareStatement(sql10);
				 ps.setString(1,this.code_column.getCellData(this.ConsultationTable.getSelectionModel().getSelectedIndex()));
				
				ps.execute();
			    ps.close();
			    // supprimer la consultation 
	    String sql = "DELETE FROM Consultation where code= ?  ";
	    ps =conn.prepareStatement(sql);
		ps.setString(1,this.code_column.getCellData(this.ConsultationTable.getSelectionModel().getSelectedIndex()));
		ps.execute();
		i=1;
		ConsultationTable.getItems().remove(this.ConsultationTable.getSelectionModel().getSelectedIndex());	
		ps.close();
		    }
			if(this.tableEtat.getSelectionModel().getSelectedIndex()!=-1)
			{
		String sql1 = "DELETE FROM EtatPatient where idPatient = ?  ";
		  i=1;
		 ps =conn.prepareStatement(sql1);
		ps.setString(1,ID.getText());
		ps.execute();
	     tableEtat.getItems().remove(this.tableEtat.getSelectionModel().getSelectedIndex());	
		ps.close();
			}
		if(this.tableanalyse.getSelectionModel().getSelectedIndex()!=-1)
		{
				String sql1 = "DELETE FROM bilan where id= ? and analyse_demander = ?  ";
				  i=1;
				 ps =conn.prepareStatement(sql1);
				 ps.setString(1,ID.getText());
				ps.setString(2,this.analyse_column.getCellData(this.tableanalyse.getSelectionModel().getSelectedIndex()));
				
				ps.execute();
			     tableanalyse.getItems().remove(this.tableanalyse.getSelectionModel().getSelectedIndex());	
				ps.close();
		}
		if(this.tablemaladies.getSelectionModel().getSelectedIndex()!=-1) 
		{
			String sql1 = "DELETE FROM maladies where id= ? and maladies = ?  ";
			  i=1;
			 ps =conn.prepareStatement(sql1);
			 ps.setString(1,ID.getText());
			ps.setString(2,this.maladies_column.getCellData(this.tablemaladies.getSelectionModel().getSelectedIndex()));
			
			ps.execute();
		     tablemaladies.getItems().remove(this.tablemaladies.getSelectionModel().getSelectedIndex());	
			ps.close();
		}
		
		if(this.tablemaladiesDiagnostique.getSelectionModel().getSelectedIndex()!=-1) 
		{
			String sql1 = "DELETE FROM maladies_diagnostique where code = ? and maladies = ?  ";
			  i=1;
			 ps =conn.prepareStatement(sql1);
			 ps.setString(1,ID.getText());
			ps.setString(2,this.maladiesDiagno_column.getCellData(this.tablemaladiesDiagnostique.getSelectionModel().getSelectedIndex()));
			
			ps.execute();
		     tablemaladiesDiagnostique.getItems().remove(this.tablemaladiesDiagnostique.getSelectionModel().getSelectedIndex());	
			ps.close();
		}
		
		// si la variable i =0 donc tout les index des tableux = -1 donc l'utulisateur  n'a pas sélectionner des lignes
		// donc on affiche une message d'erreur 
		if (i==0) { 	
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText("Sélectionner votre ligne ");
		
		alert0.showAndWait();}
		else { 
			// Message de réussite de l'action 
			Alert alert0 = new Alert(AlertType.INFORMATION);
			alert0.setTitle("Suppression");
			alert0.setHeaderText("la Suppression s'est terminée avec succés !! ");
			supprimer.setDisable(true);
			alert0.showAndWait();
		} 
		
		} catch (Exception e) {
			Alert alert0 = new Alert(AlertType.WARNING);
			alert0.setTitle("Ereur");
			alert0.setHeaderText(e.toString());
			
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
    		System.out.println(e);
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
    			System.out.println(e);
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
