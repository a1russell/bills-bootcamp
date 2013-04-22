package unitconversion.internal;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import unitconversion.Unit;

public class UnitConversionGraph extends DirectedSparseMultigraph<Unit, Double> {
    public boolean addEdge(Double e, Unit v1, Unit v2) {
        return super.addEdge(e, v1, v2) && super.addEdge(1 / e, v2, v1);
    }
}
