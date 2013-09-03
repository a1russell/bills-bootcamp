package shoppinglist;

public interface ShoppingListItemFactory {
    ShoppingListItem create(CompositeMeasurement compositeMeasurement, String product);
}
