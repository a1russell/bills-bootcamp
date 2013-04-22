package unitconversion.internal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiplyingWeightTransformerTest {
    MultiplyingWeightTransformer transformer;

    @Before
    public void setUp() throws Exception {
        transformer = new MultiplyingWeightTransformer();
    }

    @Test
    public void shouldReturnOriginalValueOnFirstTransformation() throws Exception {
        double value = 2.0;
        assertEquals((Object) value, transformer.transform(value));
    }

    @Test
    public void shouldReturnAppropriateValueOnSecondTransformation() throws Exception {
        transformer.transform(2.0);
        double result = transformer.transform(3.0);
        assertEquals((Object) 4.0, result);
    }

    @Test
    public void shouldReturnAppropriateOnThirdTransformation() throws Exception {
        transformer.transform(2.0);
        transformer.transform(3.0);
        double result = transformer.transform(7.0);
        assertEquals((Object) 36.0, result);
    }

    @Test
    public void shouldReturnAppropriateOnFourthTransformation() throws Exception {
        transformer.transform(2.0);
        transformer.transform(3.0);
        transformer.transform(7.0);
        double result = transformer.transform(5.0);
        assertEquals((Object) 168.0, result);
    }
}
