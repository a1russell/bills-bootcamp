package minandmax.comparison;

public class IntLessThan implements Comparison<Integer> {
    @Override
    public boolean apply(Integer lhs, Integer rhs) {
        return lhs.compareTo(rhs) < 0;
    }
}
