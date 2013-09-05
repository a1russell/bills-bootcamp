package shoppinglist;

class MergeValueAdder<A, B extends Addable<B>> {
    public void add(Mergeable<A, B> originalToUpdate, Mergeable<A, B> addend) {
        if (!originalToUpdate.getMergeKey().equals(addend.getMergeKey())) {
            throw new IllegalArgumentException();
        }
        originalToUpdate.getMergeValue().add(addend.getMergeValue());
    }
}