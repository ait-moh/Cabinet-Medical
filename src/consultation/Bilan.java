package consultation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bilan {
	
	
	
private StringProperty analyse;
private StringProperty resultat;
	
	public Bilan(String analyse,String resultat) {
		super();
		this.analyse =  new SimpleStringProperty(analyse);
		this.resultat =  new SimpleStringProperty(resultat);
	
	}
	public String getanalyse() {
		return  analyse.get();
	}
	public String getresultat() {
		return  resultat.get();
	}
	
	public void setanalyse(String analyse) {
		 this.analyse.set(analyse);
	}
	public void setresultat(String resultat) {
		 this.resultat.set(resultat);
	}

	
	public StringProperty analyseProperty() {
		return this.analyse;
	}
	public StringProperty resultatProperty() {
		return this.resultat;
	}
	

}
