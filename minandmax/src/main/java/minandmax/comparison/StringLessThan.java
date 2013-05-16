package minandmax.comparison;

public class StringLessThan implements Comparison<String> {
    @Override
    public boolean apply(String lhs, String rhs) {
        return lhs.compareTo(rhs) < 0;
    }
}
