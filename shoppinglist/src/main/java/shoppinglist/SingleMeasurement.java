package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.text.DecimalFormat;

public class SingleMeasurement implements TextRepresentable, Mergeable<String, Double> {
    private final SpaceJoiner spaceJoiner;
    private double quantity;
    private String unit;

    @Inject
    SingleMeasurement(@Assisted double quantity, @Assisted String unit) {
        this.spaceJoiner = new SpaceJoiner();
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public String getText() {
        DecimalFormat formatter = new DecimalFormat("#.#");
        String formattedQuantity = formatter.format(this.quantity);
        return spaceJoiner.join(formattedQuantity, unit);
    }

    @Override
    public String getMergeKey() {
        return unit;
    }

    @Override
    public Double getMergeValue() {
        return quantity;
    }

    @Override
    public void add(Mergeable<String, Double> that) {
        if (!this.getMergeKey().equals(that.getMergeKey())) { throw new IllegalArgumentException(); }
        this.quantity += that.getMergeValue();
    }
}
