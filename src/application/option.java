package application;

public enum option {
	Medecin,Secretariat; // les options pour le combobox dans la première fenêtre
	private option() {}
	
	public String value() {
		return name();
		}
	
	public static option fromvalue(String v) {
		return valueOf(v);
	}

}
