package unitconversion.internal;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class UnitConversion {
    @NonNull private UnitConversionGraph graph;

    public double getMultiplier(String originalUnit, String desiredUnit) {
        return 1;
    }
}
