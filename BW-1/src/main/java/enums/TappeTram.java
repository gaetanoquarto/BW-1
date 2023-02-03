package enums;

public enum TappeTram {

	COLLI_PORTUENSI(7),
    SAN_GIOVANNI(12),
    GIANICOLENSE(11),
    SAN_CAMILLO(4),
    PONTE_BIANCO(7),
    STAZIONE_TRASTEVERE(9),
    MINISTERO_PUBBLICA_ISTRUZIONE(8);
	
	private final int minuti;

	TappeTram(int i) {
		this.minuti = i;
	}
	
	public int getMinuti() {
		return minuti;
	}
	
}
