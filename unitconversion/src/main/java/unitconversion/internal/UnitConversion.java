package unitconversion.internal;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashSet;

@AllArgsConstructor
public class UnitConversion {
    @NonNull private UnitConversionGraph graph;

    public double getMultiplier(String originalUnit, String desiredUnit) {
        double currentMultiplier = 1;
        if (originalUnit.equals(desiredUnit)) {
            return currentMultiplier;
        }
        Collection<String> unitsVisited = new HashSet<String>();
        unitsVisited.add(originalUnit);
        return getMultiplier(originalUnit, desiredUnit, currentMultiplier, unitsVisited);
    }

    private double getMultiplier(String originalUnit, String desiredUnit,
                                 double currentMultiplier, Collection<String> unitsVisited) {
        String unitToTraverse = originalUnit;
        for (String currentUnit : graph.getNeighbors(originalUnit)) {
            if (unitsVisited.contains(currentUnit)) {
                continue;
            }
            if (currentUnit.equals(desiredUnit)) {
                return calculateCurrentMultiplier(currentMultiplier, originalUnit, currentUnit);
            }
            unitsVisited.add(currentUnit);
            unitToTraverse = currentUnit;
        }
        return getMultiplier(unitToTraverse, desiredUnit,
                             calculateCurrentMultiplier(currentMultiplier, originalUnit, unitToTraverse),
                             unitsVisited);
    }

    private double calculateCurrentMultiplier(double currentMultiplier, String originalUnit, String currentUnit) {
        return currentMultiplier * graph.findEdge(originalUnit, currentUnit);
    }
}
