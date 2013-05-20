package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import lombok.AccessLevel;
import lombok.Getter;

import java.text.DecimalFormat;

@Getter(AccessLevel.PACKAGE)
public class SingleMeasurement implements TextRepresentable {
    private double quantity;
    private Unit unit;

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

    public void add(double quantity) {
        this.quantity += quantity;
    }
}
