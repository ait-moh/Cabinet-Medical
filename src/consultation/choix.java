package consultation;

public enum choix {
	A__positive,A_negative,B__positive,B_negative,AB__positive,AB_negative,O__positive,O_negative ;
	private choix() {}
	
	public String value() {
		return name();
		}
	
	public static choix fromvalue(String v) {
		return valueOf(v);
	}

}
