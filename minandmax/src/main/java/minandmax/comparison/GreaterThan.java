package minandmax.comparison;

public class GreaterThan<T extends Comparable<T>> implements Comparison<T> {

    @Override
    public boolean apply(T lhs, T rhs) {
        return lhs.compareTo(rhs) > 0;
    }
}
