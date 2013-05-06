package minandmax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MinAndMaxFinder<T extends Comparable<T>> {
    Collection<T> collection;

    public MinAndMaxFinder(T... elements) {
        collection = new ArrayList<T>();
        Collections.addAll(this.collection, elements);
    }

    public T min() {
        return ((List<T>) collection).get(0);
    }
}
