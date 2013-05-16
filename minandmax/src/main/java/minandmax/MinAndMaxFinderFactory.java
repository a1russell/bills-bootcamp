package minandmax;

public interface MinAndMaxFinderFactory<T extends Comparable<T>> {
    MinAndMaxFinder<T> create(T... elements);
}
