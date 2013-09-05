package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ShoppingListItem implements TextRepresentable, Mergeable<String, CompositeMeasurement> {
    private final SpaceJoiner spaceJoiner;
    private CompositeMeasurement measurement;
    private String product;

    @Inject
    ShoppingListItem(@Assisted CompositeMeasurement measurement, @Assisted String product) {
        this.spaceJoiner = new SpaceJoiner();
        this.measurement = measurement;
        this.product = product;
    }

    @Override
    public String getText() {
        return spaceJoiner.join(measurement.getText(), product);
    }

    @Override
    public String getMergeKey() {
        return product;
    }

    @Override
    public CompositeMeasurement getMergeValue() {
        return measurement;
    }

    @Override
    public void add(Mergeable<String, CompositeMeasurement> that) {
        if (!this.getMergeKey().equals(that.getMergeKey())) { throw new IllegalArgumentException(); }
        this.getMergeValue().add(that.getMergeValue());
    }
}
