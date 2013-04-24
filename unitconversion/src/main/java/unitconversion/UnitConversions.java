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

        String in = "in.";
        String ft = "ft.";
        String yd = "yd.";

        graph.addVertex(in);
        graph.addVertex(ft);
        graph.addVertex(yd);

        graph.addEdge(12, in, ft);
        graph.addEdge(3, ft, yd);
    }

    public double convert(String originalUnit, String desiredUnit) throws InvalidConversionException {
        UnitConversion conversion = new UnitConversion(graph);
        return 1 / conversion.getMultiplier(originalUnit, desiredUnit);
    }
}
