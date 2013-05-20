package shoppinglist;

public interface CompositeMeasurementFactory {
    CompositeMeasurement create(double quantity, Unit unit);
}
