package minandmax.comparison;

public class IntGreaterThan implements Comparison<Integer> {
    @Override
    public boolean apply(Integer lhs, Integer rhs) {
        return lhs.compareTo(rhs) > 0;
    }
}
