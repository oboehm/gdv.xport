package gdv.xport.satz.feld.common;

public enum WagnisartLeben {

	NULL(-1), ANGABEN_VERSICHERTE_PERSON(0), KAPITALLEBENSVERSICHERUNG(1), RISIKOVERSICHERUNG(3), RENTENVERSICHERUNG(
	        2), BU(4), BU_SELBSTSTAENDIGE(8), RISIKOZUSATZ(5), UNFALL(6), KAPITAL_ODER_FONDGEBUNDENE_LEBENSVERSICHERUNG(
	        7), FONDSGEBUNDENE_RENTENVERSICHERUNG(9);

	private int code = -1;

	private WagnisartLeben(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static boolean isIn(WagnisartLeben wagnis) {
		for (WagnisartLeben existing : WagnisartLeben.values()) {
			if (existing == wagnis) {
				return true;
			}
		}
		return false;
	}

	public static WagnisartLeben isIn(int code) {
		for (WagnisartLeben existing : WagnisartLeben.values()) {
			if (existing.getCode() == code) {
				return existing;
			}
		}
		return WagnisartLeben.NULL;
	}
}
