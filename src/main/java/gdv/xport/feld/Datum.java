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
import java.util.*;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.constraint.MatchPatternCheck;
import net.sf.oval.context.ClassContext;

/**
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 */
public class Datum extends Feld {

    private DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

    public Datum(String name, int start) {
        this(name, 8, start);
    }
    
    public Datum(String name, String mmddjjjj) {
        this(name, 8, 1, mmddjjjj);
    }

    public Datum(String name, int length, int start) {
        super(name, length, start, Align.RIGHT);
    }
    
    public Datum(String name, int length, int start, String mmddjjjj) {
        super(name, length, start, mmddjjjj, Align.RIGHT);
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

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        if (super.isEmpty()) {
            return true;
        }
        try {
            int n = Integer.parseInt(this.getInhalt());
            return n == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#isValid()
     */
    @Override
    public boolean isValid() {
        return this.validate().isEmpty();
    }
    
    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#isInvalid()
     */
    @Override
    public boolean isInvalid() {
        return !this.isValid();
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#validate()
     */
    @Override
    public List<ConstraintViolation> validate() {
        List<ConstraintViolation> violations = super.validate();
        if (this.isEmpty()) {
            return violations;
        }
        try {
            Date date = this.toDate();
            String conv = this.dateFormat.format(date);
            String orig = this.getInhalt();
            if (!conv.equals(orig)) {
                throw new RuntimeException(orig + " is not a valid date (would be interpreted as "
                        + conv + ")");
            }
        } catch (RuntimeException e) {
            ConstraintViolation cv = new ConstraintViolation(new MatchPatternCheck(), e
                    .getLocalizedMessage(), this, this.getInhalt(), new ClassContext(this
                    .getClass()));
            violations.add(cv);
        }
        return violations;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#hashCode()
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
