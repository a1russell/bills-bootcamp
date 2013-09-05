package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.text.DecimalFormat;

public class SingleMeasurement implements TextRepresentable, Mergeable<String, AddableDouble> {
    private final String unit;
    private AddableDouble quantity;

    private final SpaceJoiner spaceJoiner;
    private final MergeValueAdder<String, AddableDouble> mergeValueAdder;

    public interface Factory {
        SingleMeasurement create(double quantity, String unit);
    }

    @Inject
    private SingleMeasurement(SpaceJoiner spaceJoiner,
                              MergeValueAdder<String, AddableDouble> mergeValueAdder,
                              @Assisted double quantity,
                              @Assisted String unit) {
        this.spaceJoiner = spaceJoiner;
        this.mergeValueAdder = mergeValueAdder;
        this.quantity = new AddableDouble(quantity);
        this.unit = unit;
    }

    @Override
    public String getText() {
        DecimalFormat formatter = new DecimalFormat("#.#");
        String formattedQuantity = formatter.format(quantity.getValue());
        return spaceJoiner.join(formattedQuantity, unit);
    }

    @Override
    public String getMergeKey() {
        return unit;
    }

    @Override
    public AddableDouble getMergeValue() {
        return quantity;
    }

    @Override
    public void add(Mergeable<String, AddableDouble> that) {
        mergeValueAdder.add(this, that);
    }
}
