package Secretariat;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Rendez_vous {

	
	
	

		private StringProperty num_RD;
		private StringProperty id;
		private StringProperty nom_patient;
		private StringProperty prenom_patient;
		private StringProperty tel_patient;
		private StringProperty email_patient;
		private StringProperty jour_RD;
		private StringProperty heure_RD;
		private StringProperty date_RD;
		private StringProperty inscrit_en_BD;

		
		
		
		
		
		
		public Rendez_vous(String num_RD, String id, String nom_patient,
				String prenom_patient,String tel_patient,String email_patient, String jour_RD, String heure_RD, String date_RD,
				String inscrit_en_BD) {
			super();
			this.num_RD =new SimpleStringProperty(num_RD);
			this.id = new SimpleStringProperty(id);
			this.nom_patient = new SimpleStringProperty(nom_patient);
			this.prenom_patient = new SimpleStringProperty(prenom_patient);
			this.tel_patient = new SimpleStringProperty(tel_patient);
			this.email_patient = new SimpleStringProperty(email_patient);
			this.jour_RD = new SimpleStringProperty(jour_RD);
			this.heure_RD = new SimpleStringProperty(heure_RD);
			this.date_RD = new SimpleStringProperty(date_RD);
			this.inscrit_en_BD = new SimpleStringProperty(inscrit_en_BD);
		}




		

		public String getNum_RD() {
			return num_RD.get();
		}
		
		public String getId() {
			return id.get();
		}
				
		public String getNom_patient() {
			return nom_patient.get();
		}
		
		public String getPrenom_patient() {
			return prenom_patient.get();
		}
		
		public String getTel_patient() {
			return tel_patient.get();
		}
		
		public String getEmail_patient() {
			return email_patient.get();
		}
		
		
		public String getJour_RD() {
			return jour_RD.get();
		}
		
		public String getHeure_RD() {
			return heure_RD.get();
		}
		
		public String getDate_RD() {
			return date_RD.get();
		}
		
		public String getInscrit_en_BD() {
			return inscrit_en_BD.get();
		}
		
		
		
		
		
		
		
		
		
		public void setNum_RD(String num_RD) {
			this.num_RD.set(num_RD);
		}
		
		public void setId(String id) {
			this.id.set(id);
		}
		
		public void setNom_patient(String nom_patient) {
			this.nom_patient.set(nom_patient);
		}
		
		public void setPrenom_patient(String prenom_patient) {
			this.prenom_patient.set(prenom_patient);
		}
		
		public void setTel_patient(String tel_patient) {
			this.tel_patient.set(tel_patient);
		}
		
		public void setEmail_patient(String email_patient) {
			this.email_patient.set(email_patient);
		}
		
		public void setJour_RD(String jour_RD) {
			this.jour_RD.set(jour_RD);
		}
		
		public void setHeure_RD(String heure_RD) {
			this.heure_RD.set(heure_RD);
		}
		
		public void setDate_RD(String date_RD) {
			this.date_RD.set(date_RD);
		}
		
		public void setInscrit_en_BD(String inscrit_en_BD) {
			this.inscrit_en_BD.set(inscrit_en_BD);
		}
		
		
		
		
		
		
		public StringProperty num_RDProperty() {
			return this.num_RD;
		}
		
		public StringProperty idProperty() {
			return this.id;
		}
		
		public StringProperty nom_patientProperty() {
			return this.nom_patient;
		}
		
		public StringProperty prenom_patientProperty() {
			return this.prenom_patient;
		}
		
		public StringProperty tel_patientProperty() {
			return this.tel_patient;
		}
		
		public StringProperty email_patientProperty() {
			return this.email_patient;
		}
		
		public StringProperty jour_RDProperty() {
			return this.jour_RD;
		}
		
		public StringProperty heure_RDProperty() {
			return this.heure_RD;
		}
		
		public StringProperty date_RDProperty() {
			return this.date_RD;
		}
		
		public StringProperty inscrit_en_BDProperty() {
			return this.inscrit_en_BD;
		}
		
		
		
		

	}


