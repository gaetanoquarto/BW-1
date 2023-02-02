package enums;

public enum TappeAutobus {
    QUIRINALE(8),
    PIAZZA_VENEZIA(5),
    ARGENTINA(10),
    CHIESA_NUOVA(3),
    PONTE_VITTORIO_EMANUELE(12),
    TRANSPONTINA(9),
    CASTEL_SANT_ANGELO(12);
	
	private final int minuti;

	TappeAutobus(int i) {

		this.minuti = i;
	}
	
	public int getMinuti() {
		return minuti;
	}
}
