package unitconversion;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import unitconversion.internal.MultiplyingWeightTransformer;
import unitconversion.internal.UnitConversionGraph;

public class UnitConversions {
    UnitConversionGraph graph = new UnitConversionGraph();

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

    public double convert(String originalUnit, String desiredUnit) {
        MultiplyingWeightTransformer transformer = new MultiplyingWeightTransformer();
        DijkstraShortestPath<String, Double> path = new DijkstraShortestPath<String, Double>(graph, transformer);
        return path.getDistance(originalUnit, desiredUnit).doubleValue();
    }
}
