package unitconversion.internal;

import org.apache.commons.collections15.Transformer;

public class MultiplyingWeightTransformer implements Transformer<Double, Double> {
    private double multiplier = 1;
    private double previousResultsTotal = 0;

    @Override
    public Double transform(Double value) {
        double result = value * multiplier - previousResultsTotal;
        previousResultsTotal += result;
        multiplier *= value;
        return result;
    }
}
