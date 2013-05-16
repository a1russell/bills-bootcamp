package minandmax;

public interface ComparerFactory<T> {
    Comparer<T> create(T... elements);
}
