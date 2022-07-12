package statistiques;




	import java.net.URL;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.HashSet;
	import java.util.ResourceBundle;

import Medecin.MenuPrincipalController;
import consultation.Consultation;
    import dbUtil.dbConnection;
    import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
	import javafx.scene.chart.LineChart;
	import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

	public class Statistiques implements Initializable {
		CategoryAxis mois ;
		
		NumberAxis nombredeConsultation ;
	@FXML LineChart<String, Number> stat;
	@FXML PieChart piechart;
	@FXML Button btn;

	
	@FXML	private	Label lb1;
	@FXML	private	Label lb2;
	@FXML	private	Label lb3;
	@FXML	private	Label lb4;
	@FXML	private	Label lb5;
	@FXML	private	Label lb6;
	@FXML	private	Label lb7;
	@FXML	private	Label lb8;
	@FXML	private	Label lb9;
	@FXML	private	Label lb10;
	@FXML	private	Label lb11;
	@FXML	private	Label lb12;
	

	@FXML	private	Label tf1;
	@FXML	private	Label tf2;
	@FXML	private	Label tf3;
	@FXML	private	Label tf4;
	@FXML	private	Label tf5;
	@FXML	private	Label tf6;
	@FXML	private	Label tf7;
	@FXML	private	Label tf8;
	@FXML	private	Label tf9;
	@FXML	private	Label tf10;
	@FXML	private	Label tf11;
	@FXML	private	Label tf12;
	
	
	
	
	XYChart.Series<String, Number> series = new XYChart.Series <String, Number>();
	 


	private dbConnection bd;

	
		
	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
			this.bd = new dbConnection();
			//mettre les infos dans la courbe
			XYChart.Series<String, Number> series = new XYChart.Series <String, Number>();
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m1,MenuPrincipalController.nb12));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m2,MenuPrincipalController.nb11));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m3,MenuPrincipalController.nb10));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m4,MenuPrincipalController.nb9));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m5,MenuPrincipalController.nb8));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m6,MenuPrincipalController.nb7));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m7,MenuPrincipalController.nb6));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m8,MenuPrincipalController.nb5));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m9,MenuPrincipalController.nb4));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m10,MenuPrincipalController.nb3));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m11,MenuPrincipalController.nb2));
			series.getData().add(new XYChart.Data <>(MenuPrincipalController.m12,MenuPrincipalController.nb1));
			this.stat.getData().add(series);
			
			//mettre les infos dans le piechart
			ObservableList<Data> list = FXCollections.observableArrayList(
					new PieChart.Data(MenuPrincipalController.m1, MenuPrincipalController.montant12),
					new PieChart.Data(MenuPrincipalController.m2, MenuPrincipalController.montant11),
					new PieChart.Data(MenuPrincipalController.m3, MenuPrincipalController.montant10),
					new PieChart.Data(MenuPrincipalController.m4, MenuPrincipalController.montant9),
					new PieChart.Data(MenuPrincipalController.m5, MenuPrincipalController.montant8),
					new PieChart.Data(MenuPrincipalController.m6, MenuPrincipalController.montant7),
					new PieChart.Data(MenuPrincipalController.m7, MenuPrincipalController.montant6),
					new PieChart.Data(MenuPrincipalController.m8, MenuPrincipalController.montant5),
					new PieChart.Data(MenuPrincipalController.m9, MenuPrincipalController.montant4),
					new PieChart.Data(MenuPrincipalController.m10, MenuPrincipalController.montant3),
					new PieChart.Data(MenuPrincipalController.m11, MenuPrincipalController.montant2),
					new PieChart.Data(MenuPrincipalController.m12, MenuPrincipalController.montant1)
					
					);
			
			piechart.setData(list);
			
			
			//mettre les infos dans les labels
			lb1.setText(MenuPrincipalController.m1);
			lb2.setText(MenuPrincipalController.m2);
			lb3.setText(MenuPrincipalController.m3);
			lb4.setText(MenuPrincipalController.m4);
			lb5.setText(MenuPrincipalController.m5);
			lb6.setText(MenuPrincipalController.m6);
			lb7.setText(MenuPrincipalController.m7);
			lb8.setText(MenuPrincipalController.m8);
			lb9.setText(MenuPrincipalController.m9);
			lb10.setText(MenuPrincipalController.m10);
			lb11.setText(MenuPrincipalController.m11);
			lb12.setText(MenuPrincipalController.m12);
	
			tf1.setText(String.valueOf(MenuPrincipalController.montant12));
			tf2.setText(String.valueOf(MenuPrincipalController.montant11));
			tf3.setText(String.valueOf(MenuPrincipalController.montant10));
			tf4.setText(String.valueOf(MenuPrincipalController.montant9));
			tf5.setText(String.valueOf(MenuPrincipalController.montant8));
			tf6.setText(String.valueOf(MenuPrincipalController.montant7));
			tf7.setText(String.valueOf(MenuPrincipalController.montant6));
			tf8.setText(String.valueOf(MenuPrincipalController.montant5));
			tf9.setText(String.valueOf(MenuPrincipalController.montant4));
			tf10.setText(String.valueOf(MenuPrincipalController.montant3));
			tf11.setText(String.valueOf(MenuPrincipalController.montant2));
			tf12.setText(String.valueOf(MenuPrincipalController.montant1));
			
			
			
			
						
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
			
	
			
		
	
		
	}


