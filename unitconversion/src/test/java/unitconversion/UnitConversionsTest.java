package unitconversion;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class UnitConversionsTest {
    private final UnitConversions conversions = new UnitConversions();

    @Test
    public void shouldConvertTbspToTsp() throws Exception {
        assertThat(conversions.convert(Unit.TBSP, Unit.TSP).apply((double) 1), is(3.0));
    }

    @Test
    public void shouldConvertCupToTsp() throws Exception {
        assertThat(conversions.convert(Unit.CUP, Unit.TSP).apply((double) 1), is(48.0));
    }

    @Test
    public void shouldConvertTbspToCup() throws Exception {
        assertThat(conversions.convert(Unit.TBSP, Unit.CUP).apply((double) 1), closeTo(0.0625, 0.0001));
    }

    @Test
    public void shouldConvertTspToCup() throws Exception {
        assertThat(conversions.convert(Unit.TSP, Unit.CUP).apply((double) 1), closeTo(0.020833, 0.000001));
    }
}
