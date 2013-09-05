package shoppinglist;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.Collection;

import static com.google.common.collect.Sets.newHashSet;

public class CompositeMeasurement implements TextRepresentable {
    @VisibleForTesting
    Collection<SingleMeasurement> measurements;

    private final ElementToCollectionAdder<SingleMeasurement, String, Double> elementToCollectionAdder;
    private final TextJoiner textJoiner;

    public interface Factory {
        CompositeMeasurement create(double quantity, String unit);
    }

    @Inject
    private CompositeMeasurement(SingleMeasurement.Factory singleMeasurementFactory,
                                 ElementToCollectionAdder<SingleMeasurement, String, Double> elementToCollectionAdder,
                                 TextJoiner textJoiner,
                                 @Assisted double quantity,
                                 @Assisted String unit) {
        this.elementToCollectionAdder = elementToCollectionAdder;
        this.textJoiner = textJoiner;
        this.measurements = newHashSet();
        add(singleMeasurementFactory.create(quantity, unit));
    }

    @Override
    public String getText() {
        return textJoiner.join(measurements, ", ");
    }

    public void add(SingleMeasurement singleMeasurement) {
        elementToCollectionAdder.add(singleMeasurement, measurements);
    }

    public void add(CompositeMeasurement that) {
        for (SingleMeasurement measurement : that.measurements) {
            add(measurement);
        }
    }
}
