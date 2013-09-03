package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JukitoRunner.class)
public class SingleMeasurementTest {
    @Inject
    SingleMeasurementFactory measurementFactory;

    public static class Module extends JukitoModule {
        protected void configureTest() {
            install(new FactoryModuleBuilder().build(SingleMeasurementFactory.class));
        }
    }

    @Test
    public void hasQuantityAndUnitInTextRepresentation() {
        SingleMeasurement measurement = measurementFactory.create(1, "cup");
        assertThat(measurement.getText(), is("1 cup"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddMeasurementOfAnotherUnit() {
        SingleMeasurement baseMeasurement = measurementFactory.create(1, "cup");
        SingleMeasurement measurementToAdd = measurementFactory.create(2, "fl oz");
        baseMeasurement.add(measurementToAdd);
    }

    @Test
    public void addsToQuantity() {
        String unit = "cup";
        SingleMeasurement baseMeasurement = measurementFactory.create(1, unit);
        SingleMeasurement measurementToAdd = measurementFactory.create(2, unit);
        baseMeasurement.add(measurementToAdd);
        assertThat(baseMeasurement.getText(), is("3 cup"));
    }
}
