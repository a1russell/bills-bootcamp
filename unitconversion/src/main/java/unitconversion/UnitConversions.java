package unitconversion;

import unitconversion.internal.UnitConversion;
import unitconversion.internal.UnitConversionGraph;

public class UnitConversions {
    private final UnitConversionGraph graph = new UnitConversionGraph();

    public UnitConversions() {
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addVertex(Unit.CUP);

        graph.addEdge(3, Unit.TSP, Unit.TBSP);
        graph.addEdge(16, Unit.TBSP, Unit.CUP);

        graph.addVertex(Unit.IN);
        graph.addVertex(Unit.FT);
        graph.addVertex(Unit.YD);

        graph.addEdge(12, Unit.IN, Unit.FT);
        graph.addEdge(3, Unit.FT, Unit.YD);
    }

    public double convert(Unit originalUnit, Unit desiredUnit) throws InvalidConversionException {
        UnitConversion conversion = new UnitConversion(graph);
        return 1 / conversion.getMultiplier(originalUnit, desiredUnit);
    }
}
