package unitconversion.internal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitConversionGraphTest {
    private UnitConversionGraph graph;
    private String tsp = "tsp.";
    private String tbsp = "tbsp.";
    private double tspPerTbsp = 3;
    private double tbspPerTsp = 1./3;

    @Before
    public void setUp() {
        graph = new UnitConversionGraph();
    }

    @Test
    public void shouldContainEdgeAfterAdding() throws Exception {
        graph.addVertex(tsp);
        graph.addVertex(tbsp);
        graph.addEdge(tspPerTbsp, tsp, tbsp);
        assertEquals((Object) tspPerTbsp, graph.findEdge(tsp, tbsp));
    }

    @Test
    public void shouldContainReverseEdgeAfterAdding() throws Exception {
        graph.addVertex(tsp);
        graph.addVertex(tbsp);
        graph.addEdge(tspPerTbsp, tsp, tbsp);
        assertEquals((Object) (tbspPerTsp), graph.findEdge(tbsp, tsp));
    }

    @Test
    public void shouldTreatIntEdgeAsDoubleWhenAddingEdge() throws Exception {
        graph.addVertex(tsp);
        graph.addVertex(tbsp);
        graph.addEdge(((Double) tspPerTbsp).intValue(), tsp, tbsp);
        assertEquals((Object) tspPerTbsp, graph.findEdge(tsp, tbsp));
    }
}
