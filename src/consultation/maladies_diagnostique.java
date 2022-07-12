package consultation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class maladies_diagnostique {
	private StringProperty Code ;
	private StringProperty maladies_diagnostique;
	
	public maladies_diagnostique(String maladies,String Code) {
		super();
		this.maladies_diagnostique =  new SimpleStringProperty(maladies);
 	    this.Code = new SimpleStringProperty(Code);
	}
	public String getmaladies_diagnostique() {
		return maladies_diagnostique.get();
	}
	public String getCode() {
		return Code.get();
	}
	public void setmaladies_diagnostique(String maladies) {
		 this.maladies_diagnostique.set(maladies);
	}
	
	public void setCode(String code) {
		 this.Code.set(code);
	}
	
	public StringProperty maladies_diagnostiqueProperty() {
		return this.maladies_diagnostique;
	}
	public StringProperty Code() {
		return this.Code;
	}

}
