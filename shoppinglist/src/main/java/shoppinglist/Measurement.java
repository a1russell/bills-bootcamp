package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.text.DecimalFormat;

public class Measurement {
    private double quantity;
    private Unit unit;

    @Inject
    Measurement(@Assisted double quantity, @Assisted Unit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getText() {
        DecimalFormat formatter = new DecimalFormat("#.#");
        String formattedQuantity = formatter.format(this.quantity);
        return formattedQuantity + " " + unit.getText();
    }
}
