package unitconversion.internal;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import unitconversion.InvalidConversionException;

import java.util.Collection;
import java.util.HashSet;

@AllArgsConstructor
public class UnitConversion {
    @NonNull private UnitConversionGraph graph;

    public double getMultiplier(String originalUnit, String desiredUnit) throws InvalidConversionException {
        double currentMultiplier = 1;
        if (originalUnit.equals(desiredUnit)) {
            return currentMultiplier;
        }
        Collection<String> unitsVisited = new HashSet<String>();
        unitsVisited.add(originalUnit);
        double multiplier = getMultiplier(originalUnit, desiredUnit, currentMultiplier, unitsVisited);
        if (multiplier == 0) {
            throw new InvalidConversionException();
        }
        return multiplier;
    }

    private double getMultiplier(String originalUnit, String desiredUnit,
                                 double currentMultiplier, Collection<String> unitsVisited) {
        double multiplier = 0;
        for (String currentUnit : graph.getNeighbors(originalUnit)) {
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

    private double calculateCurrentMultiplier(double currentMultiplier, String originalUnit, String currentUnit) {
        return currentMultiplier * graph.findEdge(originalUnit, currentUnit).getMultiplier();
    }
}
