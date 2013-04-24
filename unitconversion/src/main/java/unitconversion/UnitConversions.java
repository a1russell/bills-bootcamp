package unitconversion;

import unitconversion.internal.UnitConversion;
import unitconversion.internal.UnitConversionGraph;

public class UnitConversions {
    private UnitConversionGraph graph = new UnitConversionGraph();

    public UnitConversions() {
        String tsp = "tsp.";
        String tbsp = "tbsp.";
        String cup = "cup";

        graph.addVertex(tsp);
        graph.addVertex(tbsp);
        graph.addVertex(cup);

        graph.addEdge(3, tsp, tbsp);
        graph.addEdge(16, tbsp, cup);
    }

    public double convert(String originalUnit, String desiredUnit) throws InvalidConversionException {
        UnitConversion conversion = new UnitConversion(graph);
        return 1 / conversion.getMultiplier(originalUnit, desiredUnit);
    }
}
