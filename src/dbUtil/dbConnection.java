package dbUtil;
import java.sql.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class dbConnection {
			
		public static Connection getConnection() {
		try {	// Connextion a  la BDD 
			Class.forName("org.sqlite.JDBC");
			Connection conn =DriverManager.getConnection("jdbc:sqlite:CabinetMedical.db");
			return conn;
		}catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ereur");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
			return null;
		}
		
		
		}

	}



