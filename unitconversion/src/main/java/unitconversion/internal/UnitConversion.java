package unitconversion.internal;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import unitconversion.InvalidConversionException;
import unitconversion.Unit;

import java.util.Collection;
import java.util.HashSet;

@AllArgsConstructor
public class UnitConversion {
    @NonNull private UnitConversionGraph graph;

    public double getMultiplier(Unit originalUnit, Unit desiredUnit) throws InvalidConversionException {
        double currentMultiplier = 1;
        if (originalUnit.equals(desiredUnit)) {
            return currentMultiplier;
        }
        Collection<Unit> unitsVisited = new HashSet<Unit>();
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
