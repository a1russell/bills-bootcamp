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
public class CompositeMeasurementTest {
    @Inject
    CompositeMeasurementFactory measurementFactory;

    public static class Module extends JukitoModule {
        protected void configureTest() {
            install(new FactoryModuleBuilder().build(SingleMeasurementFactory.class));
            install(new FactoryModuleBuilder().build(CompositeMeasurementFactory.class));
        }
    }

    @Test
    public void hasQuantityAndUnitInTextRepresentation() {
        CompositeMeasurement measurement = measurementFactory.create(1, Unit.CUP);
        assertThat(measurement.getText(), is("1 cup"));
    }

    @Test
    public void addsMeasurementWhenUnitIsDifferent() {
        CompositeMeasurement measurement = measurementFactory.create(1, Unit.CUP);
        measurement.add(2, Unit.TSP);
        assertThat(measurement.getText(), is("1 cup, 2 tsp"));
    }

    @Test
    public void addsAndCoalescesMeasurementWhenUnitsAreTheSame() {
        CompositeMeasurement measurement = measurementFactory.create(1, Unit.CUP);
        measurement.add(2, Unit.CUP);
        assertThat(measurement.getText(), is("3 cup"));
    }
}
