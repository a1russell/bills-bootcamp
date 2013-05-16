package minandmax.comparison;

public interface Comparison<T> {
    boolean apply(T lhs, T rhs);
}
