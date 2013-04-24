package unitconversion;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Measurement implements MeasurementConverter {
    @NonNull private UnitConversions unitConversions;
    @NonNull private int quantity;
    @NonNull private Unit unit;

    @Override
    public double convert(Unit desiredUnit) throws InvalidConversionException {
        return quantity * unitConversions.convert(unit, desiredUnit);
    }
}
