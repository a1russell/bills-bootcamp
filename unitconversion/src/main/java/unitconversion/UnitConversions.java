package unitconversion;

import unitconversion.internal.UnitConversion;
import unitconversion.internal.UnitConversionGraph;

public class UnitConversions {
    private final UnitConversionGraph graph = new UnitConversionGraph();

    public UnitConversions() {
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addVertex(Unit.CUP);

        graph.addEdgeAndInverse((double x) -> 3 * x, Unit.TBSP, Unit.TSP);
        graph.addEdgeAndInverse((double x) -> 16 * x, Unit.CUP, Unit.TBSP);

        graph.addVertex(Unit.IN);
        graph.addVertex(Unit.FT);
        graph.addVertex(Unit.YD);

        graph.addEdgeAndInverse((double x) -> 12 * x, Unit.FT, Unit.IN);
        graph.addEdgeAndInverse((double x) -> 3 * x, Unit.YD, Unit.FT);
    }

    public double convert(Unit originalUnit, Unit desiredUnit) throws InvalidConversionException {
        UnitConversion conversion = new UnitConversion(graph);
        return conversion.convert(originalUnit, desiredUnit);
    }
}
