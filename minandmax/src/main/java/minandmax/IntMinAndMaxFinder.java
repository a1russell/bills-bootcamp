package minandmax;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;
import minandmax.comparison.Comparison;
import minandmax.comparison.IntGreaterThanBelowLimitFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class IntMinAndMaxFinder {
    private final Collection<Integer> collection;
    private final Comparison<Integer> lessThan;
    private final Comparison<Integer> greatherThan;
    private final IntGreaterThanBelowLimitFactory intGreaterThanBelowLimitFactory;

    @Inject
    IntMinAndMaxFinder(@Named("Less Than") Comparison<Integer> lessThan,
                       @Named("Greater Than") Comparison<Integer> greatherThan,
                       IntGreaterThanBelowLimitFactory intGreaterThanBelowLimitFactory,
                       @Assisted Integer... elements) {
        this.collection = new ArrayList<Integer>(elements.length);
        Collections.addAll(this.collection, elements);
        this.lessThan = lessThan;
        this.greatherThan = greatherThan;
        this.intGreaterThanBelowLimitFactory = intGreaterThanBelowLimitFactory;
    }

    public Integer compare(Comparison<Integer> comparison) {
        Integer champion = null;
        for (Integer challenger : collection) {
            if (champion == null || comparison.apply(challenger, champion)) {
                champion = challenger;
            }
        }
        return champion;
    }

    public Integer min() {
        return compare(lessThan);
    }

    public Integer max() {
        return compare(greatherThan);
    }

    public Integer maxBelow(Integer limit) {
        return compare(intGreaterThanBelowLimitFactory.create(limit));
    }
}
