package unitconversion;

public class Main {

    public static void main(String[] argv) {
        UnitConversions conversions = new UnitConversions();
        int originalQuantity = 3;
        String originalUnit = "tsp.";
        Measurement measurement = new Measurement(conversions, originalQuantity, originalUnit);
        String desiredUnit = "tbsp.";
        double desiredQuantity = 0;
        try {
            desiredQuantity = measurement.convert(desiredUnit);
            System.out.println(Integer.valueOf(originalQuantity).toString() + " " + originalUnit +
                               " is " + Double.valueOf(desiredQuantity).toString() + " " + desiredUnit);
        } catch (InvalidConversionException e) {
            System.out.println("Cannot convert " + originalUnit + " to " + desiredUnit);
        }
    }
}
