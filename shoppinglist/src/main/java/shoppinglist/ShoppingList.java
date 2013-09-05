package shoppinglist;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

public class ShoppingList implements TextRepresentable {
    private final ElementToCollectionAdder<ShoppingListItem, String, CompositeMeasurement> adder;
    private final TextJoiner textJoiner;
    private final Collection<ShoppingListItem> shoppingListItems;

    public ShoppingList() {
        adder = new ElementToCollectionAdder<ShoppingListItem, String, CompositeMeasurement>();
        shoppingListItems = newArrayList();
        textJoiner = new TextJoiner();
    }

    @Override
    public String getText() {
        return textJoiner.join(shoppingListItems, "\n");
    }

    public void add(ShoppingListItem shoppingListItem) {
        adder.add(shoppingListItem, shoppingListItems);
    }
}
