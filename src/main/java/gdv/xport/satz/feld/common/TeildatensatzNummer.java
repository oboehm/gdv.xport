package gdv.xport.satz.feld.common;

public enum TeildatensatzNummer {

	NULL(-1), BEZUGSRECHTE(6), AUSZAHLUNGEN(7), ZUKUENFTIGE_SUMMENAENDERUNG(8), WERTUNGSSUMMEN(9);

	private int code = -1;

	private TeildatensatzNummer(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static TeildatensatzNummer isIn(int code) {
		for (TeildatensatzNummer existing : TeildatensatzNummer.values()) {
			if (existing.getCode() == code) {
				return existing;
			}
		}
		return TeildatensatzNummer.NULL;
	}
}
