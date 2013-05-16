package minandmax.comparison;

public interface GreaterThanBelowLimitFactory<T extends Comparable<T>> {
    GreaterThanBelowLimit<T> create(T limit);
}
