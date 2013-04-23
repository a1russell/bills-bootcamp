package unitconversion;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Measurement {
    @NonNull private UnitConversions unitConversions;
    @NonNull private int quantity;
    @NonNull private String unit;

    public double convert(String desiredUnit) {
        return 6;
    }
}
