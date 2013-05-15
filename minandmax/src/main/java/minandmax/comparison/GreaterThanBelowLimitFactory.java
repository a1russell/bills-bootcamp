package minandmax.comparison;

public class GreaterThanBelowLimitFactory<T extends Comparable<T>> {
    public GreaterThanBelowLimit<T> create(T limit) {
        return new GreaterThanBelowLimit<T>(limit);
    }
}
