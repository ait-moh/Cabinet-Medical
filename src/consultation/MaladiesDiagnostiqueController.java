package consultation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;



import dbUtil.dbConnection;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MaladiesDiagnostiqueController implements Initializable{
	private ObservableList<maladies_diagnostique> data;
	public int i=0;
    @FXML
    private TextField maladiesDia;

    @FXML
    private TableView<maladies_diagnostique> tablemaladies;

    @FXML
    private TableColumn<maladies_diagnostique,String> maladiesD_column;

    @FXML
    private Button Suprimier;

    @FXML
    private TextField code;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		code.setText(mainControler.codeCopier);
		initiali();
		
	}
	  public void initiali() {
	    	
		   this.medica2 = FXCollections.observableArrayList();
		    	
		    	
			}
	   private ObservableList<maladies_diagnostique> medica;
	    private ObservableList<maladies_diagnostique> medica2;

	@FXML 
	private void AjouterMaladies(ActionEvent event) throws IOException, SQLException{

		
		try {
			String sql1 = "SELECT * FROM Consultation where Code = ?  ";
			  int y=0 ;
			  ResultSet rs = null ;
				Connection conn0 = dbConnection.getConnection();
				PreparedStatement stmt0 = conn0.prepareStatement(sql1);
					stmt0 = conn0.prepareStatement(sql1);
					stmt0.setString(1,code.getText());
					
					rs = stmt0.executeQuery();
					
					
					 while(rs.next()) { 
						y=1;}
					
			if (y==0 || code.getText().equals(null)|| code.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ereur");
			alert.setHeaderText("Verifier votre Code !!");
			alert.setContentText("la Consultation n'existe pas dans la base des données ");
			alert.showAndWait();
			 }
			else {
				if (maladiesDia.getText().equals("")) {
					
					Alert alert2 = new Alert(AlertType.WARNING);
					alert2.setTitle("Ereur");
					alert2.setHeaderText("Verifier votre informmation !!");
					alert2.setContentText(null);
					alert2.showAndWait();
					
					}
			
				else {
		
String sql= "INSERT INTO  maladies_diagnostique(Code,maladies) VALUES (?,?);";
		    
			
				
					Connection conn = dbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, this.code.getText());
				
				stmt.setString(2,this.maladiesDia.getText());
				if(i==1) {
					medica2.clear();
					medica2.addAll(data);
					i=0;
					data.clear();
					
				}
				
				    
					this.medica = FXCollections.observableArrayList(medica2);
					
			this.medica.add( //  creation du l'objet maladies_diagnostique
					new maladies_diagnostique(this.maladiesDia.getText(),this.code.getText()));
			
			medica2=medica ;	
			this.maladiesD_column.setCellValueFactory(new PropertyValueFactory<maladies_diagnostique,String>("maladies_diagnostique"));
			
			this.tablemaladies.setItems(this.medica2);
			
			//System.out.println(this.maladies_column.getCellData(this.tablemaladies.getSelectionModel().getSelectedIndex()));
			//System.out.println(this.tablemaladies.getSelectionModel().getSelectedItems());
			stmt.execute();
				conn.close();
				
			this.code.setDisable(true);
				
				} }}catch(SQLException e) {
					Alert alert0 = new Alert(AlertType.WARNING);
					alert0.setTitle("Ereur");
					alert0.setHeaderText(null);
					alert0.setContentText(e.toString());
					alert0.showAndWait();
			
		
			} 
		   
            
			this.maladiesDia.setText("");
				
			}
		
	
	
	
	@FXML 
	private void affehierMaladies(ActionEvent event) throws IOException, SQLException{
		tablemaladies.setItems(null);
		PreparedStatement ps= null ;
		ResultSet rs = null ;
		Connection conn = dbConnection.getConnection();
		String sql3 = "SELECT * FROM maladies_diagnostique where Code= ?  ";
		try {
			if (code.getText().equals("")) {
				
				Alert alert2 = new Alert(AlertType.WARNING);
				alert2.setTitle("Ereur");
				alert2.setHeaderText("Id est vide !!");
				alert2.setContentText(null);
				alert2.showAndWait();
				
				}
		
		
			else {
			ps = conn.prepareStatement(sql3);
			ps.setString(1,code.getText());
			
			rs = ps.executeQuery();
			
			this.data = FXCollections.observableArrayList();
			 while(rs.next()) {
				 
				this.data.add(//  creation du l'objet maladies_diagnostique
						new maladies_diagnostique(rs.getString(2),code.getText()) );
				i=1 ;
			}
			 this.maladiesD_column.setCellValueFactory(new PropertyValueFactory<maladies_diagnostique,String>("maladies_diagnostique"));
				
				this.tablemaladies.setItems(this.data);
				this.code.setDisable(true);
			 if (i==0) { Alert alert0 = new Alert(AlertType.WARNING);
				alert0.setTitle("Ereur");
				alert0.setHeaderText(null);
				alert0.setContentText( "Il n'a pas des maladies diagnostique");
				alert0.showAndWait();
				 
			 }	
		} }catch (SQLException e) {
			System.err.println(  e);
		}		
		
		

				
			}
	@FXML 
	private void SuprimierMaladies(ActionEvent event) throws IOException, SQLException{
		
		if (this.tablemaladies.getSelectionModel().getSelectedIndex()==-1) {
			Alert alert0 = new Alert(AlertType.WARNING);
			alert0.setTitle("Ereur");
			alert0.setHeaderText(null);
			alert0.setContentText( "Sélectionner votre ligne ");
			alert0.showAndWait();
		}
			// message de confirmation 
		else {Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Bilan");
		alert.setHeaderText("Voulez-vous vraiment déplacer ce maladie vers la corbeille ?");
		alert.setContentText(null);
		 
		Optional<ButtonType> option= alert.showAndWait();
		if (option.get()==ButtonType.OK) { 
			PreparedStatement ps= null ;
			Connection conn = dbConnection.getConnection();
			  String sql = "DELETE FROM maladies_diagnostique where id= ? and maladies = ? ";
			  
			try { ps =conn.prepareStatement(sql);
			ps.setString(1,code.getText());
			ps.setString(2,this.maladiesD_column.getCellData(this.tablemaladies.getSelectionModel().getSelectedIndex()));
			ps.execute();
			tablemaladies.getItems().remove(this.tablemaladies.getSelectionModel().getSelectedIndex());
	
				
			} catch (Exception e) {
				Alert alert2 = new Alert(AlertType.WARNING);
				alert2.setTitle("Ereur");
				alert2.setHeaderText(null);
				alert2.setContentText(e.toString());
				alert2.showAndWait();
				
						}
		}
		}
		
		
	}
	
		
}
