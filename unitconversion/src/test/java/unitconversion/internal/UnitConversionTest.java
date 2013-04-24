package unitconversion.internal;

import org.junit.Before;
import org.junit.Test;
import unitconversion.InvalidConversionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class UnitConversionTest {
    private UnitConversionGraph graph;
    private UnitConversion conversion;
    private String tsp = "tsp.";
    private String tbsp = "tbsp.";
    private String oz = "fl oz";
    private String cup = "cup";

    @Before
    public void setUp() {
        graph = new UnitConversionGraph();

        graph.addVertex(tsp);
        graph.addVertex(tbsp);
        graph.addVertex(oz);
        graph.addVertex(cup);

        graph.addEdge(3, tsp, tbsp);
        graph.addEdge(2, tbsp, oz);
        graph.addEdge(8, oz, cup);

        conversion = new UnitConversion(graph);
    }

    @Test
    public void shouldReturnOneTspPerTsp() throws Exception {
        double multiplier = conversion.getMultiplier(tsp, tsp);
        assertThat(multiplier, is(1.0));
    }

    @Test
    public void shouldReturnThreeTspPerTbsp() throws Exception {
        double multiplier = conversion.getMultiplier(tsp, tbsp);
        assertThat(multiplier, is(3.0));
    }

    @Test
    public void shouldReturnFortyEightTspPerCup() throws Exception {
        double multiplier = conversion.getMultiplier(tsp, cup);
        assertThat(multiplier, is(48.0));
    }

    @Test
    public void shouldReturnTwoTbspPerCup() throws Exception {
        double multiplier = conversion.getMultiplier(tbsp, cup);
        assertThat(multiplier, is(16.0));
    }

    @Test
    public void shouldReturnOneSixthFlOzPerTsp() throws Exception {
        double multiplier = conversion.getMultiplier(oz, tsp);
        assertThat(multiplier, closeTo(0.1667, 0.0001));
    }

    @Test(expected=InvalidConversionException.class)
    public void shouldThrowExceptionWhenNoPathExistsBetweenRequestedVertices() throws Exception {
        conversion.getMultiplier(tsp, "foot");
    }
}
