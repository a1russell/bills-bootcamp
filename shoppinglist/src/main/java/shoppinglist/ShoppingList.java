package shoppinglist;

import com.google.common.base.Optional;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

public class ShoppingList implements TextRepresentable {
    Collection<ShoppingListItem> shoppingListItems = newArrayList();

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
        Optional<ShoppingListItem> existingItem = findItemWithProduct(shoppingListItem.getProduct());
        if (existingItem.isPresent()) {
            existingItem.get().add(shoppingListItem);
        } else {
            shoppingListItems.add(shoppingListItem);
        }
    }

    private Optional<ShoppingListItem> findItemWithProduct(String product) {
        for (ShoppingListItem shoppingListItem : shoppingListItems) {
            if (product.equals(shoppingListItem.getProduct())) {
                return Optional.of(shoppingListItem);
            }
        }
        return Optional.absent();
    }
}
