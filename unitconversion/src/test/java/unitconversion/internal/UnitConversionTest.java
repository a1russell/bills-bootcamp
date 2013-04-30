package unitconversion.internal;

import org.junit.Before;
import org.junit.Test;
import unitconversion.InvalidConversionException;
import unitconversion.Unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class UnitConversionTest {
    private UnitConversion conversion;

    @Before
    public void setUp() {
        UnitConversionGraph graph = new UnitConversionGraph();

        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addVertex(Unit.FL_OZ);
        graph.addVertex(Unit.CUP);

        graph.addEdgeAndInverse((double x) -> 3 * x, Unit.TBSP, Unit.TSP);
        graph.addEdgeAndInverse((double x) -> 2 * x, Unit.FL_OZ, Unit.TBSP);
        graph.addEdgeAndInverse((double x) -> 8 * x, Unit.CUP, Unit.FL_OZ);

        conversion = new UnitConversion(graph);
    }

    @Test
    public void shouldReturnOneTspPerTsp() throws Exception {
        double multiplier = conversion.convert(Unit.TSP, Unit.TSP);
        assertThat(multiplier, is(1.0));
    }

    @Test
    public void shouldReturnThreeTspPerTbsp() throws Exception {
        double multiplier = conversion.convert(Unit.TBSP, Unit.TSP);
        assertThat(multiplier, is(3.0));
    }

    @Test
    public void shouldReturnFortyEightTspPerCup() throws Exception {
        double multiplier = conversion.convert(Unit.CUP, Unit.TSP);
        assertThat(multiplier, is(48.0));
    }

    @Test
    public void shouldReturnTwoTbspPerCup() throws Exception {
        double multiplier = conversion.convert(Unit.CUP, Unit.TBSP);
        assertThat(multiplier, is(16.0));
    }

    @Test
    public void shouldReturnOneSixthFlOzPerTsp() throws Exception {
        double multiplier = conversion.convert(Unit.TSP, Unit.FL_OZ);
        assertThat(multiplier, closeTo(0.1667, 0.0001));
    }

    @Test(expected=InvalidConversionException.class)
    public void shouldThrowExceptionWhenNoPathExistsBetweenRequestedVertices() throws Exception {
        conversion.convert(Unit.TSP, Unit.FT);
    }
}
