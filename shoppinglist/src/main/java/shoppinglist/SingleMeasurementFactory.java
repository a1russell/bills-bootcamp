package shoppinglist;

public interface SingleMeasurementFactory {
    SingleMeasurement create(double quantity, Unit unit);
}
