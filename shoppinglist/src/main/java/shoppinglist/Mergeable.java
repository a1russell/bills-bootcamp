package shoppinglist;

interface Mergeable<A, B extends Addable<B>> extends Addable<Mergeable<A, B>> {
    A getMergeKey();
    B getMergeValue();
}
