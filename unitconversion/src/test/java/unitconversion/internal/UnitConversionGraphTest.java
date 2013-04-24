package unitconversion.internal;

import org.junit.Before;
import org.junit.Test;
import unitconversion.Unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UnitConversionGraphTest {
    private UnitConversionGraph graph;
    private final double tspPerTbsp = 3;

    @Before
    public void setUp() {
        graph = new UnitConversionGraph();
    }

    @Test
    public void shouldContainEdgeAfterAdding() throws Exception {
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addEdge(tspPerTbsp, Unit.TSP, Unit.TBSP);
        assertThat(graph.findEdge(Unit.TSP, Unit.TBSP).getMultiplier(), is(tspPerTbsp));
    }

    @Test
    public void shouldContainReverseEdgeAfterAdding() throws Exception {
        double tbspPerTsp = 1./3;
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addEdge(tspPerTbsp, Unit.TSP, Unit.TBSP);
        assertThat(graph.findEdge(Unit.TBSP, Unit.TSP).getMultiplier(), is(tbspPerTsp));
    }

    @Test
    public void shouldTreatIntEdgeAsDoubleWhenAddingEdge() throws Exception {
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addEdge(((Double) tspPerTbsp).intValue(), Unit.TSP, Unit.TBSP);
        assertThat(graph.findEdge(Unit.TSP, Unit.TBSP).getMultiplier(), is(tspPerTbsp));
    }
}
