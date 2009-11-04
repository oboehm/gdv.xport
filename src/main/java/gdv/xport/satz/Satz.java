/**
 * 
 */
package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.SATZART;
import static patterntesting.runtime.NullConstants.NULL_STRING;
import gdv.xport.config.Config;
import gdv.xport.feld.*;

import java.io.*;

import org.apache.commons.logging.*;

/**
 * @author oliver
 */
public class Satz {
	
	private static final Log log = LogFactory.getLog(Satz.class);
	/** 4 Zeichen */
	protected final NumFeld satzart = new NumFeld(SATZART, 4, 1);
	/** Teildatensaetze */
	protected Teildatensatz[] teildatensatz;
	
	protected Satz(int art) {
		this(art, 1);
	}
	
	protected Satz(String art) {
		this(art, (art.length() + 255) / 256);
	}
	
	protected Satz(NumFeld art) {
		this(art.getInhalt());
	}
	
	public Satz(NumFeld art, int n) {
		this.satzart.setInhalt(art.getInhalt());
		this.createTeildatensaetze(n);
	}
	
	public Satz(String content, int n) {
		this.satzart.setInhalt(content.substring(0, 4));
		this.createTeildatensaetze(n);
		if (content.length() > 4) {
			try {
	            this.importFrom(content);
            } catch (IOException ioe) {
	            throw new IllegalArgumentException("1st argument too short", ioe);
            }
		}
	}
	
	/**
	 * @param art z.B. 100 (f. Adressteil)
	 * @param n Anzahl der Teildatensaetze
	 */
	public Satz(int art, int n) {
		this.satzart.setInhalt(art);
		this.createTeildatensaetze(n);
	}
	
	protected void createTeildatensaetze(int n) {
		teildatensatz = new Teildatensatz[n];
		for (int i = 0; i < n; i++) {
			teildatensatz[i] = new Teildatensatz(satzart, i+1);
		}
	}
	
	/**
	 * Fuegt das uebergebene Feld zur Liste der Datenfelder hinzu
	 * TODO: Konflikterkennung bei ueberlappenden Feldern (-> Exception)
	 * @param feld
	 */
	public void add(Feld feld) {
		int n = feld.getTeildatensatzNr();
		this.teildatensatz[n-1].add(feld);
	}
	
	/**
	 * Fuellt fuer alle leeren Stellen ein entsprechendes Fuellfeld auf.
	 */
	public void addFiller() {
		throw new UnsupportedOperationException("not yet implemented");
	}
	
	/**
	 * Setzt das angegebene Feld in allen Teildatensaetzen, in denen es
	 * gefunden wird. Normalerweise braeuchten wir eigentlich nur die erste
	 * Fundstelle setzen, da die anderen Teildatensaetze (hoffentlich) auf
	 * die gleiche Referenz verweisen - aber sicher ist sicher.
	 * Falls das Feld nicht gefunden wird, wird eine IllegalArgumentException
	 * geworfen.
	 * 
	 * @param name Name des Felds (Bezeichnung)
	 * @param value
	 */
	public void set(String name, String value) {
		boolean found = false;
		for (int i = 0; i < teildatensatz.length; i++) {
			Feld x = teildatensatz[i].getFeld(name);
			if (x != Feld.NULL_FELD) {
				x.setInhalt(value);
				found = true;
			}
		}
		if (!found) {
			throw new IllegalArgumentException("Feld \"" + name +"\" not found");
		}
	}
	
	/**
	 * @param name gesuchtes Feld
	 * @return Inhalt des gefundenden Felds
	 *         (NULL_STRING, falls 'name' nicht gefunden wurde)
	 */
	public String get(String name) {
		Feld f = getFeld(name);
		if (f == Feld.NULL_FELD) {
			return NULL_STRING;
		} else {
			return f.getInhalt();
		}
	}
	
	/**
	 * @param name gesuchtes Feld
	 * @return NULL_FELD, falls das angegebene Feld nicht gefunden wird
	 */
	public Feld getFeld(String name) {
		for (int i = 0; i < teildatensatz.length; i++) {
			Feld x = teildatensatz[i].getFeld(name);
			if (x != Feld.NULL_FELD) {
				return x;
			}
		}
		return Feld.NULL_FELD;
	}
	
	public int getSatzart() {
		return satzart.toInt();
	}
	
	public void export(Writer writer) throws IOException {
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].export(writer);
		}
	}
	
	/**
	 * Eigentlich wollte ich ja diese Methode "import" nennen, aber das
	 * kollidiert leider mit dem Schluesselwort "import" in Java.
	 * 
	 * @param s
	 * @throws IOException 
	 */
	public void importFrom(String s) throws IOException {
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].importFrom(s.substring(i * 256));
		}
	}
	
	public void importFrom(InputStream istream) throws IOException {
		importFrom(new InputStreamReader(istream, Config.DEFAULT_ENCODING));
	}
		
	public void importFrom(Reader reader) throws IOException {
		importFrom(new PushbackReader(reader, 4));
	}
	
	public void importFrom(PushbackReader reader) throws IOException {
		char[] cbuf = new char[256];
		for (int i = 0; i < teildatensatz.length; i++) {
			int art = readSatzart(reader);
			if (art != this.getSatzart()) {
				log.warn((teildatensatz.length - i) + " more Teildatensaetze expected for " + this
				        + ", but got data for Satzart " + art);
				break;
			}
			reader.read(cbuf);
			teildatensatz[i].importFrom(new String(cbuf));
			skipNewline(reader);
		}
	}
	
	/**
	 * Liest 4 Bytes, um die Satzart zu bestimmen und stellt die Bytes
	 * anschliessend wieder zurueck in den Reader.
	 * 
	 * @param reader
	 * @return Satzart
	 * @throws IOException
	 */
	public static int readSatzart(PushbackReader reader) throws IOException {
		char[] cbuf = new char[4];
		reader.read(cbuf);
		reader.unread(cbuf);
		return Integer.parseInt(new String(cbuf));
	}
	
	private static void skipNewline(PushbackReader reader) throws IOException {
		char[] cbuf = new char[1];
		do {
			if (reader.read(cbuf) == -1) {
				log.info("end of file detected");
				return;
			}
		} while ((cbuf[0] == '\n') || (cbuf[0] == '\r'));
		reader.unread(cbuf);
	}
	
	public boolean isValid() {
		if (!this.satzart.isValid()) {
			log.info(this + " is invalid: invalid Satzart " + this.satzart);
			return false;
		}
		if (this.teildatensatz != null) {
			for (int i = 0; i < teildatensatz.length; i++) {
		        if (!teildatensatz[i].isValid()) {
		        	log.info("Teildatensatz " + (i+1) + " is invalid");
		        	return false;
		        }
	        }
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return this.toShortString();
		} catch (RuntimeException shouldNeverHappen) {
			log.error("shit happens in toString()", shouldNeverHappen);
			return super.toString();
		}
	}
	
	public String toShortString() {
		return "Satzart " + this.satzart.getInhalt() + " ("
				+ this.toLongString().substring(0, 60) + "...)";
	}
	
	public String toLongString() {
		StringWriter swriter = new StringWriter();
		try {
			this.export(swriter);
		} catch (IOException canthappen) {
			log.warn(canthappen + " ignored", canthappen);
			swriter.write(canthappen.getLocalizedMessage());
		}
		return swriter.toString();
	}

	/* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
	    try {
	    	return this.equals((Satz) other);
	    } catch (ClassCastException cce) {
	    	return false;
	    }
    }
    
    public boolean equals(Satz other) {
    	if (this.getSatzart() != other.getSatzart()) {
    		return false;
    	}
    	for (int i = 0; i < teildatensatz.length; i++) {
    		if (!this.teildatensatz[i].equals(other.teildatensatz[i])) {
    			return false;
    		}
    	}
    	return true;
    }

	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	    return this.toLongString().hashCode();
    }

}
