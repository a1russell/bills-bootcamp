package unitconversion.internal;

import com.google.common.base.Function;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import unitconversion.Unit;

public class UnitConversionGraph extends DirectedSparseMultigraph<Unit, Conversion> {
    private int edgeCount = 0;

    public boolean addEdgeAndInverse(Function<Double, Double> edge, Unit vertex1, Unit vertex2) {
        return super.addEdge(new Conversion(++edgeCount, edge), vertex1, vertex2) &&
               super.addEdge(new Conversion(++edgeCount,
                                            (Double x) -> x / edge.apply((double) 1)),
                             vertex2, vertex1);
    }
}
