package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import lombok.AccessLevel;
import lombok.Getter;

import java.text.DecimalFormat;

public class SingleMeasurement implements TextRepresentable {
    private double quantity;
    @Getter(AccessLevel.PACKAGE) private Unit unit;

    @Inject
    SingleMeasurement(@Assisted double quantity, @Assisted Unit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public String getText() {
        DecimalFormat formatter = new DecimalFormat("#.#");
        String formattedQuantity = formatter.format(this.quantity);
        return formattedQuantity + " " + unit.getText();
    }

    public void addSingleMeasurement(SingleMeasurement that) {
        if (!this.unit.equals(that.unit)) { throw new IllegalArgumentException(); }
        this.quantity += that.quantity;
    }
}
