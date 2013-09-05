package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isOneOf;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(JukitoRunner.class)
public class CompositeMeasurementTest {
    @Inject
    CompositeMeasurement.Factory compositeMeasurementFactory;

    @Inject
    SingleMeasurement.Factory singleMeasurementFactory;

    public static class Module extends JukitoModule {
        protected void configureTest() {
            install(new FactoryModuleBuilder().build(SingleMeasurement.Factory.class));
            install(new FactoryModuleBuilder().build(CompositeMeasurement.Factory.class));
        }
    }

    @Test
    public void hasQuantityAndUnitInTextRepresentation() {
        CompositeMeasurement measurement = compositeMeasurementFactory.create(1, "cup");
        assertThat(measurement.getText(), is("1 cup"));
    }

    @Test
    public void addsMeasurementWhenUnitIsDifferent() {
        CompositeMeasurement compositeMeasurement = compositeMeasurementFactory.create(1, "cup");
        SingleMeasurement singleMeasurement = singleMeasurementFactory.create(2, "tsp");

        compositeMeasurement.add(singleMeasurement);

        assertThat(compositeMeasurement.getText(), isOneOf("1 cup, 2 tsp", "2 tsp, 1 cup"));
    }

    @Test
    public void addsAndCoalescesMeasurementWhenUnitsAreTheSame() {
        String unit = "cup";
        CompositeMeasurement compositeMeasurement = compositeMeasurementFactory.create(1, unit);
        SingleMeasurement singleMeasurement = singleMeasurementFactory.create(2, unit);

        compositeMeasurement.add(singleMeasurement);

        assertThat(compositeMeasurement.getText(), is("3 cup"));
    }

    @Test
    public void addsFirstSingleMeasurementOfCompositeWhenThereIsOnlyOne() {
        CompositeMeasurement baseMeasurement = spy(compositeMeasurementFactory.create(1, "cup"));
        CompositeMeasurement compositeMeasurementToAdd = compositeMeasurementFactory.create(2, "tsp");
        SingleMeasurement singleMeasurementToAdd = compositeMeasurementToAdd.measurements.iterator().next();

        baseMeasurement.add(compositeMeasurementToAdd);

        verify(baseMeasurement).add(singleMeasurementToAdd);
    }

    @Test
    public void addsLastSingleMeasurementOfCompositeWhenThereAreTwo() {
        CompositeMeasurement baseMeasurement = spy(compositeMeasurementFactory.create(1, "cup"));
        CompositeMeasurement compositeMeasurementToAdd = compositeMeasurementFactory.create(2, "tsp");
        compositeMeasurementToAdd.add(singleMeasurementFactory.create(3, "fl oz"));
        SingleMeasurement[] singleMeasurementArray = new SingleMeasurement[compositeMeasurementToAdd.measurements.size()];
        SingleMeasurement lastSingleMeasurementToAdd = compositeMeasurementToAdd.measurements.toArray(singleMeasurementArray)[1];

        baseMeasurement.add(compositeMeasurementToAdd);

        verify(baseMeasurement).add(lastSingleMeasurementToAdd);
    }

    @Test
    public void addsTwoSingleMeasurementsWhenThereAreTwoInComposite() {
        CompositeMeasurement baseMeasurement = spy(compositeMeasurementFactory.create(1, "cup"));
        CompositeMeasurement compositeMeasurementToAdd = compositeMeasurementFactory.create(2, "tsp");
        compositeMeasurementToAdd.add(singleMeasurementFactory.create(3, "fl oz"));

        baseMeasurement.add(compositeMeasurementToAdd);

        verify(baseMeasurement, times(2)).add(any(SingleMeasurement.class));
    }
}
