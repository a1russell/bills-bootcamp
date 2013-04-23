package unitconversion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MeasurementTest {
    private UnitConversions conversions = new UnitConversions();
    private Measurement measurement;

    @Test
    public void shouldConvertTwoTbspToSixTsp() throws Exception {
        measurement = new Measurement(conversions, 2, "tbsp.");
        assertEquals((Object) 6.0, measurement.convert("tsp."));
    }
}
