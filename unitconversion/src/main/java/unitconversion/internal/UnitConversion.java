package unitconversion.internal;

import unitconversion.InvalidConversionException;
import unitconversion.Unit;

import java.util.Collection;

import static com.google.common.collect.Sets.newHashSet;

public class UnitConversion {
    private UnitConversionGraph graph;

    public UnitConversion(UnitConversionGraph graph) {
        if (graph == null) throw new NullPointerException("graph");
        this.graph = graph;
    }

    public double getMultiplier(Unit originalUnit, Unit desiredUnit) throws InvalidConversionException {
        double currentMultiplier = 1;
        if (originalUnit.equals(desiredUnit)) {
            return currentMultiplier;
        }
        Collection<Unit> unitsVisited = newHashSet();
        unitsVisited.add(originalUnit);
        double multiplier = getMultiplier(originalUnit, desiredUnit, currentMultiplier, unitsVisited);
        if (multiplier == 0) {
            throw new InvalidConversionException();
        }
        return multiplier;
    }

    private double getMultiplier(Unit originalUnit, Unit desiredUnit,
                                 double currentMultiplier, Collection<Unit> unitsVisited) {
        double multiplier = 0;
        for (Unit currentUnit : graph.getNeighbors(originalUnit)) {
            if (unitsVisited.contains(currentUnit)) {
                continue;
            }
            if (currentUnit.equals(desiredUnit)) {
                return calculateCurrentMultiplier(currentMultiplier, originalUnit, currentUnit);
            }
            unitsVisited.add(currentUnit);
            multiplier = getMultiplier(currentUnit, desiredUnit,
                                       calculateCurrentMultiplier(currentMultiplier, originalUnit, currentUnit),
                                       unitsVisited);
            if (multiplier != 0) {
                break;
            }
        }
        return multiplier;
    }

    private double calculateCurrentMultiplier(double currentMultiplier, Unit originalUnit, Unit currentUnit) {
        return currentMultiplier * graph.findEdge(originalUnit, currentUnit).getMultiplier();
    }
}
