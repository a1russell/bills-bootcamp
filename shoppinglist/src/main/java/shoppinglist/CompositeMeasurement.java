package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.ArrayList;
import java.util.Collection;

public class CompositeMeasurement implements Measurement {
    Collection<Measurement> measurements;

    @Inject
    CompositeMeasurement(@Assisted double quantity, @Assisted Unit unit) {
        this.measurements = new ArrayList<Measurement>();
        add(quantity, unit);
    }

    @Override
    public String getText() {
        String text = "";
        String separator = ", ";
        for (Measurement measurement : measurements) {
            text += measurement.getText() + separator;
        }
        return text.substring(0, text.length() - separator.length());
    }

    public void add(double quantity, Unit unit) {
        Measurement measurement = createSingleMeasurement(quantity, unit);
        measurements.add(measurement);
    }

    private Measurement createSingleMeasurement(double quantity, Unit unit) {
        return new SingleMeasurement(quantity, unit);
    }
}
