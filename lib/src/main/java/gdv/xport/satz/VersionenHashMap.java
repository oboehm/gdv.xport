package gdv.xport.satz;

import gdv.xport.feld.Version;
import gdv.xport.util.SatzTyp;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

/**
 * Die Idee bei der VersionenHashMap ist, bei der Abfrage der Versionen fuer
 * einen bestimmte Satzart (also z.B. 0220.010.7.6) eben nur auf die
 * "uebergeordnete" Gruppe zu gehen (in dem Falle 0220.010, da nur für diese
 * Kombination eine Version im Vorsatz geliefert wird).
 * Diese Logik ist bei {@link VersionenHashMap#get(Object)} abgebildet (s.a.
 * https://github.com/oboehm/gdv.xport/issues/64#issuecomment-924107450).
 *
 * @since 5.2
 * @author markusneidhart
 */
public class VersionenHashMap extends HashMap<SatzTyp, Version> {

    @Override
    public boolean containsKey(Object satzTyp) {
        return findEntry(satzTyp).isPresent();
    }

    @Override
    public Version get(Object satzTyp) {
        Optional<Entry<SatzTyp, Version>> entry = findEntry(satzTyp);
        return entry.map(Entry::getValue).orElse(null);
    }

    private Optional<Entry<SatzTyp, Version>> findEntry(Object key) {
        if (!(key instanceof SatzTyp)) {
            return Optional.empty();
        }

        SatzTyp satzTyp = (SatzTyp) key;
        return entrySet().stream()
                .filter(e -> e.getKey().getSatzart() == satzTyp.getSatzart() && (!satzTyp.hasSparte() || !e.getKey().hasSparte() || e.getKey().getSparte() == satzTyp.getSparte()))
                .sorted(Comparator.comparingInt(e1 -> -e1.getKey().getSparte()))
                .findFirst();
    }

}
