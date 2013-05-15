package minandmax;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import minandmax.comparison.Comparison;
import minandmax.comparison.GreaterThanBelowLimitFactory;

public class MinAndMaxFinderFactory<T extends Comparable<T>> {
    private final Comparison<T> lessThan;
    private final Comparison<T> greatherThan;
    private final GreaterThanBelowLimitFactory<T> greaterThanBelowLimitFactory;

    @Inject
    public MinAndMaxFinderFactory(@Named("Less Than") Comparison<T> lessThan,
                                  @Named("Greater Than") Comparison<T> greatherThan,
                                  GreaterThanBelowLimitFactory<T> greaterThanBelowLimitFactory) {
        this.lessThan = lessThan;
        this.greatherThan = greatherThan;
        this.greaterThanBelowLimitFactory = greaterThanBelowLimitFactory;
    }

    public MinAndMaxFinder<T> create(T... elements) {
        return new MinAndMaxFinder<T>(lessThan, greatherThan, greaterThanBelowLimitFactory, elements);
    }
}
