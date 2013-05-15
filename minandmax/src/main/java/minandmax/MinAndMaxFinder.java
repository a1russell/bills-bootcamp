package minandmax;

import minandmax.comparison.Comparison;
import minandmax.comparison.GreaterThanBelowLimitFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MinAndMaxFinder<T extends Comparable<T>> {
    private final Collection<T> collection;
    private final Comparison<T> lessThan;
    private final Comparison<T> greatherThan;
    private final GreaterThanBelowLimitFactory<T> greaterThanBelowLimitFactory;

    MinAndMaxFinder(Comparison<T> lessThan,
                    Comparison<T> greatherThan,
                    GreaterThanBelowLimitFactory<T> greaterThanBelowLimitFactory,
                    T... elements) {
        this.collection = new ArrayList<T>(elements.length);
        Collections.addAll(this.collection, elements);
        this.lessThan = lessThan;
        this.greatherThan = greatherThan;
        this.greaterThanBelowLimitFactory = greaterThanBelowLimitFactory;
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
        return compare(lessThan);
    }

    public T max() {
        return compare(greatherThan);
    }

    public T maxBelow(T limit) {
        return compare(greaterThanBelowLimitFactory.create(limit));
    }
}
