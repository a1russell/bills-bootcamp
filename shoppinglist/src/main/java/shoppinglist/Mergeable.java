package shoppinglist;

interface Mergeable<A, B> {
    void add(Mergeable<A, B> element);
    A getMergeKey();
    B getMergeValue();
}
