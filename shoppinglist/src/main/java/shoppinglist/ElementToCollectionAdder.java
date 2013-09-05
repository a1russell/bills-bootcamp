package shoppinglist;

import com.google.common.base.Optional;

import java.util.Collection;

class ElementToCollectionAdder<A extends Mergeable<B, C>, B, C extends Addable<C>> {
    public void add(A element, Collection<A> collection) {
        Optional<Mergeable<B, C>> existingElement = findElementWithMergeKey(element.getMergeKey(), collection);
        if (existingElement.isPresent()) {
            existingElement.get().add(element);
        } else {
            collection.add(element);
        }
    }

    private Optional<Mergeable<B, C>> findElementWithMergeKey(B mergeKey, Collection<A> collection) {
        for (Mergeable<B, C> element : collection) {
            if (mergeKey.equals(element.getMergeKey())) {
                return Optional.of(element);
            }
        }
        return Optional.absent();
    }
}