package minandmax.comparison;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class GreaterThanBelowLimit<T extends Comparable<T>> implements Comparison<T> {

    private T limit;

    @Inject
    GreaterThanBelowLimit(@Assisted T limit) {
        if (limit == null) throw new NullPointerException("limit");
        this.limit = limit;
    }

    @Override
    public boolean apply(T lhs, T rhs) {
        return lhs.compareTo(limit) < 0 && lhs.compareTo(rhs) > 0;
    }
}
