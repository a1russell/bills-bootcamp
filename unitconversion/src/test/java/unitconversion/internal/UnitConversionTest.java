package unitconversion.internal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals((Object) 1.0, multiplier);
    }
}
