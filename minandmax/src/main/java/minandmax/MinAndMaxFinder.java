package minandmax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MinAndMaxFinder<T extends Comparable<T>> {
    Collection<T> collection;

    public MinAndMaxFinder(T... elements) {
        collection = new ArrayList<T>();
        Collections.addAll(this.collection, elements);
    }

    public T min() {
        T min = null;
        for (T element : collection) {
            if (min == null || element.compareTo(min) < 0) {
                min = element;
            }
        }
        return min;
    }

    public T max() {
        T max = null;
        for (T element : collection) {
            if (max == null || element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }
}
