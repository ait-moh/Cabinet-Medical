package consultation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Etat {   
		
	private StringProperty groupeSanguin;
	private StringProperty taille ;
	private StringProperty poids;
	private StringProperty tension;
	private StringProperty teuxDiabete;
	public Etat( String groupeSanguin, String taille,
			String poids ,String tension, String teuxDiabete
			) {
		super();
		
		this.groupeSanguin = new SimpleStringProperty(groupeSanguin);
		this.taille = new SimpleStringProperty(taille);
		this.poids = new SimpleStringProperty(poids);
		this.tension = new SimpleStringProperty(tension);
		this.teuxDiabete = new SimpleStringProperty(teuxDiabete);
	}
	
	public String getgroupeSanguin() {
		return groupeSanguin.get();
	}
	public String gettaille() {
		return taille.get();
	}
	
	public String gettension() {
		return tension.get();
	}
	public String getCommantaire() {
		return teuxDiabete.get();
	}
	public String getpoinds() {
		return poids.get();
	}
	public void getPoids(String poids) {
		this.poids.set(poids);
	}
	
	public void setgroupeSanguin(String group) {
		this.groupeSanguin.set(group);
	}
	
	public void settaille(String taille) {
		this.taille.set(taille);
	}
	public void setteuxDiabete(String teux) {
		this.teuxDiabete.set(teux);
	}
	public void settension(String tension) {
		this.tension.set(tension);
	}
	public StringProperty groupeSanguinProperty() {
		return this.groupeSanguin;
	}
	
	
	public StringProperty tensionProperty() {
		return this.tension;
	}
	
	public StringProperty tailleProperty() {
		return this.taille;
	}
	public StringProperty teuxDiabeteProperty() {
		return this.teuxDiabete;
	}
	public StringProperty poidsProperty() {
		return this.poids;
	}
	


}
