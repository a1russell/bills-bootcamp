package unitconversion.internal;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import unitconversion.Unit;

public class UnitConversionGraph extends DirectedSparseMultigraph<Unit, UnitMultiplier> {
    private int edgeCount = 0;

    public boolean addEdge(Double e, Unit v1, Unit v2) {
        return super.addEdge(new UnitMultiplier(++edgeCount, e), v1, v2) &&
               super.addEdge(new UnitMultiplier(++edgeCount, 1 / e), v2, v1);
    }

    public boolean addEdge(Integer e, Unit v1, Unit v2) {
        return addEdge(e.doubleValue(), v1, v2);
    }
}
