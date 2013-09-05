package shoppinglist;

import com.google.inject.Inject;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

public class ShoppingList implements TextRepresentable {
    private final Collection<ShoppingListItem> shoppingListItems;

    private final ElementToCollectionAdder<ShoppingListItem, String, CompositeMeasurement> elementToCollectionAdder;
    private final TextJoiner textJoiner;

    public interface Factory {
        ShoppingList create();
    }

    @Inject
    private ShoppingList(ElementToCollectionAdder<ShoppingListItem,
                                                  String,
                                                  CompositeMeasurement> elementToCollectionAdder,
                         TextJoiner textJoiner) {
        this.elementToCollectionAdder = elementToCollectionAdder;
        this.textJoiner = textJoiner;
        this.shoppingListItems = newArrayList();
    }

    @Override
    public String getText() {
        return textJoiner.join(shoppingListItems, "\n");
    }

    public void add(ShoppingListItem shoppingListItem) {
        elementToCollectionAdder.add(shoppingListItem, shoppingListItems);
    }
}
