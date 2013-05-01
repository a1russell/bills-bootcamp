package unitconversion;

public class Measurement implements MeasurementConverter {
    private UnitConversions unitConversions;
    private int quantity;
    private Unit unit;

    public Measurement(UnitConversions unitConversions, int quantity, Unit unit) {
        if (unitConversions == null) throw new NullPointerException("unitConversions");
        if (unit == null) throw new NullPointerException("unit");
        this.unitConversions = unitConversions;
        this.quantity = quantity;
        this.unit = unit;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public double convert(Unit desiredUnit) throws InvalidConversionException {
        return unitConversions.convert(unit, desiredUnit).apply((double) quantity);
    }
}
