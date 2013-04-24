package unitconversion;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class UnitConversionsTest {
    UnitConversions conversions = new UnitConversions();

    @Test
    public void shouldConvertTbspToTsp() throws Exception {
        assertThat(conversions.convert("tbsp.", "tsp."), is(3.0));
    }

    @Test
    public void shouldConvertCupToTsp() throws Exception {
        assertThat(conversions.convert("cup", "tsp."), is(48.0));
    }

    @Test
    public void shouldConvertTbspToCup() throws Exception {
        assertThat(conversions.convert("tbsp.", "cup"), closeTo(0.0625, 0.0001));
    }

    @Test
    public void shouldConvertTspToCup() throws Exception {
        assertThat(conversions.convert("tsp.", "cup"), closeTo(0.020833, 0.000001));
    }
}
