package minandmax.comparer;

public interface ComparerFactory<T> {
    Comparer<T> create(T... elements);
}
