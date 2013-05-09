package minandmax.comparison;

public class GreaterThanBelowLimit<T extends Comparable<T>> implements Comparison<T> {

    private T limit;

    public GreaterThanBelowLimit(T limit) {
        if (limit == null) throw new NullPointerException("limit");
        this.limit = limit;
    }

    @Override
    public boolean apply(T lhs, T rhs) {
        return lhs.compareTo(limit) < 0 && lhs.compareTo(rhs) > 0;
    }
}
