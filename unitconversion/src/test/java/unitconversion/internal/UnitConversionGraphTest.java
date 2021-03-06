package unitconversion.internal;

import com.google.common.base.Function;
import org.junit.Before;
import org.junit.Test;
import unitconversion.Unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UnitConversionGraphTest {
    private UnitConversionGraph graph;
    private final Function<Double, Double> tspPerTbspFunction = (Double x) -> 3 * x;

    @Before
    public void setUp() {
        graph = new UnitConversionGraph();
    }

    @Test
    public void shouldContainEdgeAfterAddingEdge() throws Exception {
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addEdge(tspPerTbspFunction, Unit.TBSP, Unit.TSP);
        assertThat(graph.findEdge(Unit.TBSP, Unit.TSP).getConversion(), is(tspPerTbspFunction));
    }

    @Test
    public void shouldContainEdgeAfterAddingEdgeAndInverse() throws Exception {
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addEdgeAndInverse(tspPerTbspFunction, Unit.TBSP, Unit.TSP);
        assertThat(graph.findEdge(Unit.TBSP, Unit.TSP).getConversion(), is(tspPerTbspFunction));
    }

    @Test
     public void shouldContainInverseEdgeAfterAddingEdgeWhenConversionOperandIsOne() throws Exception {
        double tbspPerTsp = 1./3;
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addEdgeAndInverse(tspPerTbspFunction, Unit.TBSP, Unit.TSP);
        assertThat(graph.findEdge(Unit.TSP, Unit.TBSP).getConversion().apply(1.), is(tbspPerTsp));
    }

    @Test
    public void shouldContainInverseEdgeAfterAddingEdgeWhenConversionOperandIsTwo() throws Exception {
        double tbspPerTwoTsp = 2./3;
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addEdgeAndInverse(tspPerTbspFunction, Unit.TBSP, Unit.TSP);
        assertThat(graph.findEdge(Unit.TSP, Unit.TBSP).getConversion().apply(2.), is(tbspPerTwoTsp));
    }
}
