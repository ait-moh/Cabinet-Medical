package Medecin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient {

	private StringProperty id;
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty dateNaissance;
	private StringProperty sex;
	private StringProperty tel;
	private StringProperty email;
	private StringProperty adresse;
	private StringProperty date_premiere_visite;
	private StringProperty date_derniere_visite;
	
	public Patient( String id ,String nom, String prenom, String dateNaissance, String sex, String tel, String email,String adresse,String date_premiere_visite,String date_derniere_visite) {
		super();
		this.id = new SimpleStringProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.dateNaissance = new SimpleStringProperty(dateNaissance);
		this.sex = new SimpleStringProperty(sex);
		this.tel = new SimpleStringProperty(tel);
		this.email = new SimpleStringProperty(email);
		this.adresse =new SimpleStringProperty(adresse);
		this.date_premiere_visite = new SimpleStringProperty(date_premiere_visite);
		this.date_derniere_visite = new SimpleStringProperty(date_derniere_visite);

	}
	
	

	
	public String getId() {
		return id.get();
	}
	public void setId(String id) {
		this.id.set(id);
	}
	public String getNom() {
		return nom.get();
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	public String getPrenom() {
		return prenom.get();
	}
	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}
	public String getDateNaissance() {
		return dateNaissance.get();
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance.set(dateNaissance);
	}
	public String getSex() {
		return sex.get();
	}
	public void setSex(String sex) {
		this.sex.set(sex);
	}
	public String getTel() {
		return tel.get();
	}
	public void setTel(String tel) {
		this.tel.set(tel);
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}

	
	public StringProperty idProperty() {
		return this.id;
	}
	
	public StringProperty nomProperty() {
		return this.nom;
	}
	
	public StringProperty prenomProperty() {
		return this.prenom;
	}
	public StringProperty dateNaissanceProperty() {
		return this.dateNaissance;
	}
	
	public StringProperty sexProperty() {
		return this.sex;
	}
	
	public StringProperty telProperty() {
		return this.tel;
	}
	
	public StringProperty emailProperty() {
		return this.email;
	}

	
	public String getAdresse() {
		return adresse.get();
	}	
	
	public String getDate_premiere_visite() {
		return date_premiere_visite.get();
	}	
	
	public String getDate_derniere_visite() {
		return date_derniere_visite.get();
	}	
	
	
	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}
	
	public void setDate_premiere_visite(String date_premiere_visite) {
		this.date_premiere_visite.set(date_premiere_visite);
	}
	
	public void setDate_derniere_visite(String date_derniere_visite) {
		this.date_derniere_visite.set(date_derniere_visite);
	}
	
	public StringProperty adresseProperty() {
		return this.adresse;
	}
	
	public StringProperty date_premiere_visiteProperty() {
		return this.date_premiere_visite;
	}
	
	public StringProperty date_derniere_visiteeProperty() {
		return this.date_derniere_visite;
	}
	
	
	
	

}
