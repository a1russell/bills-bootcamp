package shoppinglist;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

public class CompositeMeasurement implements TextRepresentable {
    Collection<SingleMeasurement> measurements;

    @Inject
    SingleMeasurementFactory singleMeasurementFactory;

    @Inject
    CompositeMeasurement(SingleMeasurementFactory singleMeasurementFactory,
                         @Assisted double quantity, @Assisted Unit unit) {
        this.singleMeasurementFactory = singleMeasurementFactory;
        this.measurements = newArrayList();
        add(quantity, unit);
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

    public void add(double quantity, Unit unit) {
        SingleMeasurement measurement = singleMeasurementFactory.create(quantity, unit);
        Optional<SingleMeasurement> existingMeasurement = findMeasurementWithUnit(unit);
        if (existingMeasurement.isPresent()) {
            existingMeasurement.get().add(quantity);
        } else {
            measurements.add(measurement);
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
