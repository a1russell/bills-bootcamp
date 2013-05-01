package unitconversion.internal;

import com.google.common.base.Function;
import org.junit.Before;
import org.junit.Test;
import unitconversion.InvalidConversionException;
import unitconversion.Unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class UnitConversionTest {
    private UnitConversion unitConversion;

    @Before
    public void setUp() {
        UnitConversionGraph graph = new UnitConversionGraph();

        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addVertex(Unit.FL_OZ);
        graph.addVertex(Unit.CUP);

        graph.addEdgeAndInverse((Double x) -> 3 * x, Unit.TBSP, Unit.TSP);
        graph.addEdgeAndInverse((Double x) -> 2 * x, Unit.FL_OZ, Unit.TBSP);
        graph.addEdgeAndInverse((Double x) -> 8 * x, Unit.CUP, Unit.FL_OZ);

        unitConversion = new UnitConversion(graph);
    }

    @Test
    public void shouldReturnOneTspPerTsp() throws Exception {
        Function<Double, Double> conversionFn = unitConversion.convert(Unit.TSP, Unit.TSP);
        assertThat(conversionFn.apply((double) 1), is(1.0));
    }

    @Test
    public void shouldReturnThreeTspPerTbsp() throws Exception {
        Function<Double, Double> conversionFn = unitConversion.convert(Unit.TBSP, Unit.TSP);
        assertThat(conversionFn.apply((double) 1), is(3.0));
    }

    @Test
    public void shouldReturnFortyEightTspPerCup() throws Exception {
        Function<Double, Double> conversionFn = unitConversion.convert(Unit.CUP, Unit.TSP);
        assertThat(conversionFn.apply((double) 1), is(48.0));
    }

    @Test
    public void shouldReturnTwoTbspPerCup() throws Exception {
        Function<Double, Double> conversionFn = unitConversion.convert(Unit.CUP, Unit.TBSP);
        assertThat(conversionFn.apply((double) 1), is(16.0));
    }

    @Test
    public void shouldReturnOneSixthFlOzPerTsp() throws Exception {
        Function<Double, Double> conversionFn = unitConversion.convert(Unit.TSP, Unit.FL_OZ);
        assertThat(conversionFn.apply((double) 1), closeTo(0.1667, 0.0001));
    }

    @Test(expected=InvalidConversionException.class)
    public void shouldThrowExceptionWhenNoPathExistsBetweenRequestedVertices() throws Exception {
        unitConversion.convert(Unit.TSP, Unit.FT);
    }
}
