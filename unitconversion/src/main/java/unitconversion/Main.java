package unitconversion;

public class Main {

    public static void main(String[] argv) {
        UnitConversions conversions = new UnitConversions();
        int originalQuantity = 3;
        Unit originalUnit = Unit.TSP;
        MeasurementConverter measurement = new Measurement(conversions, originalQuantity, originalUnit);
        Unit desiredUnit = Unit.TBSP;
        double desiredQuantity;
        try {
            desiredQuantity = measurement.convert(desiredUnit);
            System.out.println(Integer.valueOf(originalQuantity).toString() + " " + originalUnit +
                               " is " + Double.valueOf(desiredQuantity).toString() + " " + desiredUnit);
        } catch (InvalidConversionException e) {
            System.out.println("Cannot convert " + originalUnit + " to " + desiredUnit);
        }
    }
}
