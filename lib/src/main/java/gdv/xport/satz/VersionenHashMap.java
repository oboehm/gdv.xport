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
        Version v = super.get(satzTyp);
        if (v == null) {
            Optional<Entry<SatzTyp, Version>> entry = findEntry(satzTyp);
            v = entry.map(Entry::getValue).orElse(null);
        }
        return v;
    }

    private Optional<Entry<SatzTyp, Version>> findEntry(Object key) {
        if (!(key instanceof SatzTyp)) {
            return Optional.empty();
        }
        SatzTyp satzTyp = (SatzTyp) key;
        return entrySet().stream()
                .filter(e -> matches(e, satzTyp)).min(Comparator.comparingInt(e1 -> -e1.getKey().getSparte()));
    }

    private static boolean matches(Entry<SatzTyp, Version> e, SatzTyp satzTyp) {
        SatzTyp stored = e.getKey();
        return stored.getSatzart() == satzTyp.getSatzart() &&
                (!satzTyp.hasSparte() || !stored.hasSparte() || stored.getSparte() == satzTyp.getSparte());
    }

}
