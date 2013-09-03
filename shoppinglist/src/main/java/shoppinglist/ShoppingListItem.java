package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import lombok.AccessLevel;
import lombok.Getter;

public class ShoppingListItem implements TextRepresentable {
    private CompositeMeasurement measurement;
    @Getter(AccessLevel.PACKAGE) private String product;

    @Inject
    ShoppingListItem(@Assisted CompositeMeasurement measurement, @Assisted String product) {
        this.measurement = measurement;
        this.product = product;
    }

    @Override
    public String getText() {
        return String.format("%s %s", measurement.getText(), product);
    }

    public void add(ShoppingListItem that) {
        if (!this.product.equals(that.product)) { throw new IllegalArgumentException(); }
        this.measurement.addCompositeMeasurement(that.measurement);
    }
}
