package shoppinglist;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.Collection;

import static com.google.common.collect.Sets.newHashSet;

public class CompositeMeasurement implements TextRepresentable {
    Collection<SingleMeasurement> measurements;

    @Inject
    SingleMeasurementFactory singleMeasurementFactory;

    @Inject
    CompositeMeasurement(SingleMeasurementFactory singleMeasurementFactory,
                         @Assisted double quantity, @Assisted String unit) {
        this.singleMeasurementFactory = singleMeasurementFactory;
        this.measurements = newHashSet();
        add(this.singleMeasurementFactory.create(quantity, unit));
    }

    @Override
    public String getText() {
        String text = "";
        String separator = ", ";
        for (TextRepresentable measurement : measurements) {
            text += measurement.getText() + separator;
        }
        return text.substring(0, text.length() - separator.length());
    }

    public void add(SingleMeasurement singleMeasurement) {
        Optional<SingleMeasurement> existingMeasurement = findMeasurementWithUnit(singleMeasurement.getUnit());
        if (existingMeasurement.isPresent()) {
            existingMeasurement.get().add(singleMeasurement);
        } else {
            measurements.add(singleMeasurement);
        }
    }

    public void add(CompositeMeasurement that) {
        for (SingleMeasurement measurement : that.measurements) {
            add(measurement);
        }
    }

    private Optional<SingleMeasurement> findMeasurementWithUnit(String unit) {
        for (SingleMeasurement measurement : measurements) {
            if (unit.equals(measurement.getUnit())) {
                return Optional.of(measurement);
            }
        }
        return Optional.absent();
    }
}
