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
                         @Assisted double quantity, @Assisted Unit unit) {
        this.singleMeasurementFactory = singleMeasurementFactory;
        this.measurements = newHashSet();
        addSingleMeasurement(this.singleMeasurementFactory.create(quantity, unit));
    }

    @Override
    public String getText() {
        String text = "";
        String separator = ", ";
        for (SingleMeasurement measurement : measurements) {
            text += measurement.getText() + separator;
        }
        return text.substring(0, text.length() - separator.length());
    }

    public void addSingleMeasurement(SingleMeasurement singleMeasurement) {
        Optional<SingleMeasurement> existingMeasurement = findMeasurementWithUnit(singleMeasurement.getUnit());
        if (existingMeasurement.isPresent()) {
            existingMeasurement.get().addSingleMeasurement(singleMeasurement);
        } else {
            measurements.add(singleMeasurement);
        }
    }

    public void addCompositeMeasurement(CompositeMeasurement that) {
        for (SingleMeasurement measurement : that.measurements) {
            addSingleMeasurement(measurement);
        }
    }

    private Optional<SingleMeasurement> findMeasurementWithUnit(Unit unit) {
        for (SingleMeasurement measurement : measurements) {
            if (unit == measurement.getUnit()) {
                return Optional.of(measurement);
            }
        }
        return Optional.absent();
    }
}
