package Medecin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GererLesComptesController implements Initializable {

	@FXML private TextField username_medecin;
	@FXML private TextField password_medecin;
	
	@FXML private TextField username_secretaire;
	@FXML private TextField password_secretaire;
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

	public void modifier_compte_medecin(ActionEvent event)
	{
		String sqlInsert = "UPDATE login SET username = ?, password = ?  WHERE division = ? ";
		
		
try {
	
	
	Connection conn = dbConnection.getConnection();
	PreparedStatement stmt = conn.prepareStatement(sqlInsert);

	

	
	stmt.setString(1, this.username_medecin.getText());
	stmt.setString(2, this.password_medecin.getText());
	stmt.setString(3,"Medecin");
	
	stmt.execute();
	conn.close();

	JOptionPane.showMessageDialog(null, "Le compte médecin est modifié avec succés");
	
	   } catch (SQLException e) {
	JOptionPane.showMessageDialog(null,e);
}catch (Exception e) {
	JOptionPane.showMessageDialog(null,e);
}
	}
	
	
	public void modifier_compte_secretaire(ActionEvent event)
	{
		String sqlInsert = "UPDATE login SET username = ?, password = ?  WHERE division = ? ";
		
		
try {
	
	
	Connection conn = dbConnection.getConnection();
	PreparedStatement stmt = conn.prepareStatement(sqlInsert);

	

	
	stmt.setString(1, this.username_secretaire.getText());
	stmt.setString(2, this.password_secretaire.getText());
	stmt.setString(3,"Secretariat");
	
	stmt.execute();
	conn.close();

	JOptionPane.showMessageDialog(null, "Le compte Secretariat est modifié avec succés");
	
	   } catch (SQLException e) {
	JOptionPane.showMessageDialog(null, e);
}catch (Exception e) {
	JOptionPane.showMessageDialog(null,e);
}
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
