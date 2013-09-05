package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.Collection;

import static com.google.common.collect.Sets.newHashSet;

public class CompositeMeasurement implements TextRepresentable {
    private final ElementToCollectionAdder<SingleMeasurement, String, Double> adder;
    private final TextJoiner textJoiner;
    Collection<SingleMeasurement> measurements;

    @Inject
    SingleMeasurementFactory singleMeasurementFactory;

    @Inject
    CompositeMeasurement(SingleMeasurementFactory singleMeasurementFactory,
                         @Assisted double quantity, @Assisted String unit) {
        this.adder = new ElementToCollectionAdder<SingleMeasurement, String, Double>();
        this.textJoiner = new TextJoiner();
        this.singleMeasurementFactory = singleMeasurementFactory;
        this.measurements = newHashSet();
        add(this.singleMeasurementFactory.create(quantity, unit));
    }

    @Override
    public String getText() {
        return new TextJoiner().join(measurements, ", ");
    }

    public void add(SingleMeasurement singleMeasurement) {
        adder.add(singleMeasurement, measurements);
    }

    public void add(CompositeMeasurement that) {
        for (SingleMeasurement measurement : that.measurements) {
            add(measurement);
        }
    }
}
