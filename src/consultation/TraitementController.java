package consultation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;





import javafx.print.PrinterJob;




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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TraitementController implements Initializable {
	  @FXML
	    private TableView<Medicament> tableMedicament;
	  @FXML private TableColumn<Medicament,String> medicament_column;
		@FXML private TableColumn<Medicament,String> dosage_column;
		@FXML private TableColumn<Medicament,String> Durée_column;

	    @FXML
	    private TextField medicament;



	    @FXML
	    private TextField dosage;
        
	    @FXML
	    private TextField Durée;
	    @FXML
	    private TextField code;

	    @FXML
	    private Button ajouterMedicament;
	    @FXML
	    private Button imprimier;

	    @FXML
	    private TextField ID;
	    @FXML
	    private Button modifier;

	    @FXML
	    private Button Suprimier;

	    @FXML
	    private Button rechercher;

	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	ID.setText(mainControler.Idcopier);
	        code.setText(mainControler.codeCopier);
	    	Suprimier.setDisable(true);
	    	modifier.setDisable(true);
	    	imprimier.setDisable(true);
	    	
	    	
	    	initiali();
	    	//String s = mainControler.coller();
	    	
	    	
	    	
		}
   public void initiali() {
	    	
	   this.medica2 = FXCollections.observableArrayList();
	    	
	    	
		}
	
	
	    private ObservableList<Medicament> medica;
	    private ObservableList<Medicament> medica2;
	    
	   
	
	@FXML 
	private void AjouterMedicament(ActionEvent event) throws IOException, SQLException{

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
					
			if (y==0 || code.getText().equals(null)|| code.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ereur");
			alert.setHeaderText("Verifier votre ID !!");
			alert.setContentText("le patient n'existe pas dans la base des données ");
			alert.showAndWait();
			 }
			else {
				if (medicament.getText().equals("")) {
					
					Alert alert2 = new Alert(AlertType.WARNING);
					alert2.setTitle("Ereur");
					alert2.setHeaderText("Medicament vide !!");
					alert2.setContentText(null);
					alert2.showAndWait();
					
					}
			
				else {
		code.setDisable(false);
		
String sql= "INSERT INTO  Medicament(codeConsultation,medicament,doze,Durée) VALUES (?,?,?,?);";
		    
			
				
				Connection conn = dbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, this.code.getText());
				
				stmt.setString(2,this.medicament.getText());
				stmt.setString(4, this.Durée.getText());
				
				stmt.setString(3, this.dosage.getText());
				this.medica = FXCollections.observableArrayList(medica2);
           
			this.medica.add(new Medicament(this.medicament.getText(),this.dosage.getText(),this.Durée.getText()));
			medica2= medica ;	
			this.medicament_column.setCellValueFactory(new PropertyValueFactory<Medicament,String>("medicament"));
			this.dosage_column.setCellValueFactory(new PropertyValueFactory<Medicament,String>("dosage"));
			this.Durée_column.setCellValueFactory(new PropertyValueFactory<Medicament,String>("nombre"));
			this.tableMedicament.setItems(this.medica2);
			    Suprimier.setDisable(false);
				imprimier.setDisable(false);
				stmt.execute();
				conn.close();
				modifier.setDisable(false);
				ajouterMedicament.setText("Ajouter");
				
			
				}} }catch(SQLException e) {
					Alert alert0 = new Alert(AlertType.WARNING);
					alert0.setTitle("Ereur");
					alert0.setHeaderText(e.toString());
					
					alert0.showAndWait();
			
			} 
		   
            
			this.medicament.setText("");this.Durée.setText(null);this.dosage.setText(null); 
				
			}
			


	

@FXML 
private void printOrdannace(ActionEvent event) throws IOException, SQLException{
	
	try {
		 System.out.println(" can I print?");
         PrinterJob printerJob = PrinterJob.createPrinterJob();
         if (printerJob.showPrintDialog(null) && printerJob.printPage(Durée))
         {
             printerJob.endJob();
           
         }
     
		
	} catch (Exception e) {
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText(e.toString());
		
		alert0.showAndWait();
	}
	
}

public void SuprimierMedicament(ActionEvent event) throws IOException, SQLException
{
	 PreparedStatement ps= null ;
		Connection conn = dbConnection.getConnection();
		 
		  
		try {
			;
			
			if(this.tableMedicament.getSelectionModel().getSelectedIndex()!=-1) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confermation ");
				alert.setHeaderText("Voulez-vous vraiment déplacer ce medicament vers la corbeille ?");
				alert.setContentText(null);
				 
				Optional<ButtonType> option= alert.showAndWait();
				if (option.get()==ButtonType.OK) {
	    String sql = "DELETE FROM medicament where codeConsultation= ? and medicament = ?  ";
	    ps =conn.prepareStatement(sql);
		ps.setString(1,this.code.getText());
		ps.setString(2,this.medicament_column.getCellData(this.tableMedicament.getSelectionModel().getSelectedIndex()));
		ps.execute();
		
		tableMedicament.getItems().remove(this.tableMedicament.getSelectionModel().getSelectedIndex());	
		ps.close();}}
			else {
		    Alert alert0 = new Alert(AlertType.WARNING);
			alert0.setTitle("Ereur");
			alert0.setHeaderText(" Sélectionner le medicament ");
			
			alert0.showAndWait();
				
			}
	
		}
		catch (Exception e) {
			Alert alert0 = new Alert(AlertType.WARNING);
			alert0.setTitle("Ereur");
			alert0.setHeaderText(e.toString());
			
			alert0.showAndWait();
			
		}
}


private ObservableList<Medicament> data;
public void AffechierMedicament(ActionEvent event) throws IOException, SQLException
  {
	if (code.getText().equals("")) {
		
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
	int i=0;
	String sql4 = "SELECT * FROM Medicament where codeConsultation =? ";
	try {
		
							ps = conn.prepareStatement(sql4);
							ps.setString(1,code.getText());
							
							rs = ps.executeQuery();
							
							this.data = FXCollections.observableArrayList();
							 while(rs.next())
												 {
													  i=1;
													this.data.add( new Medicament(rs.getString(2), rs.getString(3), rs.getString(4) ));
												}
							
					      if (i==1) { modifier.setDisable(false); Suprimier.setDisable(false); imprimier.setDisable(false);}
								      else {
								    	  Alert alert0 = new Alert(AlertType.WARNING);
											alert0.setTitle("Ereur");
											alert0.setHeaderText("N'a pas des Medicaments ");
											alert0.setContentText("Merci de vérifier votre code !!");
											alert0.showAndWait();
								    	  
								      }
					    	
							 
		 
				} catch (SQLException e) {
					Alert alert0 = new Alert(AlertType.WARNING);
					alert0.setTitle("Ereur");
					alert0.setHeaderText(e.toString());
					
					alert0.showAndWait();
				}
			            
	
		this.medicament_column.setCellValueFactory(new PropertyValueFactory<Medicament,String>("medicament"));
		this.dosage_column.setCellValueFactory(new PropertyValueFactory<Medicament,String>("dosage"));
		this.Durée_column.setCellValueFactory(new PropertyValueFactory<Medicament,String>("nombre"));
	
		this.tableMedicament.setItems(this.data);
	     medica2 = data ;
	     ps.close();


	}
	
  }

public void ModifierMedicament(ActionEvent event) throws IOException, SQLException
 {
		try {
				PreparedStatement ps= null ;
				ResultSet rs = null ;
				Connection conn = dbConnection.getConnection();
				if (this.tableMedicament.getSelectionModel().getSelectedIndex()==-1) {
						Alert alert0 = new Alert(AlertType.WARNING);
						alert0.setTitle("Ereur");
						alert0.setHeaderText("Sélectionner le medicament dans le table ");
						
						alert0.showAndWait(); }
						
				
				else {
							code.setDisable(true);
							medicament.setText(this.medicament_column.getCellData(this.tableMedicament.getSelectionModel().getSelectedIndex()));
							dosage.setText(this.dosage_column.getCellData(this.tableMedicament.getSelectionModel().getSelectedIndex()));
							Durée.setText(this.Durée_column.getCellData(this.tableMedicament.getSelectionModel().getSelectedIndex()));
							String sql = "DELETE FROM medicament where codeConsultation= ? and medicament = ?  ";
						    ps =conn.prepareStatement(sql);
							ps.setString(1,this.code.getText());
							ps.setString(2,this.medicament_column.getCellData(this.tableMedicament.getSelectionModel().getSelectedIndex()));
							ps.execute();
							
							tableMedicament.getItems().remove(this.tableMedicament.getSelectionModel().getSelectedIndex());	
							medica2 = data ;
							ps.close();
							
							
						    ajouterMedicament.setText("Sauvgarder");
						    modifier.setDisable(true);
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
		Alert alert0 = new Alert(AlertType.WARNING);
		alert0.setTitle("Ereur");
		alert0.setHeaderText(e.toString());
		
		alert0.showAndWait();
	}

}

	
	
}




	
		
	
