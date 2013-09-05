package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ShoppingListItem implements TextRepresentable, Mergeable<String, CompositeMeasurement> {
    private final String product;
    private CompositeMeasurement measurement;

    private final SpaceJoiner spaceJoiner;
    private final MergeValueAdder<String, CompositeMeasurement> mergeValueAdder;

    public interface Factory {
        ShoppingListItem create(CompositeMeasurement compositeMeasurement, String product);
    }

    @Inject
    private ShoppingListItem(SpaceJoiner spaceJoiner,
                             MergeValueAdder<String, CompositeMeasurement> mergeValueAdder,
                             @Assisted CompositeMeasurement measurement,
                             @Assisted String product) {
        this.spaceJoiner = spaceJoiner;
        this.mergeValueAdder = mergeValueAdder;
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
        mergeValueAdder.add(this, that);
    }
}