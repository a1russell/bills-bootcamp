package shoppinglist;

public interface MeasurementFactory {
    Measurement create(double quantity, Unit unit);
}
