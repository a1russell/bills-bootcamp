package minandmax.comparer;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import minandmax.comparison.Comparison;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Comparer<T> {
    private final Collection<T> collection;

    @Inject
    Comparer(@Assisted T... elements) {
        this.collection = new ArrayList<T>(elements.length);
        Collections.addAll(this.collection, elements);
    }

    public T compare(Comparison<T> comparison) {
        T champion = null;
        for (T challenger : collection) {
            if (champion == null || comparison.apply(challenger, champion)) {
                champion = challenger;
            }
        }
        return champion;
    }
}
