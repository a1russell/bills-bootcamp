package unitconversion;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Collections;

public class MixedMeasurement implements MeasurementConverter {
    private Multiset<Measurement> measurements;

    public MixedMeasurement(Measurement ... measurements) {
        this.measurements = HashMultiset.create();
        Collections.addAll(this.measurements, measurements);
    }

    @Override
    public double convert(Unit desiredUnit) throws InvalidConversionException {
        double total = 0;
        for (Measurement measurement : measurements) {
            total += measurement.convert(desiredUnit);
        }
        return total;
    }
}
