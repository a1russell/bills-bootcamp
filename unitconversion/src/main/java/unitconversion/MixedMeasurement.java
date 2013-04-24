package unitconversion;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Collections;

public class MixedMeasurement implements MeasurementConverter {
    private Multiset<MeasurementConverter> measurements;

    public MixedMeasurement(MeasurementConverter ... measurements) {
        this.measurements = HashMultiset.create();
        Collections.addAll(this.measurements, measurements);
    }

    @Override
    public double convert(Unit desiredUnit) throws InvalidConversionException {
        double total = 0;
        for (MeasurementConverter measurement : measurements) {
            total += measurement.convert(desiredUnit);
        }
        return total;
    }
}
