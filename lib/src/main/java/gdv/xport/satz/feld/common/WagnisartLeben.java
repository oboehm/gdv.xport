/*
 * Copyright (c) 2013
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gdv.xport.satz.feld.common;

/**
 * The Enum WagnisartLeben.
 */
public enum WagnisartLeben {

	/** The null. */
	NULL(-1),

	/** The angaben versicherte person. */
	ANGABEN_VERSICHERTE_PERSON(0),

	/** The kapitallebensversicherung. */
	KAPITALLEBENSVERSICHERUNG(1),

	/** The risikoversicherung. */
	RISIKOVERSICHERUNG(3),

	/** The rentenversicherung. */
	RENTENVERSICHERUNG(2),

	/** The bu. */
	BU(4),

	/** The bu selbststaendige. */
	BU_SELBSTSTAENDIGE(8),

	/** The risikozusatz. */
	RISIKOZUSATZ(5),

	/** The unfall. */
	UNFALL(6),

	/** The kapital oder fondgebundene lebensversicherung. */
	KAPITAL_ODER_FONDGEBUNDENE_LEBENSVERSICHERUNG(7),

	/** The fondsgebundene rentenversicherung. */
	FONDSGEBUNDENE_RENTENVERSICHERUNG(9);

	private final int code;

	private WagnisartLeben(final int code) {
		this.code = code;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Checks if is in.
	 *
	 * @param code the code
	 * @return the wagnisart leben
	 */
	public static WagnisartLeben isIn(final int code) {
		for (WagnisartLeben existing : WagnisartLeben.values()) {
			if (existing.getCode() == code) {
				return existing;
			}
		}
		return WagnisartLeben.NULL;
	}

}
