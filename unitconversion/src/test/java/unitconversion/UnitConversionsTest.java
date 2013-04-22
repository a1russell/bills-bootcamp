package unitconversion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitConversionsTest {
    UnitConversions conversions = new UnitConversions();

    @Test
    public void shouldConvertTspToTbsp() {
        assertEquals((Object) 3.0, conversions.convert("tsp.", "tbsp."));
    }

    @Test
    public void shouldConvertTspToCup() {
        assertEquals((Object) 48.0, conversions.convert("tsp.", "cup"));
    }
}
