package unitconversion;

public interface MeasurementConverter {
    double convert(Unit desiredUnit) throws InvalidConversionException;
}
