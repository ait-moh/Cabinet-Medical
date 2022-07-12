package consultation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Consultation {
	private StringProperty code;
	private StringProperty idPatient;
	private StringProperty dateConsultation ;
	private StringProperty Montant;
	private StringProperty Commantaire;
	public Consultation(String code, String idPatient, String dateConsultation,
			String montant ,String commantaire 
			) {
		super();
		this.code =  new SimpleStringProperty(code);
		this.idPatient = new SimpleStringProperty(idPatient);
		this.dateConsultation = new SimpleStringProperty(dateConsultation);
		
		Montant = new SimpleStringProperty(montant);
		Commantaire = new SimpleStringProperty(commantaire);
	}
	public String getCode() {
		return code.get();
	}
	public String getIdPatient() {
		return idPatient.get();
	}
	public String getDateConsultation() {
		return dateConsultation.get();
	}
	
	public String getMontant() {
		return Montant.get();
	}
	public String getCommantaire() {
		return Commantaire.get();
	}
	public void setCode(String code) {
		this.code.set(code);
	}
	public void setIdPatient(String idPatient) {
		this.idPatient.set(idPatient);
	}
	public void setDateConsultation(String dateConsultation) {
		this.dateConsultation.set(dateConsultation);
	}
	
	public void setMontant(String montant) {
		this.Montant.set(montant);
	}
	public void setCommantaire(String commantaire) {
		this.Commantaire.set(commantaire);
	}
	public StringProperty codeProperty() {
		return this.code;
	}
	
	public StringProperty IdPatientProperty() {
		return this.idPatient;
	}
	public StringProperty DateConsultationProperty() {
		return this.dateConsultation;
	}
	
	public StringProperty MontantProperty() {
		return this.Montant;
	}
	public StringProperty CommantaireProperty() {
		return this.Commantaire;
	}
	

	
	
	

}
