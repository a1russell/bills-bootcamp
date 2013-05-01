package unitconversion;

import com.google.common.base.Function;
import unitconversion.internal.UnitConversion;
import unitconversion.internal.UnitConversionGraph;

public class UnitConversions {
    private final UnitConversionGraph graph = new UnitConversionGraph();

    public UnitConversions() {
        addEnglishVolumeUnitsToGraph();
        addEnglishLengthUnitsToGraph();
        addTemperatureUnitsToGraph();
    }

    public Function<Double, Double> convert(Unit originalUnit, Unit desiredUnit) throws InvalidConversionException {
        UnitConversion conversion = new UnitConversion(graph);
        return conversion.convert(originalUnit, desiredUnit);
    }

    private void addEnglishVolumeUnitsToGraph() {
        graph.addVertex(Unit.TSP);
        graph.addVertex(Unit.TBSP);
        graph.addVertex(Unit.CUP);

        graph.addEdgeAndInverse((Double x) -> 3 * x, Unit.TBSP, Unit.TSP);
        graph.addEdgeAndInverse((Double x) -> 16 * x, Unit.CUP, Unit.TBSP);
    }

    private void addEnglishLengthUnitsToGraph() {
        graph.addVertex(Unit.IN);
        graph.addVertex(Unit.FT);
        graph.addVertex(Unit.YD);

        graph.addEdgeAndInverse((Double x) -> 12 * x, Unit.FT, Unit.IN);
        graph.addEdgeAndInverse((Double x) -> 3 * x, Unit.YD, Unit.FT);
    }

    private void addTemperatureUnitsToGraph() {
        graph.addVertex(Unit.CELSIUS);
        graph.addVertex(Unit.FAHRENHEIT);

        graph.addEdge((Double x) -> (5./9) * (x - 32), Unit.FAHRENHEIT, Unit.CELSIUS);
        graph.addEdge((Double x) -> (9./5) * x + 32, Unit.CELSIUS, Unit.FAHRENHEIT);
    }
}
