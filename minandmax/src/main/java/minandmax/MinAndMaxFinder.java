package minandmax;

import minandmax.comparison.Comparison;
import minandmax.comparison.GreaterThan;
import minandmax.comparison.LessThan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MinAndMaxFinder<T extends Comparable<T>> {
    Collection<T> collection;

    public MinAndMaxFinder(T... elements) {
        collection = new ArrayList<T>();
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

    public T min() {
        return compare(new LessThan<T>());
    }

    public T max() {
        return compare(new GreaterThan<T>());
    }

    public T maxBelow(T limit) {
        return ((ArrayList<T>) collection).get(0);
    }
}
