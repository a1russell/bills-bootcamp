package minandmax.comparison;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class IntGreaterThanBelowLimit implements Comparison<Integer> {
    private Integer limit;

    @Inject
    IntGreaterThanBelowLimit(@Assisted Integer limit) {
        if (limit == null) throw new NullPointerException("limit");
        this.limit = limit;
    }

    @Override
    public boolean apply(Integer lhs, Integer rhs) {
        return lhs.compareTo(limit) < 0 && lhs.compareTo(rhs) > 0;
    }
}
