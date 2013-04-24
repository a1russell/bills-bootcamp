package unitconversion;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class MixedMeasurementTest {
    private final UnitConversions conversions = new UnitConversions();
    MixedMeasurement mixedMeasurement;

    @Test
    public void shouldConvertOneCupAndTwoTspToSixteenAndTwoThirdsTbsp() throws Exception {
        mixedMeasurement = new MixedMeasurement(new Measurement(conversions, 1, Unit.CUP),
                                                new Measurement(conversions, 2, Unit.TSP));
        assertThat(mixedMeasurement.convert(Unit.TBSP), closeTo(16.667, 0.001));
    }

    @Test(expected=InvalidConversionException.class)
    public void shouldThrowExceptionWhenConvertingMixedIncompatibleUnits() throws Exception {
        mixedMeasurement = new MixedMeasurement(new Measurement(conversions, 1, Unit.CUP),
                                                new Measurement(conversions, 1, Unit.YD));
        mixedMeasurement.convert(Unit.TBSP);
    }
}
