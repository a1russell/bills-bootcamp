package unitconversion.internal;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import unitconversion.InvalidConversionException;
import unitconversion.Unit;

import java.util.Collection;

import static com.google.common.collect.Sets.newHashSet;

@SuppressWarnings("ConstantConditions")
public class UnitConversion {
    private UnitConversionGraph graph;

    public UnitConversion(UnitConversionGraph graph) {
        if (graph == null) throw new NullPointerException("graph");
        this.graph = graph;
    }

    public Function<Double, Double> convert(Unit originalUnit, Unit desiredUnit) throws InvalidConversionException {
        Function<Double, Double> currentConversion = (Double x) -> x;
        if (originalUnit.equals(desiredUnit)) {
            return currentConversion;
        }
        Collection<Unit> unitsVisited = newHashSet();
        unitsVisited.add(originalUnit);
        Function<Double, Double> conversion = convert(originalUnit, desiredUnit, currentConversion, unitsVisited);
        if (conversion.apply((double) 1) == 0) {
            throw new InvalidConversionException();
        }
        return conversion;
    }

    private Function<Double, Double> convert(Unit originalUnit, Unit desiredUnit,
                                             Function<Double, Double> currentConversion, Collection<Unit> unitsVisited) {
        Function<Double, Double> conversion = (Double x) -> (double) 0;
        for (Unit currentUnit : graph.getNeighbors(originalUnit)) {
            if (unitsVisited.contains(currentUnit)) {
                continue;
            }
            if (currentUnit.equals(desiredUnit)) {
                return calculateCurrentConversion(currentConversion, originalUnit, currentUnit);
            }
            unitsVisited.add(currentUnit);
            conversion = convert(currentUnit, desiredUnit,
                                 calculateCurrentConversion(currentConversion, originalUnit, currentUnit),
                                 unitsVisited);
            if (conversion.apply((double) 1) != 0) {
                break;
            }
        }
        return conversion;
    }

    private Function<Double, Double> calculateCurrentConversion(Function<Double, Double> currentConversion,
                                                                Unit originalUnit, Unit currentUnit) {
        return Functions.compose(graph.findEdge(originalUnit, currentUnit).getConversion(), currentConversion);
    }
}
