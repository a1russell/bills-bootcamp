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
        SingleMeasurement measurement = measurementFactory.create(1, Unit.CUP);
        assertThat(measurement.getText(), is("1 cup"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddMeasurementOfAnotherUnit() {
        SingleMeasurement baseMeasurement = measurementFactory.create(1, Unit.CUP);
        SingleMeasurement measurementToAdd = measurementFactory.create(2, Unit.FL_OZ);
        baseMeasurement.addSingleMeasurement(measurementToAdd);
    }

    @Test
    public void addsToQuantity() {
        SingleMeasurement baseMeasurement = measurementFactory.create(1, Unit.CUP);
        SingleMeasurement measurementToAdd = measurementFactory.create(2, Unit.CUP);
        baseMeasurement.addSingleMeasurement(measurementToAdd);
        assertThat(baseMeasurement.getText(), is("3 cup"));
    }
}
