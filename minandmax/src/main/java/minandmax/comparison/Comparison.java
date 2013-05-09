package minandmax.comparison;

public interface Comparison<T extends Comparable<T>> {
    boolean apply(T lhs, T rhs);
}
