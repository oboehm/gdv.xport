/*
 * $Id$
 *
 * Copyright (c) 2009 by Oliver Boehm
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
 * 
 * (c)reated 04.10.2009 by oliver (ob@oasd.de)
 */

package gdv.xport.feld;

import java.text.*;
import java.util.Date;

/**
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 */
public class Datum extends Feld {
	
	/** Standard-Format fuer Umwandlungen */
	private static final DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	
	public Datum(String name, int start) {
		this(name, 8, start);
	}
	
	public Datum(String name, int length, int start) {
		super(name, length, start, Align.RIGHT);
	}
	
	public Datum() {
		this(1);
	}
	
	public Datum(int start) {
		this(8, start);
	}
	
	public Datum(int length, int start) {
		super(length, start, Align.RIGHT);
	}
	
	public void setInhalt(Datum d) {
		this.setInhalt(d.getInhalt());
	}
	
	public void setInhalt(Date d) {
		this.setInhalt(dateFormat.format(d));
	}
	
	public Date toDate() {
		try {
			return dateFormat.parse(this.getInhalt());
		} catch (ParseException e) {
			throw new IllegalStateException(this + " has an invalid date (\""
					+ this.getInhalt() + "\")");
		}
	}
	
	public static Datum heute() {
		Datum d = new Datum();
		d.setInhalt(new Date());
		return d;
	}
	
}
