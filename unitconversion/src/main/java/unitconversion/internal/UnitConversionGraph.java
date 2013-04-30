package unitconversion.internal;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import unitconversion.Unit;

import java.util.function.DoubleUnaryOperator;

public class UnitConversionGraph extends DirectedSparseMultigraph<Unit, Conversion> {
    private int edgeCount = 0;

    public boolean addEdgeAndInverse(DoubleUnaryOperator edge, Unit vertex1, Unit vertex2) {
        return super.addEdge(new Conversion(++edgeCount, edge), vertex1, vertex2) &&
               super.addEdge(new Conversion(++edgeCount,
                                                (double x) -> x / edge.applyAsDouble(1)),
                             vertex2, vertex1);
    }
}
