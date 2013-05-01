package unitconversion;

public class Main {

    public static void main(String[] argv) {
        UnitConversions conversions = new UnitConversions();
        int originalQuantity = 23;
        Unit originalUnit = Unit.CELSIUS;
        MeasurementConverter measurement = new Measurement(conversions, originalQuantity, originalUnit);
        Unit desiredUnit = Unit.FAHRENHEIT;
        double desiredQuantity;
        try {
            desiredQuantity = measurement.convert(desiredUnit);
            System.out.println(Integer.valueOf(originalQuantity).toString() + " " +
                               originalUnit.toString().toLowerCase() +
                               " is " + Double.valueOf(desiredQuantity).toString() + " " +
                               desiredUnit.toString().toLowerCase());
        } catch (InvalidConversionException e) {
            System.out.println("Cannot convert " + originalUnit + " to " + desiredUnit);
        }
    }
}
