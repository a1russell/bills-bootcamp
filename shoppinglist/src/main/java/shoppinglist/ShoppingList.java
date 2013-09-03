package shoppinglist;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

public class ShoppingList implements TextRepresentable {
    private final ElementToCollectionAdder<ShoppingListItem, String, CompositeMeasurement> adder;
    private final Collection<ShoppingListItem> shoppingListItems;

    public ShoppingList() {
        adder = new ElementToCollectionAdder<ShoppingListItem, String, CompositeMeasurement>();
        shoppingListItems = newArrayList();
    }

    @Override
    public String getText() {
        String text = "";
        String separator = "\n";
        for (TextRepresentable shoppingListItem : shoppingListItems) {
            text += shoppingListItem.getText() + separator;
        }
        return text.substring(0, text.length() - separator.length());
    }

    public void add(ShoppingListItem shoppingListItem) {
        adder.add(shoppingListItem, shoppingListItems);
    }
}
