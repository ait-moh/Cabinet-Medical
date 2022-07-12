package consultation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class maladies {
	
	private StringProperty maladies_chroniques;
	
	public maladies(String maladies) {
		super();
		this.maladies_chroniques =  new SimpleStringProperty(maladies);
	
	
	}
	public String getMaladies_chronique() {
		return maladies_chroniques.get();
	}
	
	public void setMaladies_chronique(String maladies) {
		 this.maladies_chroniques.set(maladies);
	}
	

	
	public StringProperty maladies_chroniquesProperty() {
		return this.maladies_chroniques;
	}
	

}
