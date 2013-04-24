package unitconversion;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class MeasurementTest {
    private final UnitConversions conversions = new UnitConversions();
    private Measurement measurement;

    @Test
    public void shouldConvertTwoTbspToSixTsp() throws Exception {
        measurement = new Measurement(conversions, 2, Unit.TBSP);
        assertThat(measurement.convert(Unit.TSP), is(6.0));
    }

    @Test
    public void shouldConvertTwoFeetToTwoThirdsYard() throws Exception {
        measurement = new Measurement(conversions, 2, Unit.FT);
        assertThat(measurement.convert(Unit.YD), closeTo(0.667, 0.001));
    }
}
