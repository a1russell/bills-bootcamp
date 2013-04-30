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

        graph.addEdgeAndInverse((double x) -> 3 * x, Unit.TSP, Unit.TBSP);
        graph.addEdgeAndInverse((double x) -> 2 * x, Unit.TBSP, Unit.FL_OZ);
        graph.addEdgeAndInverse((double x) -> 8 * x, Unit.FL_OZ, Unit.CUP);

        conversion = new UnitConversion(graph);
    }

    @Test
    public void shouldReturnOneTspPerTsp() throws Exception {
        double multiplier = conversion.getMultiplier(Unit.TSP, Unit.TSP);
        assertThat(multiplier, is(1.0));
    }

    @Test
    public void shouldReturnThreeTspPerTbsp() throws Exception {
        double multiplier = conversion.getMultiplier(Unit.TSP, Unit.TBSP);
        assertThat(multiplier, is(3.0));
    }

    @Test
    public void shouldReturnFortyEightTspPerCup() throws Exception {
        double multiplier = conversion.getMultiplier(Unit.TSP, Unit.CUP);
        assertThat(multiplier, is(48.0));
    }

    @Test
    public void shouldReturnTwoTbspPerCup() throws Exception {
        double multiplier = conversion.getMultiplier(Unit.TBSP, Unit.CUP);
        assertThat(multiplier, is(16.0));
    }

    @Test
    public void shouldReturnOneSixthFlOzPerTsp() throws Exception {
        double multiplier = conversion.getMultiplier(Unit.FL_OZ, Unit.TSP);
        assertThat(multiplier, closeTo(0.1667, 0.0001));
    }

    @Test(expected=InvalidConversionException.class)
    public void shouldThrowExceptionWhenNoPathExistsBetweenRequestedVertices() throws Exception {
        conversion.getMultiplier(Unit.TSP, Unit.FT);
    }
}
