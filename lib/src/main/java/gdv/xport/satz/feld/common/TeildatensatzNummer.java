/*
 * Copyright (c) 2013 by Oli B.
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
 * The Enum TeildatensatzNummer.
 */
public enum TeildatensatzNummer {

    /** The null. */
    NULL(-1),

    /** The bezugsrechte. */
    BEZUGSRECHTE(6),

    /** The auszahlungen. */
    AUSZAHLUNGEN(7),

    /** The zukuenftige summenaenderung. */
    ZUKUENFTIGE_SUMMENAENDERUNG(8),

    /** The wertungssummen. */
    WERTUNGSSUMMEN(9);

	private int code = -1;

	private TeildatensatzNummer(final int code) {
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
	 * @return the teildatensatz nummer
	 */
	public static TeildatensatzNummer isIn(final int code) {
		for (TeildatensatzNummer existing : TeildatensatzNummer.values()) {
			if (existing.getCode() == code) {
				return existing;
			}
		}
		return TeildatensatzNummer.NULL;
	}
}
