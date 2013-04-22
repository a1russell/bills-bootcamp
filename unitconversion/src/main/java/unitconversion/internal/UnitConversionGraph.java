package unitconversion.internal;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;

public class UnitConversionGraph extends DirectedSparseMultigraph<String, Double> {
    public boolean addEdge(Double e, String v1, String v2) {
        return super.addEdge(e, v1, v2) && super.addEdge(1 / e, v2, v1);
    }

    public boolean addEdge(Integer e, String v1, String v2) {
        return addEdge(e.doubleValue(), v1, v2);
    }
}
