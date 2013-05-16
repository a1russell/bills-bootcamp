package minandmax.comparison;

public class StringGreaterThan implements Comparison<String> {
    @Override
    public boolean apply(String lhs, String rhs) {
        return lhs.compareTo(rhs) > 0;
    }
}
