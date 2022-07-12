package Secretariat;



public enum jour {
	dimanche,lundi,mardi,mercredi,jeudi,vendredi,samedi;
private jour() {}
	
	public String value() {
		return name();
		}
	
	public static jour fromvalue(String v) {
		return valueOf(v);
	}

}
