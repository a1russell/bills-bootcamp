package unitconversion;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class MeasurementTest {
    private UnitConversions conversions = new UnitConversions();
    private Measurement measurement;

    @Test
    public void shouldConvertTwoTbspToSixTsp() throws Exception {
        measurement = new Measurement(conversions, 2, "tbsp.");
        assertThat(measurement.convert("tsp."), is(6.0));
    }

    @Test
    public void shouldConvertTwoFeetToTwoThirdsYard() throws Exception {
        measurement = new Measurement(conversions, 2, "ft.");
        assertThat(measurement.convert("yd."), closeTo(0.667, 0.001));
    }
}
