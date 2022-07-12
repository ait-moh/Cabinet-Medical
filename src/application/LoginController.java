package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Medecin.MedecinController;
import Medecin.MenuPrincipalController;
import Secretariat.GererRDController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginController implements Initializable {

	LoginModel loginModel = new LoginModel();
	  
	@FXML private Label dbstatus;
	@FXML private TextField username;
	@FXML private PasswordField password;
	@FXML private ComboBox<option> combobox;
	@FXML private Button loginButton;
	@FXML private Label loginstatus;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//la méthode initialize va initialiser la fenêtre qui contrôle 
		//vous la trouverai dans toutes les classes contrôlleurs qui chacune d'eux contrôle une page.fxml précise
		if(loginModel.isDatabaseConnected())  {dbstatus.setText("connected to the DB");}
		else {dbstatus.setText(" NOT connected to the DB");}
		
		
		combobox.setItems(FXCollections.observableArrayList(option.values()));
	}
	
	
	
	//Login permettre de verifier les inforlation saisie est afficher les privilèges associer a cet iformation 
	public void Login(ActionEvent event) {
		try {
			if(this.loginModel.isLogin(username.getText(), password.getText(), ((option)this.combobox.getValue()).toString()))
			{
				Stage stage = (Stage)this.loginButton.getScene().getWindow();
				stage.close();
				
				switch (((option)this.combobox.getValue()).toString()) {
				case "Medecin":
					// si l'utulisateur choisit  Medcecin 
					adminLogin( event);
					break;
					// si l'utulisateur choisit secretaire 
				case "Secretariat":
					secretaireLogin( event);
					break;
				}
			}else {
				this.loginstatus.setText("Wrong creditials");
				this.password.setText(null);
		        	}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//secretaireLogin permet d'ouvrir  le privilège de secretaire
	
	public void secretaireLogin(ActionEvent event) {
		
		try{ 
			((Node)event.getSource()).getScene().getWindow().hide();
			
			Stage adminStage = new Stage();
			FXMLLoader Loader = new FXMLLoader();
			Pane root =(Pane) Loader.load(getClass().getResource("/Secretariat/Secretariat.fxml").openStream());
			GererRDController gererRDController = (GererRDController)Loader.getController();
			
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			adminStage.setScene(scene);
			adminStage.setTitle("espace secrétariat");
			adminStage.setResizable(false);
			adminStage.show();
			
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	//secretaireLogin permetre d'ouvrir  la fenetre Menu 
	public void adminLogin(ActionEvent event) {
		
	try{ 
		((Node)event.getSource()).getScene().getWindow().hide();	
			Stage adminStage = new Stage();
			FXMLLoader adminLoader = new FXMLLoader();
			Pane adminroot =(Pane) adminLoader.load(getClass().getResource("/Medecin/MenuPrincipal.fxml").openStream());
			MenuPrincipalController menuPrincipalController = (MenuPrincipalController)adminLoader.getController();
			
			Scene scene = new Scene(adminroot);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			adminStage.setScene(scene);
			adminStage.setTitle("Medecin Dashboard");
			adminStage.setResizable(false);
			adminStage.show();
			
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	
}
